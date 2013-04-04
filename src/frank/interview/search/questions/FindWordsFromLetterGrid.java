package frank.interview.search.questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import frank.interview.ds.trie.Node;
import frank.interview.ds.trie.Trie;

/**
 * Given a grid of random letters, and a target word, find a path in the grid,
 * which corresponds to the target word. 
 * 
 * Given a grid of random letters, and a list of valid words, find all possible paths
 * that corresponds to the valid words
 * @author Frank
 *
 */
public class FindWordsFromLetterGrid {
	
	private static Trie<String> buildDictionary(String file) throws Exception {
		Trie<String> dict = new Trie<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null) {
				String word = line.trim();
				if (word.matches("[a-z|A-Z]+") && word.length() > 1) {
					dict.put(word, word);
				}
			}
			return dict;
		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Set<String> findWords(char [][] grid, int n, String dictFile) throws Exception {
		printLetterGrid(grid, n);
		Trie<String> dict = buildDictionary(dictFile);
		boolean [][] taken = new boolean [n][n];
		List<String> wordsFound = new LinkedList<String>();
		Set<String> result = new TreeSet<String>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				List<String> found = explore(grid, n, i, j, dict.getRoot(), taken);
				if (found != null) {
					wordsFound.addAll(found);
				}
			}
		}
		result.addAll(wordsFound);
		return result;
	}
	
	private static List<String> explore(char [][] grid, int n, int i, int j, Node<String> node, boolean [][] taken) {
		if (node == null) {
			return null;
		}
		List<String> result = new LinkedList<String>();
		if (!taken[i][j]) {
			Node<String> next = node.getChild(grid[i][j]);
			if (next != null) {
				if (next.getValue() != null) {
					result.add(next.getValue());
				}
				List<String> found = null;
				taken[i][j] = true;
				// search in upper cell
				if (i > 0) {
					found = explore(grid, n, i-1, j, next, taken);
					if (found != null) result.addAll(found);
				}
				// search in right cell
				if (j < n-1) {
					found = explore(grid, n, i, j+1, next, taken);
					if (found != null) result.addAll(found);
				}
				// search in the lower cell
				if (i < n-1) {
					found = explore(grid, n, i+1, j, next, taken);
					if (found != null) result.addAll(found);
				}
				// search in the left cell
				if (j > 0) {
					found = explore(grid, n, i, j-1, next, taken);
					if (found != null) result.addAll(found);
				}
				taken[i][j] = false;
				return result;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private static boolean [][] initTakenMatrix(int n) {
		boolean [][] taken = new boolean [n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				taken[i][j] = false;
			}
		}
		return taken;
	}
	
	public static boolean findWord(char [][] grid, int n, String word) {
		printLetterGrid(grid, n);
		boolean [][] taken = initTakenMatrix(n);
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
