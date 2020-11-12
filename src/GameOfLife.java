import java.util.Arrays;
import java.lang.Math;

public class GameOfLife {
	int n;
	int gen;
	int[][] grid;

	public GameOfLife(int[][] initialState) {
		this.n = n;
		n = initialState.length;
		grid = new int[n][n];
		grid = Arrays.copyOf(initialState, n);
	}

	// public GameOfLife(int n) {
	// this.n = n;
	// grid = new int[n][n];
	// }

	// metode til at tegne
	public void setGrid() {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setScale(n - (2 + n), n + 2);
		StdDraw.setPenRadius(0.001);
		StdDraw.show(0);
	}

	// tegning
	public static void drawNewCell(int x, int y) {
		StdDraw.setPenColor(124, 185, 232);
		StdDraw.filledSquare(x, y, 0.5);
	}

	// tegning
	public static void drawDeadCell(int x, int y) {
		StdDraw.setPenColor(150, 0, 42);
		StdDraw.filledSquare(x, y, 0.5);
	}

	// tegning
	public static void drawCell(int x, int y) {
		StdDraw.setPenColor(132, 222, 100);
		StdDraw.filledSquare(x, y, 0.5);
	}

	// metode til udprint af grid
	public void printValues(int[][] game) {
		for (int i = 0; i < game.length; i++) {
			for (int j = 0; j < game[i].length; j++) {
				System.out.print(game[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// metode til udfyldning af værdier
	public void initializeGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = (int) (Math.random() * 2);
			}
		}
		printValues(grid);
	}

	// Live neighbors metode
	private int liveNeighborsTorous(int[][] grid, int x, int y) {
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

	// next state metode for hele grid
	public void nextState() {
		int[][] nextGen = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				// opdater naboer
				int status = grid[i][j];
				int neighbors = liveNeighbors(i, j);

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
		StdDraw.show(100);
		// StdDraw.pause(16);//16 ms er ca 60 fps
		StdDraw.clear();
	}

}
