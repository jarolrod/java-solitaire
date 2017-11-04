package code;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Card extends Rectangle {
	private String suit;
	private String rank;
	private String color;
	private Image cardImage;
	private Shape cardObj;
	private String id;
	private String imageLocation;
	private boolean draggable;

	public Card(String suit, String rank) {
		super(146,194);
		this.suit = suit;
		this.rank = rank;
		draggable = false;
		imageLocation = "/resources/card_images/" + rank + suit + ".gif";
		
		super.setArcHeight(15);
		super.setArcWidth(15);
		super.setStyle("-fx-background-image: url(" + imageLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;");

		Image test = new Image(imageLocation);
		super.setFill(new ImagePattern(test));
		super.setStroke(Color.BLACK);
		super.setManaged(false);
		id = rank + suit;
		super.setId(id);
		updateColor(suit);	
	}
	
	public boolean getDraggable() {
		return this.draggable;
	}
	
	public void setDraggable(boolean value) {
		this.draggable = value;
	}
	

	public String getSuit() {
		return this.suit;
	}
	
	public int getRank() {
		if(this.rank.equals("a")) return 1;
		if(this.rank.equals("j")) return 11;
		if(this.rank.equals("q")) return 12;
		if(this.rank.equals("k")) return 13;
		int rankToInt = Integer.parseInt(this.rank);
		return rankToInt;
	}

	public String getStringRank() {
		return this.rank;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public Image getImage() {
		return this.cardImage;
	}
	
	public Shape getCardObj() {
		return this.cardObj;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getImageLocation() {
		return this.imageLocation;
	}
	
	public Image getCardImage() {
		return this.cardImage;
	}
	
	public void updateColor(String suit) {
		if ((suit.equals("d")) || (suit.equals("h"))) this.color = "red";
		else if ((suit.equals("c")) || (suit.equals("s"))) this.color = "black";
	}
}
