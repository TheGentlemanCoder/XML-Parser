package code;

public class Test {
	public static void main(String[] args) {
		XMLParser xml = new XMLParser();
		xml.newFile("test file", "files/test.txt");
		String str = xml.fileToString("test file");
		System.out.println(str);
		xml.stringToFile("test file");
		System.out.println(System.getProperty("user.dir"));
	}
}
