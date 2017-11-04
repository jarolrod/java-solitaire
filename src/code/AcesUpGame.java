package code;

import java.util.ArrayList;

import GUI.ExitWindow;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class AcesUpGame extends AnchorPane {
	StockPile gameDeck;
	HomecellPile homecell = new HomecellPile("Aces_Up");
	ArrayList<TableauPile> tableauPiles = new ArrayList<>();
	private Card tempCard;
	
	private double oldX;
	private double oldY;
	
	private double mouseX;
	private double mouseY;
	
	public AcesUpGame() {
		super();
		super.setPrefSize(1464, 900);
		super.setWidth(1464);
		super.setHeight(900);
		String BG = "/assets/backgrounds/fish.gif";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
		initializeGame();
	}
	
	private void initializeGame() {
//		super.setStyle(" -fx-background-color: #008000;");
		
		ArrayList<Card> tableauCards = new ArrayList<>();
		String[] idArray = {"a","b","c","d"};
		
		int tableauX = 525;
		int tableauY = 75;
		
		// StockPile
		gameDeck = new StockPile();
		gameDeck.setId("SP");
		gameDeck.setLayoutX(175);
		gameDeck.setLayoutY(125);
		gameDeck.setManaged(false);
		gameDeck.setOnMouseEntered(e -> {
			if(!gameDeck.isEmpty()) {
				gameDeck.setCursor(Cursor.HAND);
			} else {
				gameDeck.setCursor(Cursor.DEFAULT);
			}
			
		});
		
		gameDeck.setOnMouseClicked(e -> {
				disableTopCards();
				if (!(this.tempCard == null)) {
					this.tempCard.setVisible(false);
					this.tempCard = null;
				}
				gameDeck.dealCards(tableauPiles);
				enableTopCards();
				functionality();
		});
		
		super.getChildren().add(gameDeck);
		
		// Tableau Piles
		for (int i = 0; i < 4; i++) {
			tableauCards.clear();
			Card current = gameDeck.removeCard();
			String imageLocation = "/resources/card_images/" + current.getStringRank() + current.getSuit() + ".gif";
			Image flipSide = new Image(imageLocation);
			current.setFill(new ImagePattern(flipSide));
			tableauCards.add(current);
			TableauPile newPile = new TableauPile("Aces_Up",tableauCards, i);
			for (Card obj : newPile.getCardStore()) {
				obj.setOnMouseEntered(e -> {
					if(obj.getDraggable()) {
						obj.setCursor(Cursor.HAND);
						obj.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
					}
				});
				
				obj.setOnMouseExited(e -> {
					if(obj.getDraggable()) {
						obj.setStyle("-fx-effect: none;");
					}
				});
				
				obj.setOnMousePressed(e -> {
					if(obj.getDraggable()) {
						Card temp = new Card(obj.getSuit(), obj.getStringRank());	
						Card response = checkTopCards(obj);
						if (response == null) {
							for (TableauPile pile : tableauPiles) {
								if (pile.getCardStore().isEmpty()) {
									moveTempCard(temp, newPile);
									break;
								} 
							}
						} else {
							obj.setCursor(Cursor.DEFAULT);
							homecell.addCard(obj);
							newPile.removeCard();
						}
					}
				});
			}
			
			newPile.setId(idArray[i]);
			newPile.setLayoutY(tableauY);
			newPile.setLayoutX(tableauX);
			newPile.setManaged(false);
			
			super.getChildren().add(newPile);
			tableauPiles.add(newPile);
			tableauX+=191;
		}
		
		// Homecell Pile
		homecell.setId("H_A");
		homecell.setLayoutX(175);
		homecell.setLayoutY(421);
		homecell.setManaged(false);
		super.getChildren().add(homecell);
	}
	
	private Card checkTopCards(Card test) {
		int rank = test.getRank();
		String suit = test.getSuit();
		Card retVal = null;
		if (!(test.getRank() == 1)) {
			for (TableauPile pile : tableauPiles) {
				if(!pile.getCardStore().isEmpty()) {
					Card current = pile.getTopCard();
					if (current.getSuit().equals(suit)) {
						if (current.getRank() == 1) {
							retVal = test;
						} else if (current.getRank() > rank) {
							retVal = test;
						}
					}
				}
			}
		} return retVal;
	}
	
	private void functionality() {
		for (TableauPile pile : tableauPiles) {
			ArrayList<Card> store = pile.getCardStore();
			for (int i = 0; i < store.size(); i++) {
				store.get(i).setDraggable(false);
				store.get(i).setCursor(Cursor.DEFAULT);
				if (i == store.size()-1) {
					Card obj = store.get(i);
					obj.setDraggable(true);
					obj.setOnMouseEntered(e -> {
						if(obj.getDraggable()) {
							obj.setCursor(Cursor.HAND);
							obj.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
						}
					});
					
					obj.setOnMouseExited(e -> {
						if(obj.getDraggable()) {
							obj.setStyle("-fx-effect: none;");
						}
					});
					
					obj.setOnMousePressed(e -> {
						if(obj.getDraggable()) {
							Card temp = new Card(obj.getSuit(), obj.getStringRank());	
							Card response = checkTopCards(obj);
							if (response == null) {
								for (TableauPile pile2 : tableauPiles) {
									if (pile2.getCardStore().isEmpty()) {
										moveTempCard(temp, pile);
									} 
								}
							} else {
								obj.setCursor(Cursor.DEFAULT);
								homecell.addCard(obj);
								pile.removeCard();
							}
						}
					});
				}
			}
		}
	}
	
	private void moveTempCard(Card temp, TableauPile originate) {
		disableTopCards();
		this.tempCard = temp;
		String sourceID = tempCard.getId();
		String imageLocationInverted = "/resources/card_images/card_images_inverted/" + sourceID + ".png";
		this.tempCard.setStyle("-fx-background-image: url(" + imageLocationInverted +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		Image inverted = new Image(imageLocationInverted);
		this.tempCard.setFill(new ImagePattern(inverted));
		this.tempCard.setStroke(Color.WHITE);
			
		oldX = tempCard.getLayoutX();
		oldY = tempCard.getLayoutY();
		
		this.tempCard.setLayoutX(originate.getLayoutX());
		this.tempCard.setLayoutY(originate.topCard.getLayoutY() + originate.getLayoutY());
		tempCard.setManaged(false);
		tempCard.toFront();
		
		tempCard.setCursor(Cursor.HAND);
		
		
		tempCard.setOnMousePressed(b -> {
			
			mouseX = b.getSceneX();
			mouseY = b.getSceneY();
			
			oldX = tempCard.getLayoutX();
			oldY = tempCard.getLayoutY();
		});
		
		tempCard.setOnMouseDragged(b -> {
			originate.getTopCard().setVisible(false);
			tempCard.setCursor(Cursor.CLOSED_HAND);
			String sourceID2 = tempCard.getId();
			String imageLocationInverted2 = "/resources/card_images/card_images_inverted/" + sourceID2 + ".png";
			tempCard.setStyle("-fx-background-image: url(" + imageLocationInverted2 +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 35, 0, 0, 0);");
			tempCard.relocate(b.getSceneX() - mouseX + oldX, b.getSceneY() - mouseY +  oldY);
		});
		
		tempCard.setOnMouseReleased(b -> {
			double releasedX = tempCard.getLayoutX();
			double releasedY = tempCard.getLayoutY();
			
			// Return to original tableau Pile
			if ((releasedX >= originate.getLayoutX()) && (releasedX <= (originate.getLayoutX()+146) ) && (releasedY >= 75)) {
				this.tempCard.setVisible(false);
				originate.getTopCard().setVisible(true);
				enableTopCards();
			
			// Released over Tableau Pile #1
			} else if ((releasedX >= 525) && (releasedX <= 671) && (releasedY >= 75)) {
				if(!(originate.getLayoutX() == 525)) {
					Card test = originate.getTopCard();
					if(tableauPiles.get(0).getCardStore().isEmpty()) {
						tableauPiles.get(0).addCard(test);
						originate.removeCard();
						enableTopCards();
						functionality();
						this.tempCard.setVisible(false);
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #2
			} else if ((releasedX >= 716) && (releasedX <= 862) && (releasedY >= 75)) {
				if(!(originate.getLayoutX() == 716)) {
					Card test = originate.getTopCard();
					if(tableauPiles.get(1).getCardStore().isEmpty()) {
						tableauPiles.get(1).addCard(test);
						originate.removeCard();
						enableTopCards();
						functionality();
						this.tempCard.setVisible(false);
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
			// Released Over Tableau Pile #3	
			} else if ((releasedX >= 907) && (releasedX <= 1053) && (releasedY >= 75)) {
				if(!(originate.getLayoutX() == 907)) {
					Card test = originate.getTopCard();
					if(tableauPiles.get(2).getCardStore().isEmpty()) {
						tableauPiles.get(2).addCard(test);
						originate.removeCard();
						enableTopCards();
						functionality();
						this.tempCard.setVisible(false);
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #4
			} else if ((releasedX >= 1098) && (releasedX <= 1244) && (releasedY >= 75)) {
				if(!(originate.getLayoutX() == 1098)) {
					Card test = originate.getTopCard();
					if(tableauPiles.get(3).getCardStore().isEmpty()) {
						tableauPiles.get(3).addCard(test);
						originate.removeCard();
						enableTopCards();
						functionality();
						this.tempCard.setVisible(false);
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
			} else {
				tempCard.setLayoutX(oldX);
				tempCard.setLayoutY(oldY);
				tempCard.setVisible(true);
				originate.getTopCard().setVisible(false);
			}	
		});
		super.getChildren().add(tempCard);
	}
	
	private void disableTopCards() {
		for (TableauPile pile : tableauPiles) {
			if (pile.removeTest()) {
				pile.topCard.setDraggable(false);
				pile.topCard.setCursor(Cursor.DEFAULT);
			} 
		}
	}
	
	private void enableTopCards() {
		for (TableauPile pile : tableauPiles) {
			if (pile.removeTest()) {
				pile.topCard.setDraggable(true);
				pile.topCard.setCursor(Cursor.HAND);
			}
		}
	}
	
	public void defaultSkin() {
		super.setStyle(" -fx-background-color: #008000;");
	}
	
	public void koiSkin() {
		String BG = "/assets/backgrounds/fish.gif";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
	}

	public void jungleSkin() {
		String BG = "/assets/backgrounds/jungle.gif";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
	}
	
	public void desertSkin() {
		String BG = "/assets/backgrounds/desert.jpg";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
	}
}



