package code;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;

public class FreecellPile extends StackPane {
	private ArrayList<Card> store = new ArrayList<Card>();
	Card topCard;
	
	public FreecellPile() {
		super();
		super.setWidth(156);
		super.setHeight(204);
		super.setPrefSize(156, 204);
	}
	
	public boolean removeCard() {
		if (!store.isEmpty()) {
			store.remove(store.size()-1);
			topCard = null;	
			return true;
		} else return false;
	}

	public void addCard(Card newCard) {
		if(addTest()) {
			newCard.setVisible(true);
			newCard.setManaged(true);
			newCard.toFront();
			super.getChildren().add(newCard);
			store.add(newCard);
			updateTopCard();
		}		
	}
	
	public boolean isEmpty() {
		if (store.size() == 0) return true;
		return false;
	}
	
	public boolean addTest() {
		if (store.isEmpty()) return true;
		else return false;
	}

	public void updateTopCard() {
		if (!store.isEmpty()) {
			Card top = store.get(0);
			top.setDraggable(true);
			topCard = top;
		} else topCard = null;
	}
	
	public Card getTopCard() {
		return this.topCard;
	}
	
	public int getNumberOfCards() {
		return this.store.size();
	}
}
