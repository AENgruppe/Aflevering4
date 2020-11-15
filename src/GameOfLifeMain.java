import java.util.*;
import java.io.*;

public class GameOfLifeMain {

	private static final String[] PATHS = new String[] { "gol/acorn.gol", "gol/glider_gun.gol",
			"gol/pentadecathlon.gol", "gol/pulsar.gol", "gol/toad.gol" };
	private static final int[] SIZES = new int[] { 250, 40, 16, 15, 4 };

	public static void main(String[] args) throws FileNotFoundException {

		Scanner console = new Scanner(System.in);
		GameOfLife l = welcomePlayer(console);
		
		GameOfLifeList list = new GameOfLifeList(l); 
		int frequency = list.findFrequencyWithLast(); //Initialized before while loop to avoid unnecessary allocations, perhaps the compiler does this by itself?
		boolean freqFound = false;
		
		while (list.generation < GameOfLifeList.SIMULATION_LIMIT) {
			l.nextState();
			list.append(l);
			frequency = list.findFrequencyWithLast();
			if (frequency != -1 && !freqFound) {
				System.out.println("Found repetition at generation:" + list.generation + " with frequency " + frequency);
				freqFound = true;
			}
		}

	}

	private static GameOfLife welcomePlayer(Scanner console) throws FileNotFoundException {
		System.out.print("Welcome to Conway's Game of Life!   \n" + "You now have the following options: \n");
		System.out.print("\t1) Generate a random n x n state\n" + "\t2) Choose a predefined initial state (NB! This only works if the folder gol from gol.zip is in the project root folder! \n");
		System.out.print("Press 1 or 2: ");

		boolean predefined = getChoice(console);

		boolean torousMode = getTorousMode(console);

		if (predefined) {
			System.out.println("You now have the following options: ");
			System.out.print("1) acorn \t\t 2) glider gun \n" + "3) pentadecathlon \t 4) pulsar \n" + "5) toad\n");
			System.out.println("Input a number from 1-5 to pick a state!");

			int level = getLevel(console);
			int[][] chosenGrid = readFile(PATHS[level]);
			
			int[][] grid = new int[(int) (SIZES[level] * 1.5)][(int) (SIZES[level]* 1.5)]; // This is to give space for
																							// the patterns to take shape. However it seems that space has already been given to acorn.gol, which makes the map seem a bit too large, but as it works for the other levels it will have to make do.
			
			copyInto(grid, chosenGrid);
			return new GameOfLife(grid, torousMode);
		} else {
			System.out.println("How large should the random grid be? ");
			
			int size = getSize(console);
			
			return new GameOfLife(size, torousMode);
		}

	}
	
	private static boolean getChoice(Scanner console) {
		String errorMsg = "Seems like your input was invalid... Please try again: ";
		if (console.hasNextInt()) {
			int input = console.nextInt();
			if (input == 2) {
				return true;
			} else if (input == 1) {
				return false;
			}
		}
		System.out.println(errorMsg);
		console.next();
		return getChoice(console);

	}
	
	private static boolean getTorousMode(Scanner console) {
		System.out.println("Should the map be in torous mode? (Y/n)");
		boolean torousMode = false;
		String stringInput = console.next();
		if (stringInput.contains("Y") || stringInput.contains("y")) {
			torousMode = true;
		} else {
			System.out.println("Oops that was not a valid answer. The map is not in torous mode.");
		}
		return torousMode;
	}

	private static int getLevel(Scanner console) {
		while (console.hasNextInt()) {
			int input = console.nextInt();
			if (input >= 1 || input <= 5) {
				return input - 1;
			} else {
				System.out.println("Please try again, this is not a possible value.");
			}
		}
		System.out.println("Seems like that wasn't a number, using acorn.");
		return 0;
	}

	
	private static int getSize(Scanner console) {
		int size = -1;
		while (size < 1) {
			if (console.hasNextInt()) {
				size = console.nextInt();
			} else {
				console.next();
				System.out.println("Please input a number.");
			}
		}
		return size;
	}
	
	//Reads a .gol file and saves it's contents to a matrix, 
	// !! This method is not safe as it doesn't account for empty lines and whitespace. 
	//    It does not have logic to handle wrongly formatted files eg. a file with variable line length etc. !!
	private static int[][] readFile(String path) throws FileNotFoundException {
		int lineCounter = 0;
		Scanner input = new Scanner(new File(path));
		String line ="";
		while (input.hasNextLine()) {
			line = input.nextLine();
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

	// Used to copy a predefined map into a larger square array. This makes the fun stuff easier to see.
	// Basically a map widener. 
	private static void copyInto(int[][] largest, int[][] smallest) {
		
		int center = largest.length / 2;
		int halfWidth = smallest.length / 2;
		
		
		for (int i = 0; i < smallest.length; i++) {
			for (int j = 0; j < smallest[0].length; j++) {
				int xOffset = i + (center - halfWidth) ;
				int yOffset = j + (center - halfWidth);
				
				largest[xOffset][yOffset] = smallest[i][j];
			}
		}
	}

}
