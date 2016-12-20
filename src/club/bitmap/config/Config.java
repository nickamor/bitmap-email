package club.bitmap.config;

public interface Config {

	public String getAwsAccessKeyId();

	public String getAwsSecretAccessKey();

	public String getKmsCmkId();

	public String getKmsCmkRegion();

	public String getS3BucketName();

	public String getS3BucketPrefix();

	public String getLocalPrefix();

	public boolean getDeleteRemoteObjects();
}
