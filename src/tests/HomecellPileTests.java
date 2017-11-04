package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import code.FreecellGame;
import code.FreecellPile;
import code.BakersDozenGame;
import code.Card;
import code.Deck;
import code.HomecellPile;

public class HomecellPileTests {
	Deck newDeck = new Deck();
	Card[] validSetup = {newDeck.getCard(0), newDeck.getCard(1), newDeck.getCard(2)};
	Card[] invalidSetup = {newDeck.getCard(0), newDeck.getCard(2)};
	Card[] fullImplementation = new Card[13];

	@Test 
	public void HomecellZeroCardsFreecell() {
		FreecellGame newGame = new FreecellGame();
		// Each pile initially holds 0 cards
		for (int i = 0; i < 4; i++) {
			assertEquals(0, newGame.getNumberOfCards(i, 2));
		}
	}

	@Test
	public void HomecellZeroCardsBakersDozen() {
		BakersDozenGame newGame = new BakersDozenGame();
		// Each pile initially holds 0 cards
		for (int i = 0; i < 4; i++) {
			assertEquals(0, newGame.getNumberOfCards(i, 2));	
		}
	}

	@Test
	public void HomecellAddTestFreecell() {
		FreecellGame freecellGame = new FreecellGame();
		// Add valid Cards --> true
		for (Card validTest : validSetup) {
			assertEquals(true, freecellGame.addCard(0, 2, validTest));
		}
		// Add Invalid cards --> false
		for (Card invalidTest : invalidSetup) {
			assertEquals(false, freecellGame.addCard(0, 2, invalidTest));
		}
		
		// Add a card that is not an ace --> false
		assertEquals(false, freecellGame.addCard(1, 2, newDeck.getCard(4)));
	
		// Full Implementation of Ranks
		for (int i = 0; i < 13; i++) {
			fullImplementation[i] = newDeck.getCard(i);
		}
		
		// Add full implementation from Ace upwards --> true
		for (Card fullImplementationTest : fullImplementation) {
			assertEquals(true, freecellGame.addCard(2, 2, fullImplementationTest));
		}
	}

	@Test
	public void HomecellAddTestBakersDozen() {
		BakersDozenGame bakersDozenGame = new BakersDozenGame();
		// Add valid cards --> true
		for (Card validTest : validSetup) {
			assertEquals(true, bakersDozenGame.addCard(0, 2, validTest));
		}
		// Add invalid cards --> false
		for (Card invalidTest : invalidSetup) {
			assertEquals(false, bakersDozenGame.addCard(0, 2, invalidTest));
			}
		// Add card that is not an ace to empty pile --> false
		assertEquals(false, bakersDozenGame.addCard(1, 2, newDeck.getCard(4)));
	
		// Full Implementation of Ranks
		for (int i = 0; i < 13; i++) {
			fullImplementation[i] = newDeck.getCard(i);
		}
		
		// Add full implementation of ranks --> true
		for (Card fullImplementationTest : fullImplementation) {
			assertEquals(true, bakersDozenGame.addCard(2, 2, fullImplementationTest));
		}
	}
	
	@Test
	public void HomecellRemoveCardTestFreecell() {
		// Always False on each pile
		FreecellGame freecellGame = new FreecellGame();
		for (int i = 0; i < 4; i++) {
			// Add ace
			freecellGame.addCard(i, 2, newDeck.getCard(0));
			// Check for successful add
			assertEquals(1, freecellGame.getNumberOfCards(i, 2));
			// Attempt to remove --> false
			assertEquals(false, freecellGame.removeCard(i, 2));
		}
	}
	
	@Test
	public void HomecellRemoveCardTestBakersDozen() {
		// Always False on each pile
		BakersDozenGame bakersDozenGame = new BakersDozenGame();
		for (int i = 0; i < 4; i++) {
			// Add ace
			bakersDozenGame.addCard(i, 2, newDeck.getCard(0));
			// Check for successful add
			assertEquals(1, bakersDozenGame.getNumberOfCards(i, 2));
			// Attempt to remove --> false
			assertEquals(false, bakersDozenGame.removeCard(i, 2));
		}
	}
	
	@Test
	public void HomecellUpdateTopCardFreecell() {
		// Add card and Update topCard on each pile
		for (int i = 0; i < 4; i++) {
			FreecellGame freecellGame = new FreecellGame();
			assertEquals(0, freecellGame.getNumberOfCards(i, 2));
			freecellGame.addCard(i, 2, newDeck.getCard(0));
			assertEquals(1, freecellGame.getNumberOfCards(i, 2));
			assertEquals(newDeck.getCard(0).getRank(), freecellGame.getTopCard(i, 2).getRank());
			assertEquals(newDeck.getCard(0).getSuit(), freecellGame.getTopCard(i, 2).getSuit());
			
			// Full Implementation of Ranks
			for (int c = 0; c < 13; c++) {
				fullImplementation[c] = newDeck.getCard(c);
			}
			
			//Full Implementation Test
			for (int b = 0; b < fullImplementation.length; b++) {
				freecellGame.addCard(i, 2, newDeck.getCard(b));
				assertEquals(b+1, freecellGame.getNumberOfCards(i, 2));
				assertEquals(newDeck.getCard(b).getRank(), freecellGame.getTopCard(i, 2).getRank());
				assertEquals(newDeck.getCard(b).getSuit(), freecellGame.getTopCard(i, 2).getSuit());
			}
		}	
	}
	
	@Test
	public void HomecellUpdateTopCardBakersDozen() {
		// Add card and update topCard on each pile
		for (int i = 0; i < 4; i++) {
			BakersDozenGame bakersDozenGame = new BakersDozenGame();
			assertEquals(0, bakersDozenGame.getNumberOfCards(i, 2));
			bakersDozenGame.addCard(i, 2, newDeck.getCard(0));
			assertEquals(1, bakersDozenGame.getNumberOfCards(i, 2));
			assertEquals(newDeck.getCard(0).getRank(), bakersDozenGame.getTopCard(i, 2).getRank());
			assertEquals(newDeck.getCard(0).getSuit(), bakersDozenGame.getTopCard(i, 2).getSuit());
			
			// Full Implementation of Ranks
			for (int c = 0; c < 13; c++) {
				fullImplementation[c] = newDeck.getCard(c);
			}
			
			//Full Implementation Test
			for (int c = 0; c < fullImplementation.length; c++) {
				bakersDozenGame.addCard(i, 2, newDeck.getCard(c));
				assertEquals(c+1, bakersDozenGame.getNumberOfCards(i, 2));
				assertEquals(newDeck.getCard(c).getRank(), bakersDozenGame.getTopCard(i, 2).getRank());
				assertEquals(newDeck.getCard(c).getSuit(), bakersDozenGame.getTopCard(i, 2).getSuit());
			}
		}
	}
}

