package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import code.Card;
import code.Deck;

public class DeckClassTest {
	Deck gameDeck = new Deck();

	@Test
	public void Deck52Cards() {
		ArrayList<Card> deckStore = gameDeck.getStore();
		int valTest = 0;
		for (Card value : deckStore) {
			valTest++;
		}
		assertEquals(52, valTest);
	}
	
	@Test
	public void DeckUniqueSuitRank() {
		// Check for only one of each suit and rank combo
		for (int i = 0; i < 52; i++) {
			Card deckCard = gameDeck.getCard(i);
			ArrayList<Card> possibleDuplicate = new ArrayList<Card>();
			for (int b = 0; b < 52; b++) {
				Card value = gameDeck.getCard(b);
				possibleDuplicate.clear();
				if ((deckCard.getSuit().equals(value.getSuit())) 
					&& (deckCard.getRank() == value.getRank())) {
					possibleDuplicate.add(value);
					assertEquals(1, possibleDuplicate.size());
				}		
			}
			
		}		
	}
}
