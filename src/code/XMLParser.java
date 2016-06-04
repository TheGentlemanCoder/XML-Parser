package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/*
* @author Nicholas Ryan Smith
*/

public class XMLParser {
	private final static String dir = System.getProperty("user.dir");
	private static HashMap<String, Path> paths;
	private static HashMap<String, List<String>> files;
	
	public XMLParser() {
		paths = new HashMap<>();
		files = new HashMap<>();
	}
	
	public void newFile(String name, String path) {
		paths.put(name, Paths.get(dir, path));
	}
	
	public static String fileToString(String name) {
		try {
			byte[] encoded = Files.readAllBytes(paths.get(name));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
	}
}
