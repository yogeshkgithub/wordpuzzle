package com.yogesh;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class WordSearchTest {
	private final static String PUZZLE_FILE = "PuzzleTester.txt";
	private final static String WORDS_FILE = "WordTester";

	/**
	 * This test covers the overall units of the wordsearch class
	 * 
	 * 
	 */

	// @Ignore
	@Test
	public void testWordSearch() {
		System.out.println("Inside");
		WordSearch ws = new WordSearch();
		HashMap<String, WordResult> actualresult;// = new
		ws.setPuzzlegrid(PUZZLE_FILE);
		ws.setWords(WORDS_FILE);
		ws.solvePuzzle();
		actualresult = ws.getResult();
		HashMap<String, WordResult> result = new HashMap<String, WordResult>();
		result.put("COB", new WordResult("COB", 0, 0, "DDR"));
		result.put("GAAB", new WordResult("GAAB", 3, 3, "U"));
		result.put("CRAB", new WordResult("CRAB", 0, 0, "LR"));
		result.put("BAR", new WordResult("BAR", 0, 3, "RL"));
		// result.put("GBO", new WordResult("GBO", 3, 3, "DUL"));
		assertEquals(true, result.size() == actualresult.size());
		for (String key : ws.getResult().keySet()) {
			assertEquals(true, result.get(key).getAt_x() == ws.getResult().get(key).getAt_x());
			assertEquals(true, result.get(key).getAt_y() == ws.getResult().get(key).getAt_y());
			assertEquals(true, result.get(key).getDirection() == ws.getResult().get(key).getDirection());

		}

		System.out.println("Success");

	}
}
