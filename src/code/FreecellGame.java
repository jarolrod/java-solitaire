package code;


import java.util.ArrayList;

import GUI.ExitWindow;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class FreecellGame extends AnchorPane {
	Deck gameDeck;
	ArrayList<TableauPile> tableauPiles = new ArrayList<>();
	ArrayList<HomecellPile> homecellPiles = new ArrayList<>();
	ArrayList<FreecellPile> freecellPiles = new ArrayList<>();
	private Card tempCard;
	
	private double oldX;
	private double oldY;
	
	private double mouseX;
	private double mouseY;
	
	public FreecellGame() {
		super();
		super.setPrefSize(1464, 900);
		super.setWidth(1464);
		super.setHeight(900);
		initializeGame();
	}
	
	private void initializeGame() {
		super.setStyle(" -fx-background-color: #008000;");
		
		Button easterEgg = new Button();
		easterEgg.setVisible(true);
		easterEgg.setBackground(null);
		easterEgg.setOnMouseClicked(e -> {
			String easterEggBG = "/assets/easter_egg/freecell/vaporWaveBG.gif";
			String easterEggGif ="/assets/easter_egg/freecell/giphy.gif";
			String homecellGif = "/assets/easter_egg/freecell/giphy2.gif";
			super.setStyle("-fx-background-image: url(" + easterEggBG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
			for (FreecellPile pile : freecellPiles) {
				pile.setStyle("-fx-background-image: url(" + easterEggGif +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
				
				pile.setOnMouseEntered(b -> {
					pile.setStyle("-fx-border-style: solid; -fx-border-color: #ffffff; -fx-border-width: 4; -fx-border-insets: 5, 15;" + "-fx-background-image: url(" + easterEggGif +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
				});
				
				pile.setOnMouseExited(b -> {
					pile.setStyle("-fx-border-style: none;" +  "-fx-background-image: url(" + easterEggGif +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch;");
				});
			}
			
			for (HomecellPile pile : homecellPiles) {
				pile.setStyle("-fx-background-image: url(" + homecellGif +"); -fx-background-repeat: repeat; -fx-background-size: contain;");
				
				pile.setOnMouseEntered(b -> {
					pile.setStyle("-fx-border-style: solid; -fx-border-color: #ffffff; -fx-border-width: 4; -fx-border-insets: 5, 15;" + "-fx-background-image: url(" + homecellGif +"); -fx-background-repeat: repeat; -fx-background-size: contain;");
				});
				
				pile.setOnMouseExited(b -> {
					pile.setStyle("-fx-border-style: none;" +  "-fx-background-image: url(" + homecellGif +"); -fx-background-repeat: repeat; -fx-background-size: contain;");
				});
			}
			
			ImageView ayy = new ImageView();
			Image ayyImage = new Image("/assets/easter_egg/freecell/ayy.gif");
			ayy.setImage(ayyImage);
			ayy.setLayoutX(800);
			ayy.setLayoutY(650);
			ayy.setManaged(false);
			ayy.setVisible(true);
			super.getChildren().add(ayy);
			
			ImageView ayyBong = new ImageView();
			Image ayyBongImage = new Image("/assets/easter_egg/freecell/ayyBong.gif");
			ayyBong.setImage(ayyBongImage);
			ayyBong.setLayoutX(630);
			ayyBong.setLayoutY(40);
			ayyBong.setManaged(false);
			ayyBong.setVisible(true);
			ayyBong.setFitWidth(200);
			ayyBong.setFitHeight(200);
			super.getChildren().add(ayyBong);
			
		});
		super.getChildren().add(easterEgg);

		
		gameDeck = new Deck();
		gameDeck.deckShuffle();
		ArrayList<Card> tableauCards = new ArrayList<>();
		String[] firstIdArray = {"a","b","c","d"};
		String[] secondIdArray = {"e","f","g","h"};
		
		// Tableau Piles
		int startIndex = 0;
		int endIndex = 7;
		int startIndexb = 0;
		int endIndexb = 6;
		
		double tableauY = 255.0;
		double tableauX = 25.00;
		
		// 7 Card Tableaus
		for (int i = 0; i < 4; i++) {		
			tableauCards.clear();
			for (int b = startIndex; startIndex < endIndex; b++) {
				tableauCards.add(gameDeck.getCard(startIndex));
				startIndex++;
			}
			
			TableauPile newPile = new TableauPile("Freecell", tableauCards, i);
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
						moveTempCard(temp, newPile);	
					}
				});
			}

			newPile.setId(firstIdArray[i]);
			newPile.setLayoutY(tableauY);
			newPile.setLayoutX(tableauX);
			newPile.setManaged(false);
			
			super.getChildren().add(newPile);	
			tableauPiles.add(newPile);
			
			startIndex = endIndex;
			endIndex += 7;
			tableauX += 181;
		}
		startIndexb = endIndex - 7;
		endIndexb = startIndexb + 6;
		
		// 6 Card Tableaus
		for (int i = 0; i < 4; i++) {
			tableauCards.clear();
			for (int c = startIndexb; startIndexb < endIndexb; c++) {
				tableauCards.add(gameDeck.getCard(startIndexb));
				startIndexb++;
			}
			TableauPile newPile2 = new TableauPile("Freecell", tableauCards, i+4);
			for (Card obj : newPile2.getCardStore()) {
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
						moveTempCard(temp, newPile2);
					}
				});
			}
			
			newPile2.setId(secondIdArray[i]);
			newPile2.setLayoutY(tableauY);
			newPile2.setLayoutX(tableauX);
			newPile2.setManaged(false);
			
			super.getChildren().add(newPile2);	
			tableauPiles.add(newPile2);
			
			startIndexb = endIndexb;
			endIndexb += 6;
			tableauX += 181;
		}
		
		// Homecell Piles
		String[] homecellIdArray = {"H_A","H_B","H_C","H_D"};
		double homecellY = 25.00;
		double homecellX = 812.00;
		
		for (int i = 0; i < 4; i++) {
			HomecellPile newHomecell = new HomecellPile("Freecell");
			newHomecell.setLayoutY(homecellY);
			newHomecell.setLayoutX(homecellX);
			newHomecell.setManaged(false);
			newHomecell.setId(homecellIdArray[i]);
			
			
			
			super.getChildren().add(newHomecell);
			
			homecellPiles.add(newHomecell);
			homecellX += 156;
		}
		
		// Freecell Piles
		String[] freecellIdArray = {"F_A","F_B","F_C","F_D"};
		double freecellY = 25.00;
		double freecellX = 25.00;
		
		for (int i = 0; i < 4; i++) {
			FreecellPile newFreecell = new FreecellPile();
			newFreecell.setLayoutY(freecellY);
			newFreecell.setLayoutX(freecellX);
			newFreecell.setManaged(false);
			newFreecell.setId(freecellIdArray[i]);
			
			

			super.getChildren().add(newFreecell);
			
			freecellPiles.add(newFreecell);
			freecellX += 156;}
			defaultSkin();
		}
	
	private void moveTempCard(Card tempCard2, TableauPile originate) {
		disableTopCards();
		this.tempCard = tempCard2;
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
			if ((releasedX >= originate.getLayoutX()) && (releasedX <= (originate.getLayoutX()+146) ) && (releasedY >= 255)) {
				this.tempCard.setVisible(false);
				originate.getTopCard().setVisible(true);
				enableTopCards();
			
			// Released over Tableau Pile #1
			} else if ((releasedX >= 25) && (releasedX <= 171) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 25)) {
					Card test = originate.getTopCard();
					if(addCard(0,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(0));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #2
			} else if ((releasedX >= 206) && (releasedX <= 352) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 206)) {
					Card test = originate.getTopCard();
					if(addCard(1,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(1));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #3	
			} else if ((releasedX >= 387) && (releasedX <= 533) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 387)) {
					Card test = originate.getTopCard();
					if(addCard(2,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(2));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #4
			} else if ((releasedX >= 568) && (releasedX <= 714) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 568)) {
					Card test = originate.getTopCard();
					if(addCard(3,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(3));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
			
			// Released Over Tableau Pile #5
			} else if ((releasedX >= 749) && (releasedX <= 895) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 749)) {
					Card test = originate.getTopCard();
					if(addCard(4,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(4));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #6
			} else if ((releasedX >= 930) && (releasedX <= 1076) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 930)) {
					Card test = originate.getTopCard();
					if(addCard(5,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(5));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
			
			// Released Over Tableau Pile #7
			} else if ((releasedX >= 1111) && (releasedX <= 1257) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 1111)) {
					Card test = originate.getTopCard();
					if(addCard(6,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(6));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #8	
			} else if ((releasedX >= 1292) && (releasedX <= 1438) && (releasedY >= 255)) {
				if(!(originate.getLayoutX() == 1292)) {
					Card test = originate.getTopCard();
					if(addCard(7,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								moveTempCard(temp, tableauPiles.get(7));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
				
			// Released Over Freecell Pile #1	
			} else if ((releasedX >= 25) && (releasedX <= 181) && (releasedY >= 25) && (releasedY <= 250)) {
				if(freecellPiles.get(0).addTest()) {
					Card source = originate.getTopCard();
					source.setStyle("-fx-effect: none;");
					freecellPiles.get(0).addCard(source);
					originate.removeCard();
					this.tempCard.setVisible(false);
					
					source.setOnMouseEntered(e -> {
						if(source.getDraggable()) {
							source.setCursor(Cursor.HAND);
							source.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
						}
					});
					
					source.setOnMouseExited(e -> {
						if(source.getDraggable()) {
							source.setStyle("-fx-effect: none;");
						}
					});
					
					source.setOnMousePressed(e -> {
						if(source.getDraggable()) {
							source.setVisible(false);
							Card temp = new Card(source.getSuit(), source.getStringRank());	
							moveTempCardFreecell(temp, freecellPiles.get(0));
						}
					});
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Freecell Pile #2
			} else if ((releasedX >= 181) && (releasedX <= 337) && (releasedY >= 25) && (releasedY <= 250)) {
				if(freecellPiles.get(1).addTest()) {
					Card source = originate.getTopCard();
					source.setStyle("-fx-effect: none;");
					freecellPiles.get(1).addCard(source);
					originate.removeCard();
					this.tempCard.setVisible(false);
					
					source.setOnMouseEntered(e -> {
						if(source.getDraggable()) {
							source.setCursor(Cursor.HAND);
							source.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
						}
					});
					
					source.setOnMouseExited(e -> {
						if(source.getDraggable()) {
							source.setStyle("-fx-effect: none;");
						}
					});
					
					source.setOnMousePressed(e -> {
						if(source.getDraggable()) {
							Card temp = new Card(source.getSuit(), source.getStringRank());	
							moveTempCardFreecell(temp, freecellPiles.get(1));
						}
					});
					
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Freecell Pile #3
			} else if ((releasedX >= 337) && (releasedX <= 483) && (releasedY >= 25) && (releasedY <= 250)) {
				if(freecellPiles.get(2).addTest()) {
					Card source = originate.getTopCard();
					source.setStyle("-fx-effect: none;");
					freecellPiles.get(2).addCard(source);
					originate.removeCard();
					this.tempCard.setVisible(false);
					
					source.setOnMouseEntered(e -> {
						if(source.getDraggable()) {
							source.setCursor(Cursor.HAND);
							source.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
						}
					});
					
					source.setOnMouseExited(e -> {
						if(source.getDraggable()) {
							source.setStyle("-fx-effect: none;");
						}
					});
					
					source.setOnMousePressed(e -> {
						if(source.getDraggable()) {
							
							Card temp = new Card(source.getSuit(), source.getStringRank());	
							moveTempCardFreecell(temp, freecellPiles.get(2));
						}
					});
					
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Freecell Pile #4
			} else if ((releasedX >= 483) && (releasedX <= 629) && (releasedY >= 25) && (releasedY <= 250)) {
				if(freecellPiles.get(3).addTest()) {
					Card source = originate.getTopCard();
					source.setStyle("-fx-effect: none;");
					freecellPiles.get(3).addCard(source);
					originate.removeCard();
					this.tempCard.setVisible(false);
					
					source.setOnMouseEntered(e -> {
						if(source.getDraggable()) {
							source.setCursor(Cursor.HAND);
							source.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
						}
					});
					
					source.setOnMouseExited(e -> {
						if(source.getDraggable()) {
							source.setStyle("-fx-effect: none;");
						}
					});
					
					source.setOnMousePressed(e -> {
						if(source.getDraggable()) {
							Card temp = new Card(source.getSuit(), source.getStringRank());	
							moveTempCardFreecell(temp, freecellPiles.get(3));
						}
					});
					
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			//Released Over Homecell Pile #1
			} else if ((releasedX >= 812) && (releasedX <= 958) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(0).addTest(test)) {
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(0).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Homecell Pile #2
			} else if ((releasedX >= 958) && (releasedX <= 1114) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(1).addTest(test)) {
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(1).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Homecell Pile #3
			} else if ((releasedX >= 1114) && (releasedX <= 1270) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(2).addTest(test)) {
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(2).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Homecell Pile #4
			} else if ((releasedX >= 1270) && (releasedX <= 1416) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(3).addTest(test)) {
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(3).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("test", "test");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
			} 

			else {
				tempCard.setLayoutX(oldX);
				tempCard.setLayoutY(oldY);
				tempCard.setVisible(true);
				originate.getTopCard().setVisible(false);
			}
			
		});
		
		super.getChildren().add(tempCard);
	}
	
	private void moveTempCardFreecell(Card tempCard2, FreecellPile originate) {
		disableTopCards();
		this.tempCard = tempCard2;
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
			originate.getTopCard().setVisible(false);
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
			
			// Return to original Freecell Pile
			if ((releasedX >= originate.getLayoutX()) && (releasedX <= (originate.getLayoutX()+156) ) && (releasedY >= 25) && (releasedY <= 250)) {
				this.tempCard.setVisible(false);
				originate.getTopCard().setVisible(true);
				enableTopCards();
			
			// Released over Tableau Pile #1
			} else if ((releasedX >= 25) && (releasedX <= 171) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(0,1,test)) {	
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(0));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				
			// Released Over Tableau Pile #2
			} else if ((releasedX >= 206) && (releasedX <= 352) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(1,1,test)) {	
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(1));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				
				
			// Released Over Tableau Pile #3	
			} else if ((releasedX >= 387) && (releasedX <= 533) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(2,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(2));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				
			// Released Over Tableau Pile #4
			} else if ((releasedX >= 568) && (releasedX <= 714) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(3,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(3));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
			
			// Released Over Tableau Pile #5
			} else if ((releasedX >= 749) && (releasedX <= 895) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(4,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(4));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				
				
			// Released Over Tableau Pile #6
			} else if ((releasedX >= 930) && (releasedX <= 1076) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(5,1,test)) {	
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(5));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				
			
			// Released Over Tableau Pile #7
			} else if ((releasedX >= 1111) && (releasedX <= 1257) && (releasedY >= 255)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(6,1,test)) {
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(6));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				
				
			// Released Over Tableau Pile #8	
			} else if ((releasedX >= 1292) && (releasedX <= 1438) && (releasedY >= 255) && (releasedY <= 750)) {
					Card test = originate.getTopCard();
					test.setManaged(false);
					if(addCard(7,1,test)) {	
						test.setOnMouseEntered(e -> {
							if(test.getDraggable()) {
								test.setCursor(Cursor.HAND);
								test.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
							}
						});
						
						test.setOnMouseExited(e -> {
							if(test.getDraggable()) {
								test.setStyle("-fx-effect: none;");
							}
						});
						
						test.setOnMousePressed(e -> {
							if(test.getDraggable()) {
								Card temp = new Card(test.getSuit(), test.getStringRank());	
								temp.setManaged(false);
								moveTempCard(temp, tableauPiles.get(7));	
							}
						});
						originate.removeCard();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}

			//Released Over Homecell Pile #1
			} else if ((releasedX >= 812) && (releasedX <= 958) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(0).addTest(test)) {
					test.setManaged(false);
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(0).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Homecell Pile #2
			} else if ((releasedX >= 958) && (releasedX <= 1114) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(1).addTest(test)) {
					test.setManaged(false);
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(1).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Homecell Pile #3
			} else if ((releasedX >= 1114) && (releasedX <= 1270) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(2).addTest(test)) {
					test.setManaged(false);
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(2).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
				
			// Released Over Homecell Pile #4
			} else if ((releasedX >= 1270) && (releasedX <= 1416) && (releasedY >= 25) && (releasedY <= 250)) {
				Card test = originate.getTopCard();
				if(homecellPiles.get(3).addTest(test)) {
					test.setManaged(true);
					test.setStyle("-fx-effect: none;");
					test.setDraggable(false);
					test.setCursor(Cursor.DEFAULT);
					homecellPiles.get(3).addCard(test);
					this.tempCard.setVisible(false);
					originate.removeCard();
					enableTopCards();
				} else {
					ExitWindow.display("Illegal Move", "Illegal Move");
					tempCard.setLayoutX(oldX);
					tempCard.setLayoutY(oldY);
				}
			
			// Activate Easter Egg
			} else if ((releasedY >= 800)) {
				String easterBG = "/Faulty-Memory-Code.png";
				super.setStyle("-fx-background-image: url(" + easterBG +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;");
				tempCard.setLayoutX(oldX);
				tempCard.setLayoutY(oldY);
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
		
		for (FreecellPile pile : freecellPiles) {
			if(!(pile.isEmpty())) {
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
		
		for (FreecellPile pile : freecellPiles) {
			if(!(pile.isEmpty())) {
				pile.topCard.setDraggable(true);
				pile.topCard.setCursor(Cursor.HAND);
			}
		}
	}
	
	
	
	private boolean addCard(int index, int pile, Card newCard) {
		if (pile == 1) {
			if (tableauPiles.get(index).addTest(newCard)) {
				tableauPiles.get(index).addCard(newCard);
				return true;
			} else return false;
		} else if (pile == 2) {
			if(homecellPiles.get(index).addTest(newCard)) {
				homecellPiles.get(index).addCard(newCard);
				return true;
			} else return false;
		} else if (pile == 3) {
			if(freecellPiles.get(index).addTest()) {
				freecellPiles.get(index).addCard(newCard);
				return true;
			} else return false;
		} return false;
	}
	
	private boolean removeCard(int index, int pile) {
		if (pile == 1) {
			if (tableauPiles.get(index).removeTest()) {
				tableauPiles.get(index).removeCard();
				return true;
			} else return false;
		} else if (pile == 2) {
			if (homecellPiles.get(index).removeCard()) {
				homecellPiles.get(index).removeCard();
				return true;
			} else return false;
		} else if (pile == 3) {
			if (freecellPiles.get(index).removeCard()) {
				freecellPiles.get(index).removeCard();
				return true;
			} else return false;
		} return false;
	}
	
	private int getNumberOfCards(int index, int pile) {
		int numberOfCards = 0;
		if (pile == 1) {
			TableauPile testPile = tableauPiles.get(index);
			numberOfCards = testPile.getNumberOfCards();
		} else if (pile == 2) {
			HomecellPile testPile = homecellPiles.get(index);
			numberOfCards = testPile.getNumberOfCards();
		} else if (pile == 3) {
			FreecellPile testPile = freecellPiles.get(index);
			numberOfCards = testPile.getNumberOfCards();
		} return numberOfCards;
	}
	
	private Card getTopCard(int index, int pile) {
		Card topCard = null;
		if (pile == 1) {
			topCard = tableauPiles.get(index).getTopCard();
		} else if (pile == 2) {
			topCard = homecellPiles.get(index).getTopCard();
		} else if (pile == 3) {
			topCard = freecellPiles.get(index).getTopCard();
		} return topCard;
	}
	
	public void defaultSkin() {
		super.setStyle(" -fx-background-color: #008000;");
		for (FreecellPile pile : freecellPiles) {
			pile.setStyle("-fx-background-color: #e6b800; -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #ff0000; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: #e6b800;");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: #e6b800; -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
		for (HomecellPile pile : homecellPiles) {
			pile.setStyle("-fx-background-color: #004d00; -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #8080ff; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: #004d00;");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: #004d00; -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
	}
	
	public void koiSkin() {
		String BG = "/assets/backgrounds/fish.gif";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
		for (FreecellPile pile : freecellPiles) {
			pile.setStyle("-fx-background-color: rgba(255,215,0,0.5); -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #ff0000; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: rgba(255,215,0,0.5);");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: rgba(255,215,0,0.5); -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		}
		for (HomecellPile pile : homecellPiles) {
			pile.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #8080ff; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: rgba(0,0,0,0.6);");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
	}

	public void jungleSkin() {
		String BG = "/assets/backgrounds/jungle.gif";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
		for (FreecellPile pile : freecellPiles) {
			pile.setStyle("-fx-background-color: #e6b800; -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #ff0000; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: #e6b800;");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: #e6b800; -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
		for (HomecellPile pile : homecellPiles) {
			pile.setStyle("-fx-background-color: #004d00; -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #8080ff; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: #004d00;");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: #004d00; -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
	}
	
	public void desertSkin() {
		String BG = "/assets/backgrounds/desert.jpg";
		super.setStyle("-fx-background-image: url(" + BG +"); -fx-background-repeat: no-repeat; -fx-background-size: stretch; -fx-background-color: #0000cc;");
		for (FreecellPile pile : freecellPiles) {
			pile.setStyle("-fx-background-color: #e6b800; -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #ff0000; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: #e6b800;");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: #e6b800; -fx-border-style: solid; -fx-border-color: #000000; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
		for (HomecellPile pile : homecellPiles) {
			pile.setStyle("-fx-background-color: #004d00; -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			pile.setOnMouseEntered(e -> {
				pile.setStyle("-fx-border-style: solid; -fx-border-color: #8080ff; -fx-border-width: 4; -fx-border-insets: 5, 15; -fx-background-color: #004d00;");
			});
			
			pile.setOnMouseExited(e -> {
				pile.setStyle("-fx-background-color: #004d00; -fx-border-style: solid; -fx-border-color: #00cc00; -fx-border-width: 4; -fx-border-insets: 5, 15;");
			});
		
		}
	}
	
	public ArrayList<TableauPile> getTableauArray() {
		return this.tableauPiles;
	}
	
	public ArrayList<HomecellPile> getHomecellArray() {
		return this.homecellPiles;
	}
	
	public ArrayList<FreecellPile> getFreecellArray() {
		return this.freecellPiles;
	}	
}
