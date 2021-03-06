
package com.yogesh;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author X8FP
 *
 */

public class WordSearch {

	private static int min_word_size = 999999999;
	private char[][] puzzlegrid;
	private List<String> words;
	private HashMap<String, WordResult> result = new HashMap<String, WordResult>();

	public char[][] getPuzzlegrid() {
		return puzzlegrid;
	}

	public HashMap<String, WordResult> getResult() {
		return result;
	}

	public void printPuzzlegrid() {

		System.out.println("---GRID---");
		int rows = puzzlegrid.length;
		int cols = puzzlegrid[0].length;

		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < cols; j++) {
				System.out.print(puzzlegrid[i][j]);
			}
			System.out.println();
		}

	}

	/**
	 * 
	 */

	public void printResult() {
		System.out.println();
		System.out.println("Printing Puzzle Solution");
		System.out.println("Found " + result.size() + " Words");
		for (String key : result.keySet()) {
			System.out.println(result.get(key).getWord() + " " + "at (" + result.get(key).getAt_x() + " "
					+ result.get(key).getAt_y() + ") " + result.get(key).getDirection());

		}

	}
/**
 * 
 * @param fileName
 * @return
 */
	private List<String> readFile(String fileName) {

		List<String> filelist = new ArrayList<String>();

		try {

			InputStream in = WordSearch.class.getResourceAsStream(fileName);
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(in));
			// StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader1.readLine()) != null) {
				filelist.add(line.toUpperCase().replaceAll("\\s", ""));
				// stringBuffer.append(line);
				// stringBuffer.append("\n");
			}
		} catch (Exception e) {
			//
			System.out.println("Error Reading File");
			System.exit(1);

		}

		return filelist;
	}

	/**
	 * Search DDL n DDR
	 * 
	 * @param i
	 * @param j
	 * @param columns
	 * @param rows
	 */

	private void searchLefttDiagonal(int i, int j, int columns, int rows) {
		// TODO Auto-generated method stub

		StringBuilder currentWord = new StringBuilder();
		// StringBuilder currentWordReversed= new StringBuilder();

		int x_start = i;
		int y_start = j;
		// for (int l = 0; l <diagLimit; l++)
		while (i < rows && j >= 0) {

			// check Left to Right
			currentWord = currentWord.append(puzzlegrid[i][j]);
			if (currentWord.length() >= min_word_size) {

				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {

					result.put(currentWord.toString(), new WordResult(currentWord.toString(), x_start, y_start, Direction.DDL.direction()));
					// result.put(currentWord.toString(), "DDL");
				}
				// check Right To Left
				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, Direction.DUR.direction()));
					// result.put(currentWord.toString(), "DUR");
				}
				currentWord.reverse();
			}

			i++;
			j--;
		}

	}

	/**
	 * Method to move right in the grid for current row. add each character as
	 * you move and check if it exists in the word to find
	 * 
	 * @param i
	 * @param j
	 * @param word
	 * @param columns
	 * @return
	 */
	private void searchRightandLeft(int i, int j, int columns) {

		StringBuilder currentWord = new StringBuilder();
		// StringBuilder currentWordReversed= new StringBuilder();

		for (int l = j; l < columns; l++) {
			// check Left to Right
			currentWord = currentWord.append(puzzlegrid[i][l]);
			if (currentWord.length() >= min_word_size) {

				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, Direction.LR.direction()));
					// result.put(currentWord.toString(), "LR");
				}
				// check Right To Left
				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, l, Direction.RL.direction()));
					// result.put(currentWord.toString(), "RL");
				}
				currentWord.reverse();
			}

		}

	}

	/**
	 * Look DDR and DUL
	 * 
	 * @param i
	 * @param j
	 * @param columns
	 * @param rows
	 */
	
	private void searchRightDiagonal(int i, int j, int columns, int rows) {
		// TODO Auto-generated method stub

		StringBuilder currentWord = new StringBuilder();
		// StringBuilder currentWordReversed= new StringBuilder();
		int x_start = i;
		int y_start = j;

		// for (int l = 0; l <diagLimit; l++)
		while (i < rows && j < columns) {

			currentWord = currentWord.append(puzzlegrid[i][j]);
			if (currentWord.length() >= min_word_size) {

				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {

					result.put(currentWord.toString(), new WordResult(currentWord.toString(), x_start, y_start, Direction.DDR.direction()));
				}
				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {

					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, Direction.DUL.direction()));
				}
				currentWord.reverse();
			}

			i++;
			j++;
		}

	}

	private void searchUpandDown(int i, int j, int maxrows) {
		// TODO Auto-generated method stub

		StringBuilder currentWord = new StringBuilder();
		// StringBuilder currentWordReversed= new StringBuilder();

		for (int l = i; l < maxrows; l++) {
			// check Left to Right
			currentWord = currentWord.append(puzzlegrid[l][j]);
			if (currentWord.length() >= min_word_size) {

				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, Direction.D.direction()));
				}

				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), l, j, Direction.U.direction()));
				}
				currentWord.reverse();
			}

		}

	}

	/**
	 * 
	 * Load the puzzle file
	 * 
	 * @Return char[][]
	 * 
	 */
	public char[][] setPuzzlegrid(String filename) {

		List<String> filelist;// = new ArrayList<String>();
		char[][] puzzle;

		try {
			filelist = readFile(filename);
			int rows = filelist.size();
			int columns = filelist.get(0).length();
			puzzle = new char[rows][columns];
			for (int i = 0; i < filelist.size(); i++) {
				puzzle[i] = filelist.get(i).toCharArray();
			}
			System.out.println(rows + " X " + columns);
			this.puzzlegrid = puzzle;

		} catch (Exception e) {
			System.err.println("Error in Loading Puzzle grid" + e.getMessage());
			e.printStackTrace();
		}

		return this.puzzlegrid;
	}

	/**
	 * 
	 * @param result
	 */
	
	public void setResult(HashMap<String, WordResult> result) {
		this.result = result;
	}

	/**
	 * setter method to load the words to be found in the grid
	 * 
	 * @return
	 */
	public List<String> setWords(String filename) {

		List<String> filelist;
		filelist = readFile(filename);
		this.words = filelist;
		Collections.sort(words);// sort since Binarysearch will be used
		for (int i = 0; i < filelist.size(); i++) {
			if (filelist.get(i).length() < min_word_size)
				min_word_size = filelist.get(i).length();

		}

		return (words);
	}

	/**
	 * 
	 */

	public void solvePuzzle() {
		int rows = puzzlegrid.length;
		int columns = puzzlegrid[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				searchRightandLeft(i, j, columns);
				searchUpandDown(i, j, rows);
				searchRightDiagonal(i, j, columns, rows);
				searchLefttDiagonal(i, j, columns, rows);
			}

		}

	}

}
