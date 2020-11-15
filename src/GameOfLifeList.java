import java.util.Arrays;

public class GameOfLifeList {
	public static final int SIMULATION_LIMIT = 10000000; 
	
	private int[][][] list;
	public int generation;
	
	public GameOfLifeList(GameOfLife initialSnap) {
		list = new int[SIMULATION_LIMIT][][];
		list[0] = initialSnap.grid;
		generation = 1;
	}
	
	public void append(GameOfLife last) { //That we have not surpassed the arrays length must be checked elsewhere, here in GOL-Main
		list[generation] = last.grid;
		generation++;
	}
	
	//Uses the latest GOL-snapshot to find repetitions of gamestate. As the game is deterministic, we only have to check for one equality, as two equal gamestates will lead to the same outcome and thus further repetition.
	public int findFrequencyWithLast() {
		for (int i = 0; i < generation-1; i++) {
			if (arrayEquals(list[generation-1],list[i])) {
				return generation-i;
			}
		}
		return -1;
	}
	
	private boolean arrayEquals(int[][] x, int[][] y) {
		if (x.length != y.length) {
			return false;
		} else {
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					if (x[i][j] != y[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
