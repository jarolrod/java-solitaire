package code;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class StockPile extends StackPane {
	private Deck gameDeck;
	public ArrayList<Card> store;
	
	
	public StockPile() {
		super();
		gameDeck = new Deck();
		gameDeck.deckShuffle();
		store = gameDeck.getStore();
		super.setWidth(156);
		super.setHeight(204);
		super.setPrefSize(156, 204);	
		addGroups();
		updateTopGroup();
	}
	
	private void addGroups() {
		int y = 5;
		int x = 5;
		for (int i = 0; i < 12; i++) {
			Rectangle temp = new Rectangle(146,194);
			temp.setArcHeight(15);
			temp.setArcWidth(15);
			String imageLocation = "/assets/card_assets/cards/backofHand.png";
			Image tempImage = new Image(imageLocation);
			temp.setFill(new ImagePattern(tempImage));
			temp.setStroke(Color.GRAY);
			temp.setVisible(true);
			temp.toFront();
			temp.setManaged(false);
			temp.setLayoutX(x);
			temp.setLayoutY(y);
			super.getChildren().add(temp);
			x-=1;
			y-=1;
		}
	}
	
	public void updateTopGroup() {
		if(!store.isEmpty()) {
			Rectangle temp = (Rectangle) super.getChildren().get(super.getChildren().size()-1);
			temp.setOnMouseEntered(e -> {
				temp.setStroke(Color.GOLD);
				temp.setStrokeWidth(2);
			});
			
			temp.setOnMouseExited(e -> {
				temp.setStroke(Color.GRAY);
				temp.setStrokeWidth(1);
			});
		}
		
	}
	
	public void dealCards(ArrayList<TableauPile> param) {
		if (!store.isEmpty()) {
			for (TableauPile pile : param) {
				pile.addCard(store.remove(store.size()-1));
				pile.updateTopCard();
			}
			super.getChildren().remove(super.getChildren().size()-1);
			updateTopGroup();
		}
	}
	
	public Card removeCard() {
		Card retVal = store.remove(store.size() -1);
		return retVal;
	}
	
	
	public boolean isEmpty() {
		if (store.isEmpty()) return true;
		return false;
	}
}
