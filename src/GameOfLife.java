	import java.util.Arrays;
import java.lang.Math;

public class GameOfLife {
	int n;
	int[][] grid;
	private boolean torousMode;

	//Contructor which takes an intial state
	public GameOfLife(int[][] initialState, boolean torousFlag) {
		n = initialState.length;
		torousMode = torousFlag;
		
		grid = new int[n][n];
		grid = Arrays.copyOf(initialState, n);initCanvas();
	}
	
	// Contructor which creates a random nxn grid
	public GameOfLife(int n, boolean torousFlag) { 
		this.n = n;
		grid = new int[n][n];
		torousMode = torousFlag;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = (int) (Math.random() * 2);
			}
		}
		initCanvas();
	}



	// Computes the next gamestate
	public void nextState() {
		int[][] nextGen = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				//For each point, find the number of neighbors and apply rules
				int status = grid[i][j];
				int neighbors;
				
				if (torousMode) {
					neighbors = liveNeighborsTorous(i, j);
				} else {
					neighbors = liveNeighbors(i,j);
				}
				
				if (status == 1 && (neighbors == 2 || neighbors == 3)) {
					nextGen[i][j] = 1;
				} else if (status == 0 && neighbors == 3) {
					nextGen[i][j] = 1;
				} else {
					nextGen[i][j] = 0;
				}
				
				
				if (nextGen[i][j] == 1) {
					drawCell(i, j);
				} else if (grid[i][j] == 1) {
					drawDeadCell(i, j);
				}
			}
		}
		grid = Arrays.copyOf(nextGen, n);
		StdDraw.show(100); // Render and wait for 0.1 seconds.
		StdDraw.clear();
	}
	
	// Live neighbors metode
	private int liveNeighborsTorous(int x, int y) {
		int liveCells = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int col = (x + i + n) % n;
				int row = (y + j + n) % n;
				liveCells += grid[col][row];
			}
		}
		liveCells -= grid[x][y];
		return liveCells;
	}

	private int liveNeighbors(int x, int y) {
		int liveCells = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int col = (x + i);
				int row = (y + j);
				if (col < 0 || row < 0 || col >= n || row >= n) {
					liveCells += 0;
				} else {
					liveCells += grid[col][row];
				}
			}
		}
		liveCells -= grid[x][y]; // The previous for loop counts the status of the current cell, here we correct
									// it.
		return liveCells;
	}
	

	//DRAWING METHODS
	
	//I think the StdDraw "instance" is linked to this object, therefore we cannot inittialize the canvas without a GameOfLife object (at least without restructuring the way we draw the matrix). 
	// This is why a screen won't appear until all user input has been processed and an object initialized.
	public void initCanvas() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.show(0);
		StdDraw.setScale(-1, n);
	}


	private static void drawDeadCell(int x, int y) {
		StdDraw.setPenColor(150, 0, 42);
		StdDraw.filledSquare(x, y, 0.5);
	}

	public static void drawCell(int x, int y) {
		StdDraw.setPenColor(132, 222, 100);
		StdDraw.filledSquare(x, y, 0.5);
	}


}
