package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.FreecellGame;

public class FreecellGameClassTest {
	FreecellGame newGame = new FreecellGame();
	
	@Test
	public void Freecell8TableauPiles() {
		assertEquals(8, newGame.getTableauArray().size());
	}
	
	@Test
	public void Freecell4HomcellPiles() {
		assertEquals(4, newGame.getHomecellArray().size());
	}
	
	@Test
	public void Freecell4FreecellPiles() {
		assertEquals(4, newGame.getFreecellArray().size());
	}
}
