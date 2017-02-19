package com.yogesh;

public class PuzzleApplicationMain {
	private final static String PUZZLE_FILE = "PuzzleFile.txt";
	private final static String WORDS_FILE = "WordList.txt";
	
	public static void main(String args[]) {
		WordSearch ws = new WordSearch();
		ws.setPuzzlegrid(PUZZLE_FILE);
		ws.setWords(WORDS_FILE);
		ws.printPuzzlegrid();
		ws.solvePuzzle();
		ws.printResult();

	}

}
