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
		result.put("GBO", new WordResult("GBO", 3, 3, "DUL"));
		System.out.println("Testing Result Size" + " Actual: " + actualresult.size() + " Expected: " + result.size());
		assertEquals(true, result.size() == actualresult.size());
		System.out.println("Result count verified");
		for (String key : ws.getResult().keySet()) {
			// System.out.println("Verifying X Cordinates");
			assertEquals(true, result.get(key).getAt_x() == ws.getResult().get(key).getAt_x());
			// System.out.println("Verified X Cordinates");
			// System.out.println("Verifying Y Cordinates");
			assertEquals(true, result.get(key).getAt_y() == ws.getResult().get(key).getAt_y());
			// System.out.println("Verified Y Cordinates");
			// System.out.println("Verifying Direction");
			assertEquals(true, result.get(key).getDirection() == ws.getResult().get(key).getDirection());
			// System.out.println("Verified Direction");

		}

		System.out.println("Phew!!Success");

	}

}
