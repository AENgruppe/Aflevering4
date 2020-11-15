
public class Letters {
	public static void main(String[] args) {
		int out = 0;
		for (String i: args) {
			out += i.length();
		}
		System.out.printf("%d",out);
	}
}