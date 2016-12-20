package club.bitmap.config;

public class DefaultConfig implements Config {
	// AWS Client
	private static final String aws_access_key_id = "AKIAJ5MVENYCM4VL67XA";
	private static final String aws_secret_access_key = "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";

	// KMS Encryption
	private static final String kms_cmk_id = "dfd1c3c6-7ed0-4324-8e1b-b029ebbc152c";
	private static final String kms_cmk_region = "us-east-1";

	// S3 Bucket
	private static final String s3_bucket_name = "mail.bitmap.club";
	private static final String s3_bucket_prefix = "mailbox/";

	// Local file sync
	private static final String local_prefix = "mailbox/";
	private static final boolean delete_remote_objects = true;

	public String getAwsAccessKeyId() {
		return aws_access_key_id;
	}

	public String getAwsSecretAccessKey() {
		return aws_secret_access_key;
	}

	public String getKmsCmkId() {
		return kms_cmk_id;
	}

	public String getKmsCmkRegion() {
		return kms_cmk_region;
	}

	public String getS3BucketName() {
		return s3_bucket_name;
	}

	public String getS3BucketPrefix() {
		return s3_bucket_prefix;
	}

	public String getLocalPrefix() {
		return local_prefix;
	}

	public boolean getDeleteRemoteObjects() {
		return delete_remote_objects;
	}
}
