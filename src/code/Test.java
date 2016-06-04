package code;

public class Test {
	public static void main(String[] args) {
		XMLParser xml = new XMLParser();
		System.out.println(xml.parse("test", "files/test.txt"));
	}
}
