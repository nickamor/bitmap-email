package club.bitmap.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.KMSEncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import club.bitmap.config.Config;

public class CloudFolder {
	
	public class CloudFile {
		public final CloudFolder parent;
		public final String key;
		public final Date lastModified;
		
		public CloudFile(CloudFolder parent, String key, Date lastModified) {
			this.parent = parent;
			this.key = key;
			this.lastModified = lastModified;
		}

		public void erase() {
			parent.erase(this);
		}

		public InputStream getInputStream() {
			return parent.getInputStream(this);
		}
	}

	private final AmazonS3EncryptionClient client;
	private final Config config;

	public CloudFolder(Config config) {
		this.config = config;
		
		AWSCredentials credentials = new BasicAWSCredentials(config.getAwsAccessKeyId(), config.getAwsSecretAccessKey());

		KMSEncryptionMaterialsProvider materialsProvider = new KMSEncryptionMaterialsProvider(config.getKmsCmkId());

		Region region = Region.getRegion(Regions.fromName(config.getKmsCmkRegion()));
		
		CryptoConfiguration cryptoConfig =  new CryptoConfiguration().withAwsKmsRegion(region);

		client = new AmazonS3EncryptionClient(credentials, materialsProvider, cryptoConfig);	
	}
	
	private InputStream getInputStream(CloudFile file) {
		GetObjectRequest req = new GetObjectRequest(config.getS3BucketName(), file.key);
		S3Object object = client.getObject(req);

		return object.getObjectContent();
	}

	public List<CloudFile> list() {
		ObjectListing objects = client.listObjects(config.getS3BucketName(), config.getS3BucketPrefix());
		List<CloudFile> files = new ArrayList<CloudFile>();
		
		if (objects.isTruncated()) {
			// TODO: coalesce truncated listings
			System.err.println("Warning: Object listing truncated: multiple S3 calls required.");
		}
		
		for (S3ObjectSummary summary : objects.getObjectSummaries()) {
			if (!summary.getKey().equals(config.getS3BucketPrefix())) {
				files.add(new CloudFile(this, summary.getKey(), summary.getLastModified()));
			}
		}
		
		return files;
	}
	
	private void erase(CloudFile file) {
		DeleteObjectRequest req = new DeleteObjectRequest(config.getS3BucketName(), file.key);
		client.deleteObject(req);
	}
	
	public void shutdown() {
		client.shutdown();
	}
}
