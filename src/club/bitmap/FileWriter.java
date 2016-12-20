package club.bitmap;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

import club.bitmap.client.CloudFolder.CloudFile;

public class FileWriter {

	private CloudFile file;

	public FileWriter(CloudFile file) {
		this.file = file;
	}

	public void write() throws IOException {
		CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING };
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		
		String lastModified = formatter.format(file.lastModified);
		String filename = String.format("%s.%s.eml", file.key, lastModified);
		
		Path path = Paths.get(System.getProperty("user.home"), filename);
		
		Files.createFile(path);
		Files.copy(file.getInputStream(), path, options);

		System.out.println(path.toString());
	}

}
