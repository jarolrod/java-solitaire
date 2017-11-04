package code;

import java.util.ArrayList;
import javafx.scene.layout.StackPane;

public class TableauPile extends StackPane {
	private ArrayList<Card> store = new ArrayList<Card>();
	private String gameType;
	private int identifier;
	Card topCard;
	private int y;
	
	
	public TableauPile(String gameType) {
		super();
		super.setPrefWidth(146.00);
		super.setPrefHeight(418);
		super.setMaxWidth(146);
		this.gameType = gameType;
	}
	
	public TableauPile(String gameType, ArrayList<Card> initializer, int identifier) {
		super();
		super.prefWidth(146.00);
		this.gameType = gameType;
		this.identifier = identifier;
		initializePile(initializer);	
	}

	public boolean removeCard() {
		if (removeTest()) {
			store.remove(store.size()-1);
			y-=32;
			updateTopCard();
			return true;
		} else return false;
	}

	public boolean addCard(Card newCard) {
		if (gameType.equals("Bakers_Dozen")) {
			if(addTest(newCard)) {
				
				newCard.setLayoutY(y);
				newCard.setManaged(false);
				newCard.toFront();
				newCard.setVisible(true);
				super.getChildren().add(newCard);
				store.add(newCard);
				y+=32;
				updateTopCard();
				return true;
			}
		} else if (gameType.equals("Freecell")) {
			if(addTest(newCard)) {
				newCard.setLayoutY(y);
				newCard.setManaged(false);
				newCard.toFront();
				newCard.setVisible(true);
				super.getChildren().add(newCard);
				store.add(newCard);
				y+=32;
				updateTopCard();
				return true;
			}
		} else if (gameType.equals("Aces_Up")) {
			newCard.setLayoutY(y);
			newCard.setManaged(false);
			newCard.toFront();
			newCard.setVisible(true);
			super.getChildren().add(newCard);
			store.add(newCard);
			y+= 32;
			updateTopCard();
			return true;
		}
		return false;
	}
	
	public boolean removeTest() {
		if(!store.isEmpty()) return true;
		else return false;
	}
	
	public boolean addTest(Card newCard) {
		if (gameType.equals("Bakers_Dozen")) {
			if (!store.isEmpty()) {
				Card test = store.get(store.size()-1);
				int testRank = test.getRank();
				int newCardRank = newCard.getRank();
				boolean oneLess = ((testRank - newCardRank) == 1);
				if(oneLess) return true; 
			} else return false;	
		} else if (gameType.equals("Freecell")) {
			if (store.isEmpty()) return true;
			else {
				Card test = store.get(store.size()-1);
				int testRank = test.getRank();
				int newCardRank = newCard.getRank();
				boolean oneLess = ((testRank-newCardRank)==1);
				if (oneLess) {
					String testColor = test.getColor();
					String newCardColor = newCard.getColor();
					if (!testColor.equals(newCardColor)) return true;
				}
			}
		} else if (gameType.equals("Aces_Up")) {
			return true;
		}
		return false;
	}
	
	public void initializePile(ArrayList<Card> initializer) {
		y = 0;
		if (gameType.equals("Bakers_Dozen")) {
			y=0;
			for (int i = 0; i < initializer.size(); i++) {
				Card current = initializer.get(i);
				store.add(initializer.get(i));
			}
			for (int i = 0; i < store.size(); i++) {
				Card testCard = store.get(i);
				if (testCard.getStringRank().equals("k")) {
					if (i == 0) {
					} else {
						Card indexZero = store.get(0);
						if (indexZero.getStringRank().equals("k")) {
							Card indexOne = store.get(1);
							store.set(1, testCard);
							store.set(i, indexOne);
						} else {
							store.set(0, testCard);
							store.set(i, indexZero);
						}
					}
				}
			}
			for (Card obj : store) {
				obj.setManaged(false);
				obj.setLayoutY(y);
				super.getChildren().add(obj);
				y+=32;
			}
			topCard = store.get(store.size()-1);	
		} else {
			y=0;
			for (int i = 0; i < initializer.size(); i++) {
				Card current = initializer.get(i);
				current.setManaged(false);
				current.setLayoutY(y);
				super.getChildren().add(current);
				store.add(initializer.get(i));
				y+=32;
			}
		} 
		updateTopCard();
	}
	
	public void updateTopCard() {
		if (!store.isEmpty()) {
			for (int i = 0; i < store.size(); i++) {
				Card source = store.get(i);
				source.setDraggable(false);
				
				if (i == store.size()-1) {
					Card top = store.get(i);
					top.setDraggable(true);
					topCard = top;
				}
			}
		} else {
			topCard = null;
		}
	}
	
	public Card getTopCard() {
		return this.topCard;
	}
	
	public int getPrivateY() {
		return this.y;
	}
	
	public ArrayList<Card> getCardStore() {
		return this.store;
	}
	
	public int getNumberOfCards() {
		return this.store.size();
	}
	
	public int getIdentifier() {
		return this.identifier;
	}
	
	public void setIdentifier(int value) {
		this.identifier = value;
	}
}
