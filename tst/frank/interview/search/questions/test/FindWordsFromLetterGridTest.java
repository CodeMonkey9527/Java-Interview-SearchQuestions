package frank.interview.search.questions.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import frank.interview.search.questions.FindWordsFromLetterGrid;


public class FindWordsFromLetterGridTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFind1() {
		char [][] grid = {
			{'a', 'b', 'c', 'd', 'e'}, 
			{'f', 'g', 'h', 'i', 'j'}, 
			{'k', 'l', 'm', 'n', 'o'}, 
			{'p', 'q', 'r', 's', 't'}, 
			{'u', 'v', 'w', 'x', 'y'}
		};
		assertTrue(FindWordsFromLetterGrid.findWord(grid, 5, "abghide"));
		assertTrue(FindWordsFromLetterGrid.findWord(grid, 5, "dinmlqvu"));
		assertFalse(FindWordsFromLetterGrid.findWord(grid, 5, "dinmqvu"));
	}

	@Test
	public void testFindWords1() throws Exception {
		char [][] grid = {
			{'a', 'b', 'c', 'd', 'e'}, 
			{'f', 'g', 'h', 'i', 'j'}, 
			{'k', 'l', 'm', 'n', 'o'}, 
			{'p', 'q', 'r', 's', 't'}, 
			{'u', 'v', 'w', 'x', 'y'}
		};
		System.out.println(FindWordsFromLetterGrid.findWords(grid, 5, "data/dict.txt"));
	}
	
	@Test
	public void testFindWordsRandom() throws Exception {
		int n = 5;
		char [][] grid = new char [n][n];
		Random rn = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char ch = (char)(rn.nextInt(26)+'a');
				grid[i][j] = ch;
			}
		}
		System.out.println(FindWordsFromLetterGrid.findWords(grid, n, "data/dict.txt"));
	}
}
