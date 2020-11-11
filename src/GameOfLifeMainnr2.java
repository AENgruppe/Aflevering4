import java.util.*;
import java.io.*;

public class GameOfLifeMainnr2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		int lineNr = 0;
		Scanner console = new Scanner(System.in);
		String file = console.next();
		Scanner input = new Scanner(new File(file));
		while(input.hasNextLine()) {
			String line = input.nextLine();
			lineNr++;
		}
		
		int [][] fileMatrix = new int[lineNr][lineNr];
		
		Scanner input2 = new Scanner(new File(file));
		for(int i = 0; i< lineNr; i++) {
			for(int j = 0; j<lineNr; j++) {
				if(input2.hasNextInt()) {
					fileMatrix[i][j] = input2.nextInt();
				}
			}
		}
		
		printing(fileMatrix);
		
		//int[][] test = {
				//{0,0,0,0,0,0,1,0,1,1,0,1},
				//{0,0,0,0,0,0,0,1,0,0,0,1},
				//{0,0,1,1,1,0,1,1,1,1,1,0},
				//{0,1,1,1,0,0,1,0,1,1,0,1},
				//{0,0,0,0,0,0,0,0,0,0,0,0},
				//{0,0,0,0,0,0,1,1,1,0,1,1},
				//{0,0,0,0,0,0,0,0,1,0,1,1},
				//{0,0,0,0,0,0,0,0,0,1,1,0},
				//{0,0,1,1,1,0,1,1,1,0,0,0},
				//{0,1,1,1,0,0,1,1,0,0,1,1},
				//{0,1,0,1,0,0,0,1,1,0,0,0},
				//{0,0,0,0,0,0,1,1,1,1,0,1}
				//
		//};
		
		GameOfLife l = new GameOfLife(fileMatrix);
		l.setGrid();
		l.initializeGrid();
		while(true) {
			l.nextState();
		}
			
	}
		
		
		public static void printing(int[][] matrix){
			for(int i = 0; i < matrix.length; i++) {
				for(int j = 0; j < matrix[i].length; j++) {
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
	}
	
}
