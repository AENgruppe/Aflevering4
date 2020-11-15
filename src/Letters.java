import java.util.Scanner;

public class Letters {

	public static void main(String[] args) {
		System.out.println(countLetters());
	}
	public static int countLetters() {
		Scanner console = new Scanner(System.in);
		String input = console.nextLine();
		int antalLet = 0;
		for(int i = 0; i<input.length(); i++) {
			if(input.charAt(i)!=' ') {
				antalLet++;
			}
		}
		return antalLet;
	}
}
