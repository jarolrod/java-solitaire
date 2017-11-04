package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.BakersDozenGame;

public class BakersDozenGameClassTest {
	BakersDozenGame newGame = new BakersDozenGame();
	
	@Test
	public void BakersDozen13TableauPiles() {
		assertEquals(13, newGame.getTableauArray().size());
	}
	
	@Test 
	public void BakersDozen4HomecellPiles() {
		assertEquals(4, newGame.getHomecellArray().size());
	}
	
	@Test
	public void BakersDozen0FreecellPiles() {
		assertEquals(0, newGame.getFreecellArray().size());
	}

}
