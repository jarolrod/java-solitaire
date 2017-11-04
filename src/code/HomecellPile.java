package code;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;

public class HomecellPile extends StackPane { 
	private ArrayList<Card> store = new ArrayList<Card>();
	private String gameType;
	private int numberOfCards;
	Card topCard;
	
	public HomecellPile(String gameType) {
		super();
		super.setWidth(156);
		super.setHeight(204);
		super.setPrefSize(156, 204);
		this.gameType = gameType;
		numberOfCards = 0;
			
	}
	

	public boolean removeCard() {
		return false;
	}


	public void addCard(Card newCard) {
		if (addTest(newCard)) {
			newCard.setVisible(true);
			newCard.toFront();
			newCard.setManaged(true);
			super.getChildren().add(newCard);
			store.add(newCard);
			updateTopCard();
			numberOfCards++;
		}
	}
	
	public boolean addTest(Card newCard) {
		if (gameType.equals("Aces_Up")) {
			return true;
		} else {
			if (store.isEmpty()) {
				int rank = newCard.getRank();
				if (rank == 1) return true;
			} else if (topCard.getSuit().equals(newCard.getSuit())) {
				 int topCardRank = topCard.getRank();
				 int newCardRank = newCard.getRank();
				 if ((newCardRank - topCardRank) == 1) return true;
			} return false;
		}
	}
	
	public Card getTopCard() {
		return this.topCard;
	}

	private void updateTopCard() {
		if (!store.isEmpty()) {
			for (int i = 0; i < store.size(); i++) {
				Card source = store.get(i);
				source.setDraggable(false);
				
				if (i == store.size()-1) {
					Card top = store.get(i);
					topCard = top;
				}
				
			}
			
			
		} else topCard = null;
	}
	
	public int getNumberOfCards() {
		return this.numberOfCards;
	}
}
