package com.yogesh;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.yogesh.WordSearch;
import com.yogesh.WordResult;

public class WordSearchTest {
	private final static String PUZZLE_FILE = "PuzzleTester.txt";
	private final static String WORDS_FILE = "WordTester";

	// @Ignore
	@Test
	public void testWordSearch() {
		System.out.println("Inside");
		HashMap<String, WordResult> actualresult;// = new
											// HashMap<String,WordResult>();
		WordSearch ws = new WordSearch();

		ws.setPuzzlegrid(PUZZLE_FILE);
		ws.setWords(WORDS_FILE);
		//ws.printPuzzlegrid();
		ws.solvePuzzle();
		//ws.printResult();

		actualresult = ws.getResult();
		HashMap<String, WordResult> result = new HashMap<String, WordResult>();
        result.put("COB", new WordResult("COB", 0, 0, "DDR"));
		result.put("GAAB", new WordResult("GAAB", 3, 3, "U"));
		result.put("CRAB", new WordResult("CRAB", 0, 0, "LR"));
		result.put("BAR", new WordResult("BAR", 0, 3, "RL"));
		result.put("GBO", new WordResult("GBO", 3, 3, "DUL"));
		
		assertEquals(true,result.size()==actualresult.size());

		/*
		 * WordResult wr1= new WordResult("COB", 0, 0, "DDR"); WordResult wr2=
		 * new WordResult("GAAB", 3, 3, "U"); WordResult wr3= new
		 * WordResult("CRAB", 0, 0, "LR"); WordResult wr4= new WordResult("BAR",
		 * 0, 3, "RL"); WordResult wr5= new WordResult("GBO", 3, 3, "DUL");
		 */

	}
}
