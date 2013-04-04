package frank.interview.search.questions;

public class FindWordsFromLetterGrid {

	public static boolean findWord(char [][] grid, int n, String word) {
		printLetterGrid(grid, n);
		boolean [][] taken = new boolean [n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				taken[i][j] = false;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (findWord(grid, n, word, i, j, 0, taken)) {
					printWordPath(grid, taken, n, word);
					return true;
				}
			}
		}
		System.out.println("Can't find word - "+word);
		return false;
	}
	
	private static boolean findWord(char [][] grid, int n, String word, int i, int j, int pos, boolean [][] taken) {
		if (pos == word.length()) {
			return true;
		}
		if (taken[i][j] == false && word.charAt(pos) == grid[i][j]) {
			taken[i][j] = true;
			// search in upper cell
			if (i > 0) {
				if (findWord(grid, n, word, i-1, j, pos+1, taken)) {
					return true;
				}
			}
			// search in right cell
			if (j < n - 1) {
				if (findWord(grid, n, word, i, j+1, pos+1, taken)) {
					return true;
				}
			}
			// search in lower cell
			if (i < n - 1) {
				if (findWord(grid, n, word, i+1, j, pos+1, taken)) {
					return true;
				}
			}
			// search in left cell
			if (j > 0) {
				if (findWord(grid, n, word, i, j-1, pos+1, taken)) {
					return true;
				}
			}
			// reset this cell, if there is no correct path
			taken[i][j] = false;
			return false;
		} else {
			return false;
		}
	}
	
	private static void printLetterGrid(char [][] grid, int n) {
		System.out.println("-------Grid-------");
		for (int i = 0; i < n; i++) {
			StringBuffer buffer = new StringBuffer();
			for (int j = 0; j < n; j++) {
				buffer.append(grid[i][j]);
				buffer.append(' ');
			}
			System.out.println(buffer);
		}
	}
	
	private static void printWordPath(char [][] grid, boolean [][] taken, int n, String word) {
		System.out.println("-------Found word - "+word+"-------");
		for (int i = 0; i < n; i++) {
			StringBuffer buffer = new StringBuffer();
			for (int j = 0; j < n; j++) {
				if (taken[i][j]) {
					buffer.append(grid[i][j]);
					buffer.append(' ');
				} else {
					buffer.append("  ");
				}
			}
			System.out.println(buffer);
		}
	}
}
