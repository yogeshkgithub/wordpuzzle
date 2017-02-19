package com.yogesh;

public class PuzzleApplicationMain {
	
	public static void main(String args[]) {
		WordSearch ws = new WordSearch();
		ws.setPuzzlegrid();
		ws.setWords();
		ws.printPuzzlegrid();
		ws.solvePuzzle();
		ws.printResult();

	}

}
