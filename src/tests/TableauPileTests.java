package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import code.FreecellGame;
import code.BakersDozenGame;
import code.Card;
import code.Deck;
import code.TableauPile;

public class TableauPileTests {
	Deck newDeck = new Deck();
	
	@Test
	public void TableauPileInitialFreecell() {
		FreecellGame newGame = new FreecellGame();
		for (int i = 0; i < 8; i++) {
			// First 4 tableau piles == 7 cards
			if (i < 4) {
				assertEquals(7, newGame.getNumberOfCards(i, 1));
			// Next 4 tableau piles == 6 cards
			} else {
				assertEquals(6, newGame.getNumberOfCards(i, 1));
			}
		}
	}
	
	@Test
	public void TableauPileInitialBakersDozen() {
		BakersDozenGame newGame = new BakersDozenGame();
		ArrayList<TableauPile> tableauPiles = newGame.getTableauArray();
		// All tableau piles == 4 cards
		for (TableauPile value : tableauPiles) {
			assertEquals(4, value.getNumberOfCards());
		}
	}
	
	@Test
	public void TableauAddTestFreecell() {
		FreecellGame freecellGame = new FreecellGame();
		
		// Add valid card on each tableau pile
		for (int i = 0; i < 8; i++) {
			Card topCard = freecellGame.getTopCard(i, 1);
			String color = topCard.getColor();
			int rank = topCard.getRank();
			String Suit = "a";
			if (color.equals("red")) {Suit = "club";} 
			else if (color.equals("black")) {Suit = "diamond";} 
			Card validCard = new Card(Suit, rank-1);
			assertEquals(true, freecellGame.addCard(i, 1, validCard));
			
			// Check for valid topCard change
			assertEquals(Suit, freecellGame.getTopCard(i, 1).getSuit());
			assertEquals(rank-1, freecellGame.getTopCard(i, 1).getRank());
		}
		
		// Add invalid card on each tableau pile: Same suit
		for (int i = 0; i < 8; i++) {
			Card topCard = freecellGame.getTopCard(i, 1);
			String color = topCard.getColor();
			String suit = topCard.getSuit();
			int rank = topCard.getRank();
			Card invalidCardSameSuit = new Card(suit, rank-1);
			assertEquals(false, freecellGame.addCard(i, 1, invalidCardSameSuit));
		}
		
		// Add invalid card on each tableau pile: Same Rank, Different Suit
		for (int i = 0; i < 8; i++) {
			Card topCard = freecellGame.getTopCard(i, 1);
			String color = topCard.getColor();
			int rank = topCard.getRank();
			String suit = "b";
			if (color.equals("red") ) {suit = "club";} 
			else if (color.equals("black")) {suit = "diamond";} 
			Card invalidCardSameRank = new Card(suit, rank);
			assertEquals(false, freecellGame.addCard(i, 1, invalidCardSameRank));
		}
		
//		 Empty each Tableau Pile and Add a Random Card
		 
		for (int i = 0; i < 8; i++) {
			int startIndex = freecellGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				freecellGame.removeCard(i, 1);
			}
			// Empty check, null topCard
			assertEquals(0, freecellGame.getNumberOfCards(i, 1));
			assertEquals(null, freecellGame.getTopCard(i, 1));
		
			// Add Random Card
			newDeck.deckShuffle();
			Random randomNumberGenerator = new Random();
			int randomIndex = randomNumberGenerator.nextInt(51) + 0;
			Card randomCard = newDeck.getCard(randomIndex);
			String randomCardSuit = randomCard.getSuit();
			int randomRank = randomCard.getRank();
			freecellGame.addCard(i, 1, randomCard);
			
			// Check for valid add and valid update to topCard
			assertEquals(1, freecellGame.getNumberOfCards(i, 1));
			assertEquals(randomCardSuit, freecellGame.getTopCard(i, 1).getSuit());
			assertEquals(randomRank, freecellGame.getTopCard(i, 1).getRank());
		}
	}
	
	@Test
	public void TableauAddTestBakersDozen() {
		BakersDozenGame newGame = new BakersDozenGame();
		
		// Add valid card on each tableau pile: Same suit, rank == oneLess
		for (int i = 0; i < 13; i++) {
			Card topCard = newGame.getTopCard(i, 1);
			int rank = topCard.getRank();
			String suit = topCard.getSuit();
			Card validCardSameSuit = new Card(suit, rank-1);
			int numCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			newGame.addCard(i, 1, validCardSameSuit);
			
			// Check for valid add and valid update topCard
			assertEquals(numCardsBeforeAdd+1, newGame.getNumberOfCards(i, 1));
			assertEquals(rank-1, newGame.getTopCard(i, 1).getRank());
			assertEquals(suit, newGame.getTopCard(i, 1).getSuit());
		}
		
		// Add valid card on each tableau pile: different suit, rank == oneLess
		for (int i = 0; i < 13; i++) {
			Card topCard = newGame.getTopCard(i, 1);
			int rank = topCard.getRank();
			String suit = topCard.getSuit();
			String color = topCard.getColor();
			String newSuit = "a";
			if (color.equals("black")) {newSuit = "diamond";}
			else if (color.equals("red")) {newSuit = "club";}
			Card newCard = new Card(newSuit, rank-1);
			int numCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			newGame.addCard(i, 1, newCard);
			
			// Check for valid add and valid update topCard
			assertEquals(numCardsBeforeAdd+1, newGame.getNumberOfCards(i, 1));
			assertEquals(rank-1, newGame.getTopCard(i, 1).getRank());
			assertEquals(newSuit, newGame.getTopCard(i, 1).getSuit());
			
		}
		
		// Add invalid card on each tableau pile: different suit, same rank
		for (int i = 0; i < 13; i++) {
			Card topCard = newGame.getTopCard(i, 1);
			int rank = topCard.getRank();
			String suit = topCard.getSuit();
			String color = topCard.getColor();
			Card newCard = null;
			if (color.equals("black")) {
				if (suit.equals("club")) {
				newCard = new Card("spade", rank);
				} else if(suit.equals("spade")) { 
					newCard = new Card("club", rank);
				}
			} else if (color.equals("red")) {
				if (suit.equals("diamond")) {
					newCard = new Card("heart", rank);
				} else if (suit.equals("heart")) {
					newCard = new Card("diamond", rank);
				}
			}
			int numCardsBeforeAddAttempt = newGame.getNumberOfCards(i, 1);
			assertEquals(false, newGame.addCard(i, 1, newCard));
			assertEquals(numCardsBeforeAddAttempt, newGame.getNumberOfCards(i, 1));	
		}
		
		// Add invalid card on each tableau pile: rank+=1
		for (int i = 0; i < 13; i++) {
			Card topCard = newGame.getTopCard(i, 1);
			int rank = topCard.getRank();
			String suit = topCard.getSuit();
			Card newCard = new Card(suit, rank+=1);
			int numCardsBeforeAddAttempt = newGame.getNumberOfCards(i, 1);
			assertEquals(false, newGame.addCard(i, 1, newCard));
			assertEquals(numCardsBeforeAddAttempt, newGame.getNumberOfCards(i, 1));
		}
		
		// Empty each pile and attempt to add a card
		for (int i = 0; i < 13; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			// Empty Check, null topCard;
			assertEquals(0, newGame.getNumberOfCards(i, 1));
			assertEquals(null, newGame.getTopCard(i, 1));
			
			// Add Random Card -- result = false
			newDeck.deckShuffle();
			Random randomNum = new Random();
			int randomIndex = randomNum.nextInt(51) + 0;
			Card newCard = newDeck.getCard(randomIndex);
			assertEquals(false, newGame.addCard(i, 1, newCard));	
		}	
	}
	
	@Test
	public void TableauRemoveTopCardFreecell() {
		FreecellGame newGame = new FreecellGame();
		
		// Valid removal, pile is not empty
		for (int i = 0; i < 8; i++) {
			assertEquals(true, newGame.removeCard(i, 1));
		}
		
		// Empty Pile, then attempt removal --> false
		for (int i = 0; i < 8; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			// Empty check
			assertEquals(0, newGame.getNumberOfCards(i, 1));
			
			// Attempt removal --> false
			assertEquals(false, newGame.removeCard(i, 1));
		}	
	}
	
	@Test
	public void TableauRemoveTopCardBakersDozen() {
		BakersDozenGame newGame = new BakersDozenGame();
		
		// Valid Removal, pile is not empty
		for (int i = 0; i < 13; i++) {
			assertEquals(true, newGame.removeCard(i, 1));
		}
		
		// Empty Pile, then attempt Removal --> false
		for (int i = 0; i < 13; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			// Empty Check
			assertEquals(0, newGame.getNumberOfCards(i, 1));
			
			// Attempt removal --> false
			assertEquals(false, newGame.removeCard(i, 1));
		}
	}
	
	@Test
	public void TableauAddAndUpdateTopCardFreecell() {
		FreecellGame newGame = new FreecellGame();
		
		// Add valid card: check numCards++ and update topCard
		for (int i = 0; i < 8; i++) {
			int numberOfCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			Card initialTopCard = newGame.getTopCard(i, 1);
			int initialTopCardRank = initialTopCard.getRank();
			String initialTopCardSuit = initialTopCard.getSuit();
			String initialTopCardColor = initialTopCard.getColor();
			String newTopCardSuit = null;
			if (initialTopCardColor.equals("black")) {newTopCardSuit="diamond";}
			else if(initialTopCardColor.equals("red")) {newTopCardSuit="club";}
			Card newTopCard = new Card(newTopCardSuit, initialTopCardRank-1);
			
			// Check for valid add
			assertEquals(true, newGame.addCard(i, 1, newTopCard));
			newGame.addCard(i, 1, newTopCard);
			
			// Check for Increase in numOfCards
			assertEquals(numberOfCardsBeforeAdd+1, newGame.getNumberOfCards(i, 1));
			
			//Check for updated topCard
			assertEquals(newTopCardSuit, newGame.getTopCard(i, 1).getSuit());
			assertEquals(initialTopCardRank-1, newGame.getTopCard(i, 1).getRank());	
		}
		
		// Add card to emptyPile --> true
		for (int i = 0; i < 8; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			// Empty Check, numCards == 0 
			assertEquals(0, newGame.getNumberOfCards(i, 1));
			
			// Check null topCard
			assertEquals(null, newGame.getTopCard(i, 1));
			
			// Add random card to empty pile--> true
			newDeck.deckShuffle();
			Random randNum = new Random();
			int randomIndex = randNum.nextInt(51)+0;
			Card randCard = newDeck.getCard(randomIndex);
			String randCardSuit = randCard.getSuit();
			int randCardRank = randCard.getRank();
			
			// Check addTest --> true
			assertEquals(true, newGame.addCard(i, 1, randCard));
			
			// Check numCards == 1
			assertEquals(1, newGame.getNumberOfCards(i, 1));
			
			// Check new TopCard
			assertEquals(randCardSuit, newGame.getTopCard(i, 1).getSuit());
			assertEquals(randCardRank, newGame.getTopCard(i, 1).getRank());
		}
		
		// Add invalid card: same suit, rank-1
		for (int i = 0; i < 8; i++) {
			int numCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			Card initTopCard = newGame.getTopCard(i, 1);
			int initTopCardRank = initTopCard.getRank();
			String initTopCardSuit = initTopCard.getSuit();
			Card newTopCard = new Card(initTopCardSuit, initTopCardRank-1);
			
			// Attempt to add --> false
			assertEquals(false, newGame.addCard(i, 1, newTopCard));
			assertEquals(numCardsBeforeAdd, newGame.getNumberOfCards(i, 1));
			
			// No Change to topCard
			assertEquals(initTopCardSuit, newGame.getTopCard(i, 1).getSuit());
			assertEquals(initTopCardRank, newGame.getTopCard(i, 1).getRank());
		}
		
		// Add invalid card: different suit, same rank
		for (int i = 0; i < 8; i++) {
			int numCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			Card initTopCard = newGame.getTopCard(i, 1);
			int initTopCardRank = initTopCard.getRank();
			String initTopCardSuit = initTopCard.getSuit();
			String initTopCardColor = initTopCard.getColor();
			String newTopCardSuit = null;
			if(initTopCardColor.equals("black")) {newTopCardSuit="diamond";}
			else if(initTopCardColor.equals("red")) {newTopCardSuit="club";}
			Card newTopCard = new Card(newTopCardSuit, initTopCardRank);
			
			// Attempt to add --> false
			assertEquals(false, newGame.addCard(i, 1, newTopCard));
			assertEquals(numCardsBeforeAdd, newGame.getNumberOfCards(i, 1));
			
			// No Change to topCard
			assertEquals(initTopCardSuit, newGame.getTopCard(i, 1).getSuit());
			assertEquals(initTopCardRank, newGame.getTopCard(i, 1).getRank());
		}
		
		// Add invalid card: different suit, rank+1
		for (int i = 0; i < 8; i++) {
			int numCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			Card initTopCard = newGame.getTopCard(i, 1);
			int initTopCardRank = initTopCard.getRank();
			String initTopCardSuit = initTopCard.getSuit();
			String initTopCardColor = initTopCard.getColor();
			String newTopCardSuit = null;
			if(initTopCardColor.equals("black")) {newTopCardSuit="diamond";}
			else if(initTopCardColor.equals("red")) {newTopCardSuit="club";}
			Card newTopCard = new Card(newTopCardSuit, initTopCardRank+1);
			
			// Attempt to add --> false
			assertEquals(false, newGame.addCard(i, 1, newTopCard));
			assertEquals(numCardsBeforeAdd, newGame.getNumberOfCards(i, 1));
			
			// No Change to topCard
			assertEquals(initTopCardSuit, newGame.getTopCard(i, 1).getSuit());
			assertEquals(initTopCardRank, newGame.getTopCard(i, 1).getRank());
		}	
	}
	
	@Test
	public void TableauAddAndUpdateTopCardBakersDozen() {
		BakersDozenGame newGame = new BakersDozenGame();
		
		// Add valid card: check numCard++, update topCard
		for (int i = 0; i < 13; i++) {
			int numCardsBeforeAdd = newGame.getNumberOfCards(i, 1);
			Card initTopCard = newGame.getTopCard(i, 1);
			int initRank = initTopCard.getRank();
			Card newTopCard = new Card(initTopCard.getSuit(), initRank-1);
			
			// addTest --> true
			assertEquals(true, newGame.addCard(i, 1, newTopCard));
			newGame.addCard(i, 1, newTopCard);
			
			// Check numCard++
			assertEquals(numCardsBeforeAdd+1, newGame.getNumberOfCards(i, 1));
			
			// Check update topCard
			assertEquals(initTopCard.getSuit(), newGame.getTopCard(i, 1).getSuit());
			assertEquals(initRank-1, newGame.getTopCard(i, 1).getRank());
		}
		
		// Attempt add to empty pile --> false;
		for (int i = 0; i < 13; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			
			// Check empty, topCard == null
			assertEquals(0, newGame.getNumberOfCards(i, 1));
			assertEquals(null, newGame.getTopCard(i, 1));
			
			// Attempt to add random card --> false
			Random randNum = new Random();
			int randomIndex = randNum.nextInt(51)+0;
			newDeck.deckShuffle();
			Card randomCard = newDeck.getCard(randomIndex);
			assertEquals(false, newGame.addCard(i, 1, randomCard));	
		}
	}
	
	@Test
	public void TableauRemoveCardFreecell() {
		FreecellGame newGame = new FreecellGame();
		
		// Remove single card from each pile
		for (int i = 0; i < 8; i++) {
			int numCardsBeforeRemove = newGame.getNumberOfCards(i, 1);
			Card initTopCard = newGame.getTopCard(i, 1);
			String initTopCardSuit = initTopCard.getSuit();
			int initTopCardRank = initTopCard.getRank();
			newGame.removeCard(i, 1);

			// Check for decrease
			assertEquals(numCardsBeforeRemove-1, newGame.getNumberOfCards(i, 1));
			
			// Check for topCard change
			assertEquals(false, (newGame.getTopCard(i, 1).getSuit().equals(initTopCardSuit)) &&
								(newGame.getTopCard(i, 1).getRank() == initTopCardRank));
		}
		
		// Remove all cards from each pile: check topCard changes
		// with each removal
		for (int i = 0; i < 8; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				if(b == 1) {
					int numCardBeforeRemove = newGame.getNumberOfCards(i, 1);
					Card topCard = newGame.getTopCard(i, 1);
					String initTopCardSuit = topCard.getSuit();
					int initTopCardRank = topCard.getRank();
					newGame.removeCard(i, 1);
					
					// Check for decrease
					assertEquals(numCardBeforeRemove-1, newGame.getNumberOfCards(i, 1));
					
					// Null topCard
					assertEquals(null, newGame.getTopCard(i, 1));	
				} else {
					int numCardBeforeRemove = newGame.getNumberOfCards(i, 1);
					Card topCard = newGame.getTopCard(i, 1);
					String initTopCardSuit = topCard.getSuit();
					int initTopCardRank = topCard.getRank();
					newGame.removeCard(i, 1);
					
					// Check for decrease
					assertEquals(numCardBeforeRemove-1, newGame.getNumberOfCards(i, 1));
					
					// Check for topCard change
					assertEquals(false, (newGame.getTopCard(i, 1).getSuit().equals(initTopCardSuit)) && 
										(newGame.getTopCard(i, 1).getRank() == initTopCardRank));
				}
			}
		}
		
		// Attempt to remove from empty pile --> false;
		for (int i = 0; i < 8; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			// Attempt removal
			assertEquals(false, newGame.removeCard(i, 1));
			
			// Null topCard
			assertEquals(null, newGame.getTopCard(i, 1));
		}
	}
	
	@Test
	public void TableauRemoveCardBakersDozen() {
		BakersDozenGame newGame = new BakersDozenGame();
		
		// Delete single card from each pile
		for (int i = 0; i < 13; i++) {
			int numCardsBeforeRemove = newGame.getNumberOfCards(i, 1);
			Card topCard = newGame.getTopCard(i, 1);
			String initTopCardSuit = topCard.getSuit();
			int initTopCardRank = topCard.getRank();
			newGame.removeCard(i, 1);
			
			// Check for decrease
			assertEquals(numCardsBeforeRemove-1, newGame.getNumberOfCards(i, 1));
			
			// Check for topCard change
			assertEquals(false, (newGame.getTopCard(i, 1).getSuit().equals(initTopCardSuit)) && 
								(newGame.getTopCard(i, 1).getRank() == initTopCardRank));
		}
		
		// Delete all cards from each pile
		for (int i = 0; i < 13; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				if (b == 1) {
					int numCardsBeforeRemove = newGame.getNumberOfCards(i, 1);
					Card topCard = newGame.getTopCard(i, 1);
					String initTopCardSuit = topCard.getSuit();
					int initTopCardRank = topCard.getRank();
					newGame.removeCard(i, 1);
					
					// Check for decrease
					assertEquals(numCardsBeforeRemove-1, newGame.getNumberOfCards(i, 1));
					
					// null topCard
					assertEquals(null, newGame.getTopCard(i, 1));
				} else {
					int numCardsBeforeRemove = newGame.getNumberOfCards(i, 1);
					Card topCard = newGame.getTopCard(i, 1);
					String initTopCardSuit = topCard.getSuit();
					int initTopCardRank = topCard.getRank();
					newGame.removeCard(i, 1);
					
					// Check for decrease
					assertEquals(numCardsBeforeRemove-1, newGame.getNumberOfCards(i, 1));
					
					// Check for topCard change
					assertEquals(false, (newGame.getTopCard(i, 1).getSuit().equals(initTopCardSuit)) && 
							(newGame.getTopCard(i, 1).getRank() == initTopCardRank));
				}
			}
		}
		
		// Attempt to remove card from empty pile --> false
		for (int i = 0; i < 13; i++) {
			int startIndex = newGame.getNumberOfCards(i, 1);
			for (int b = startIndex; b > 0; b--) {
				newGame.removeCard(i, 1);
			}
			// Attempt removal
			assertEquals(false, newGame.removeCard(i, 1));
			
			// Null topCard
			assertEquals(null, newGame.getTopCard(i, 1));
		}
	}
}
