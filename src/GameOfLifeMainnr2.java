import java.util.*;
import java.io.*;

public class GameOfLifeMainnr2 {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner console = new Scanner(System.in);
		int[][] matrixData = getInput(console);

		GameOfLife l = new GameOfLife(matrixData);
		l.setGrid();
		l.initializeGrid();
		while (true) {
			l.nextState();
		}

	}

	private static int[][] getInput(Scanner console) throws FileNotFoundException {
		int lineCounter = 0;
	
		String file = console.next();
		Scanner input = new Scanner(new File(file));
		while (input.hasNextLine()) {
			String line = input.nextLine();
			lineCounter++;
		}

		int[][] fileMatrix = new int[lineCounter][lineCounter];

		Scanner input2 = new Scanner(new File(file));
		for (int i = 0; i < lineCounter; i++) {
			for (int j = 0; j < lineCounter; j++) {
				if (input2.hasNextInt()) {
					fileMatrix[i][j] = input2.nextInt();
				}
			}
		}
		
		return fileMatrix;
	}

}
