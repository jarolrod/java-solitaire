package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import code.FreecellGame;
import code.Card;
import code.Deck;
import code.FreecellPile;

public class FreecellPileTests {
	Deck newDeck = new Deck();
	
	@Test
	public void FreecellZeroCards() {
		FreecellGame newGame = new FreecellGame();
		// Check each pile is initialize with zero cards
		for (int i = 0; i < 4; i++) {
			assertEquals(0, newGame.getNumberOfCards(i, 3));
		}
	}
	
	@Test
	public void  FreecellAddTest() {
		FreecellGame newGame = new FreecellGame();
		
		// Add random card to each empty pile --> true
		for (int i = 0; i < 4; i++) {
			Random randNumber = new Random();
			int randomIndex = randNumber.nextInt(51)+0;
			Card randomCard = newDeck.getCard(randomIndex);

			// Attempt to add --> true
			assertTrue(newGame.addCard(i, 3, randomCard));
		}
		
		// Add random card to full pile --> false
		for (int i = 0; i < 4; i++) {
			FreecellGame newGame2 = new FreecellGame();
			Random randNumber = new Random();
			int randIndex1 = randNumber.nextInt(51)+0;
			int randIndex2 = randNumber.nextInt(51)+0;
			Card randomCard1 = newDeck.getCard(randIndex1);
			Card randomCard2 = newDeck.getCard(randIndex2);
			int numCardsBeforeAdd = newGame2.getNumberOfCards(i, 3);
			
			// Add first card --> true
			newGame2.addCard(i, 3, randomCard1);
			assertEquals(numCardsBeforeAdd+1, newGame2.getNumberOfCards(i, 3));
			
			// Attempt to add another card --> false
			assertFalse(newGame.addCard(i, 3, randomCard2));
		}
	}
	
	@Test
	public void FreecellRemoveTopCard() {
		FreecellGame newGame = new FreecellGame();
		for (int i = 0; i < 8; i++) {
			// Attempt to remove from empty pile --> false
			if(i < 4) {
				assertFalse(newGame.removeCard(i, 3));
				assertNull(newGame.getTopCard(i, 3));
			
			// Add card then remove --> true
			} else {
				int realIndex = i - 4;
				Random randNumber = new Random();
				int randomIndex = randNumber.nextInt(51)+0;
				Card randomCard = newDeck.getCard(randomIndex);
				newGame.addCard(realIndex, 3, randomCard);
				
				// Check for add + update topCard
				assertEquals(1, newGame.getNumberOfCards(realIndex, 3));
				assertFalse(newGame.getTopCard(realIndex, 3) == null);
				
				// Remove card --> true
				newGame.removeCard(realIndex, 3);
				assertEquals(0, newGame.getNumberOfCards(realIndex, 3));
				assertNull(newGame.getTopCard(realIndex, 3));
			}
		}	
	}
	
	@Test
	public void FreecellAddTopCard() {
		FreecellGame newGame = new FreecellGame();
		// Add random card and check for update topCard
		for (int i = 0; i < 4; i++) {
			Random randNumber = new Random();
			int randomIndex = randNumber.nextInt(51)+0;
			Card randomCard = newDeck.getCard(randomIndex);
			
			// Check for empty before add and null topCard
			assertEquals(0, newGame.getNumberOfCards(i, 3));
			assertNull(newGame.getTopCard(i, 3));
			
			// Add card, check for increase, check update topCard
			newGame.addCard(i, 3, randomCard);
			assertEquals(1, newGame.getNumberOfCards(i, 3));
			assertFalse(newGame.getTopCard(i, 3) == null);
		}
	}
	

}
