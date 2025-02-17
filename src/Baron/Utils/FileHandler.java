package Baron.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
	// Read entire file as String
	public static String readFileAsAString(String filepath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filepath)));
	}

	// Read file line by line into a list of strings
	public static List<String> readFileAsLines(String filepath) throws IOException {
		return Files.readAllLines(Paths.get(filepath));
	}

	// Read A file as byte array
	public static byte[] readFileAsBytes(String filepath) throws IOException {
		return Files.readAllBytes(Paths.get(filepath));
	}
}
