package frank.interview.search.questions.test;

import static org.junit.Assert.*;

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

}
