package com.yogesh;

 public class WordResult {

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getAt_x() {
		return at_x;
	}

	public void setAt_x(int at_x) {
		this.at_x = at_x;
	}

	public int getAt_y() {
		return at_y;
	}

	public void setAt_y(int at_y) {
		this.at_y = at_y;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

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

