package club.bitmap;

import club.bitmap.client.CloudFolder;
import club.bitmap.client.CloudFolder.CloudFile;
import club.bitmap.config.Config;
import club.bitmap.config.DefaultConfig;

import java.io.IOException;
import java.util.List;

public class BitmapEmail {
	Config config = new DefaultConfig();

	public static void main(String[] args) {
		try {
			BitmapEmail bitmapEmail = new BitmapEmail(args);
			bitmapEmail.run();
		} catch (Exception e) {
			System.err.println("Fatal error: " + e.getMessage());
		}
	}
	
	public BitmapEmail(String[] args) {
		
	}
	
	public void run() throws IOException {
		CloudFolder folder = new CloudFolder(config);

		List<CloudFile> files = folder.list();
		System.out.println(String.format("%d new %s.\n", files.size(), files.size() != 1 ? "objects" : "object"));

		for (CloudFile file : files) {
			FileWriter writer = new FileWriter(file);
			writer.write();

			if (config.getDeleteRemoteObjects()) {
				file.erase();
			}
		}

		folder.shutdown();
	}
}
