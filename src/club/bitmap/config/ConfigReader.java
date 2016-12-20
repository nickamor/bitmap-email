package club.bitmap.config;

import java.util.Properties;

class ConfigReader {
	static Properties fromFile(String filename) {
		Properties properties = new Properties();

		return properties;
	}

	static Properties fromArguments(String[] args) {
		Properties properties = new Properties();

		return properties;
	}

	static Properties fromEnvironment() {
		Properties properties = new Properties();

		return properties;
	}

	static class PropertiesConfig implements Config {
		private Properties properties = new Properties();

		@Override
		public String getAwsAccessKeyId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getAwsSecretAccessKey() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getKmsCmkId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getKmsCmkRegion() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getS3BucketName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getS3BucketPrefix() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getLocalPrefix() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean getDeleteRemoteObjects() {
			// TODO Auto-generated method stub
			return false;
		}

		public void setProperties(Properties properties) {
			this.properties.keySet().addAll(properties.keySet());
		}
	}
}