package com.yogesh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DirectionTest {
	
	@Test
	public void testDirection() {
		
		assertEquals(true, Direction.D.direction()=="D");
		assertEquals(false, Direction.D.direction()=="U");
		System.out.println("Direction Verified");


}
}
