package com.yogesh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordResultTest {

	@Test
	public void testWordResult() {
		WordResult wr = new WordResult("YOGESH", 10, 10, "R");
		assertEquals(true, wr.getDirection() == "R" && wr.getAt_x() == 10 && wr.getAt_y() == 10);
		System.out.println("Word Result Verified");

	}

}
