import java.util.*;
import java.io.*;

public class GameOfLifeMain {

	private static final String[] PATHS = new String[] { "gol/acorn.gol", "gol/glider_gun.gol", "gol/pentadecathlon.gol",
			"gol/pulsar.gol", "gol/toad.gol" };
	private static final int[] SIZES = new int[] { 250, 40, 16, 15, 4};

	public static void main(String[] args) throws FileNotFoundException {

		Scanner console = new Scanner(System.in);

		GameOfLife l = welcomePlayer(console);
		l.setGrid();
		while (true) {
			l.nextState();
		}

	}

	private static GameOfLife welcomePlayer(Scanner console) throws FileNotFoundException {
		System.out.print("Welcome to Conway's Game of Life!   \n" + "You now have the following options: \n");
		System.out.print("1) Generate a random n x n state\n" + "2) Choose a predefined initial state\n");
		System.out.print("Press 1 or 2: ");

		boolean predefined = getChoice(console);

		boolean torousMode = getTorousMode(console);

		if (predefined) {
			System.out.println("You now have the following options: ");
			System.out.print("1) acorn \t 2) glider_gun \n" + "3) pentadecathlon \t 4) pulsar \n" + "5) toad\n");
			System.out.println("Input a number from 1-5 to pick a state!");

			int[][] grid = new int[SIZES[4]*2][SIZES[4]*2]; // This is to give space for the patterns to take shape
			int[][] chosenGrid = readFile(PATHS[4]);
			copyInto(grid, chosenGrid);
			return new GameOfLife(grid);
		} else {
			System.out.println("How large should the random grid be? ");
			return new GameOfLife(200);
		}

	}

	private static boolean getChoice(Scanner console) {
		String errorMsg = "Seems like your input was invalid... Generating random N x N matrix";
		boolean choice = false;
		if (console.hasNextInt()) {
			int input = console.nextInt();
			if (input == 2) {
				choice = true;
			} else {
				System.out.println(errorMsg);
			}
		} else {
			System.out.println(errorMsg);
		}

		return choice;

	}

	private static boolean getTorousMode(Scanner console) {
		System.out.println("Should the map be in torous or normal mode? (Y/n)");
		boolean torousMode = false;
		if (console.hasNextLine()) {
			String stringInput = console.nextLine();
			if (stringInput.contains("Y") || stringInput.contains("y")) {
				System.out.println("Map is now a torous!");
				torousMode = true;
			}
		}
		return torousMode;
	}

	private static int[][] readFile(String path) throws FileNotFoundException {
		int lineCounter = 0;

		Scanner input = new Scanner(new File(path));
		while (input.hasNextLine()) {
			String line = input.nextLine();
			lineCounter++;
		}

		int[][] fileMatrix = new int[lineCounter][lineCounter];

		Scanner input2 = new Scanner(new File(path));
		for (int i = 0; i < lineCounter; i++) {
			for (int j = 0; j < lineCounter; j++) {
				if (input2.hasNextInt()) {
					fileMatrix[i][j] = input2.nextInt();
				}
			}
		}

		return fileMatrix;
	}
	
	
	// Niels
	// Gets data from a small matrix to larger one, centering the data in the
	// process such that it creates a valid GOL object
	private static void copyInto(int[][] largest, int[][] smallest) {
		for (int i = 0; i < smallest.length; i++) {
			for (int j = 0; j < smallest.length; j++) {
				int center = largest.length/2;
				int halfWidth = smallest.length/2;
				
				int xOffset = i-halfWidth+center;
				int yOffset = j-halfWidth+center;
				largest[xOffset][yOffset] = smallest[i][j];
			}
		}
	}

}
