package personal.deon.framework.data;

public class MainTest {
	
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hel"+new String("lo");
		String s3 = "hel";
		String s4 = "lo";
		String s5 = s3 + s4;
		String s6 = "hel" + "lo";
//		String s7 = "hello";
		System.out.println(s1 == s5);
		System.out.println(s1 == s6);
		System.out.println(s1 == s2);
	}
}
