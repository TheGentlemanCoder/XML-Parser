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
	private HashMap<String, Path> paths;
	private HashMap<String, ArrayList<String>> files;
	
	public XMLParser() {
		this.paths = new HashMap<>();
		this.files = new HashMap<>();
	}
	
	private void newFile(String fileName, String pathName) {
		paths.put(fileName, Paths.get(dir, pathName));
	}
	
	private String fileToString(String fileName) {
		try {
			byte[] encoded = Files.readAllBytes(paths.get(fileName));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("An IOException Occured");
			e.printStackTrace();
			System.exit(1);
			return "";
		}
	}
	
	private void fileToList(String fileName) {
		String file = fileToString(fileName);
		int first_bracket = 0;
		int second_bracket = 0;
		files.put(fileName, new ArrayList<String>());
		
		for (int index = 0; index < file.length(); index++) {
			if (file.charAt(index) == '<' && file.charAt(index + 1) == '/') {
				first_bracket = index + 2;
				
				if (first_bracket - 3 > second_bracket) {
					files.get(fileName).add(
					file.substring(second_bracket + 1, first_bracket - 2));
				}
			} else if (file.charAt(index) == '<') {
				first_bracket = index + 1;
			} else if (file.charAt(index) == '>') {
				second_bracket = index;
				
				files.get(fileName).add(file.substring(first_bracket, second_bracket));
			}
		}
	}
	
	public ArrayList<String> parse(String fileName, String pathName) {
		newFile(fileName, pathName);
		fileToList(fileName);
		return files.get(fileName);
	}
}
