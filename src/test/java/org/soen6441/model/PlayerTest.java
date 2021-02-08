package org.soen6441.model;
import junit.framework.TestCase;


/**
 * Unit test for Player CLass.
 */
public class PlayerTest extends TestCase
{
/*
 * Test Case for Player Name
 */
	public void testPlayername() {
		Player p1 = new Player();
		
		assertEquals(p1.Playername, "Alpha");
		
		
	}
}
