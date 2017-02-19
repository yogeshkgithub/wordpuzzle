package com.yogesh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WordSearch {

	private char[][] puzzlegrid;
	private List<String> words;
	private HashMap<String, WordResult> result = new HashMap<String, WordResult>();
	
	private static int min_word_size = 999999999;

	/*private class WordResult {

		String word;
		int at_x;
		int at_y;
		String direction;

		WordResult(String word, int x, int y, String direction) {
			this.word = word;
			this.at_x = x;
			this.at_y = y;
			this.direction = direction;

		}

	}

*/	
	
	
	private List<String> readFile(String fileName) {

		List<String> filelist = new ArrayList<String>();

		try {
			
			InputStream in = WordSearch.class.getResourceAsStream(fileName);
			//InputStream in = WordSearch.class.getClass().getClassLoader().getResourceAsStream(fileName);
			BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(in));
			//BufferedReader bufferedReader1 = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader1.readLine()) != null) {
				filelist.add(line.toUpperCase().replaceAll("\\s", ""));
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
		} catch (Exception e) {
			//
			System.out.println("Error Reading File");
			System.exit(1);
			
		}

		return filelist;
	}

	public HashMap<String, WordResult> getResult() {
		return result;
	}

	public void setResult(HashMap<String, WordResult> result) {
		this.result = result;
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

	public char[][] getPuzzlegrid() {
		return puzzlegrid;
	}

	public void printPuzzlegrid() {

		System.out.println("---GRID---");

		for (int i = 0; i < puzzlegrid[0].length; i++) {

			for (int j = 0; j < puzzlegrid.length; j++) {
				System.out.print(puzzlegrid[i][j]);

			}
			System.out.println();
		}

	}

	public void printResult() {
		System.out.println();
		System.out.println("Printing Puzzle Solution");
		System.out.println("Found " + result.size() + " Words");
		for (String key : result.keySet()) {
			System.out.println(result.get(key).word + " " + "at (" + result.get(key).at_x + " " + result.get(key).at_y
					+ ") " + result.get(key).direction);

		}

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
	 * 
	 * 
	 * 
	 */

	public void solvePuzzle() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

		StringBuilder currentWord = new StringBuilder();
		// StringBuilder currentWordReversed= new StringBuilder();

		for (int l = j; l < columns; l++) {
			// check Left to Right
			currentWord = currentWord.append(puzzlegrid[i][l]);
			if (currentWord.length() >= min_word_size) {

				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, "LR"));
					// result.put(currentWord.toString(), "LR");
				}
				// check Right To Left
				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, l, "RL"));
					// result.put(currentWord.toString(), "RL");
				}
				currentWord.reverse();
			}

		}

	}

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

					// result.put(currentWord.toString(), "DDR");
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), x_start, y_start, "DDR"));
				}

				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					// result.put(currentWord.toString(), "DUL");
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, "DUL"));
				}
				currentWord.reverse();
			}

			i++;
			j++;
		}

	}

	/**
	 * Search DDL  n DDR
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

					result.put(currentWord.toString(), new WordResult(currentWord.toString(), x_start, y_start, "DDL"));
					// result.put(currentWord.toString(), "DDL");
				}
				// check Right To Left
				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, "DUR"));
					// result.put(currentWord.toString(), "DUR");
				}
				currentWord.reverse();
			}

			i++;
			j--;
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

					result.put(currentWord.toString(), new WordResult(currentWord.toString(), i, j, "D"));
					// result.put(currentWord.toString(), "D");
				}
				// check Right To Left
				currentWord.reverse();
				if (Collections.binarySearch(words, currentWord.toString()) >= 0) {
					result.put(currentWord.toString(), new WordResult(currentWord.toString(), l, j, "U"));
					// result.put(currentWord.toString(), "U");
				}
				currentWord.reverse();
			}

		}

	}

}
