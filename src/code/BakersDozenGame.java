package code;

import java.util.ArrayList;

import GUI.ExitWindow;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class BakersDozenGame extends AnchorPane {
	Deck gameDeck;
	ArrayList<TableauPile> tableauPiles = new ArrayList<>();
	ArrayList<HomecellPile> homecellPiles = new ArrayList<>();
	ArrayList<FreecellPile> freecellPiles = new ArrayList<>();
	private Card tempCard;

	private double oldX;
	private double oldY;
	
	private double mouseX;
	private double mouseY;
	
	
	public BakersDozenGame() {
		super();
		super.setPrefSize(1464, 900);
		super.setWidth(1464);
		super.setHeight(900);
		initializeGame();
	}
	
	private void initializeGame() {
		gameDeck = new Deck();
		gameDeck.deckShuffle();
		ArrayList<Card> tableauCards = new ArrayList<>();
		String[] firstIdArray = {"a","b","c","d","e","f","g"};
		String[] secondIdArray = {"h","i","j","k","l","m",};
				
		// Tableau Piles
		int startIndex = 0;
		int endIndex = 4;
		
		double firstY = 60;
		double firstX = 80;
		
		// First 7 Tableau Piles
		for (int i = 0; i < 7; i++) {
			tableauCards.clear();
			for (int b = startIndex; startIndex < endIndex; b++) {
				startIndex++;
				tableauCards.add(gameDeck.getCard(b));		
			}
			TableauPile newPile = new TableauPile("Bakers_Dozen", tableauCards, i);
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
			newPile.setLayoutY(firstY);
			newPile.setLayoutX(firstX);
			newPile.setManaged(false);
			
			super.getChildren().add(newPile);
			tableauPiles.add(newPile);
			
			startIndex=endIndex;
			endIndex+=4;
			firstX+=157;
		}
		
		double secondY = 420;
		double secondX = 155;
		
		
		// Next 6 Tableau Piles
		for (int i = 0; i < 6; i++) {
			tableauCards.clear();
			for (int b = startIndex; startIndex < endIndex; b++) {
				startIndex++;
				tableauCards.add(gameDeck.getCard(b));		
			}
			TableauPile newPile2 = new TableauPile("Bakers_Dozen", tableauCards, i+7);
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
			newPile2.setLayoutY(secondY);
			newPile2.setLayoutX(secondX);
			newPile2.setManaged(false);
			
			super.getChildren().add(newPile2);
			tableauPiles.add(newPile2);
			
			startIndex=endIndex;
			endIndex+=4;
			secondX+=157;	
		}
		
		
		// Homecell Piles
		String[] homecellIdArray = {"H_A","H_B","H_C","H_D"};
		double homecellY = 25.00;
		double homecellX = 1283;
		
		for (int i = 0; i < 4; i++) {
			HomecellPile newHomecell = new HomecellPile("Bakers_Dozen");
			newHomecell.setLayoutY(homecellY);
			newHomecell.setLayoutX(homecellX);
			newHomecell.setManaged(false);
			newHomecell.setId(homecellIdArray[i]);
			
			super.getChildren().add(newHomecell);
			homecellPiles.add(newHomecell);
			homecellY += 204;
		}	
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
			if ((releasedX >= originate.getLayoutX()) && (releasedX <= (originate.getLayoutX()+146) )) {
				this.tempCard.setVisible(false);
				originate.getTopCard().setVisible(true);
				enableTopCards();
			
			// Released over Tableau Pile #1
			} else if ((releasedX >= 80) && (releasedX <= 226) && (releasedY <= 410) && (releasedY >= 60)) {
				if(!(originate.getLayoutX() == 80)) {
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
						tableauPiles.get(0).toFront();
						enableTopCards();
						this.tempCard.setVisible(false);
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #2
			} else if ((releasedX >= 237) && (releasedX <= 383) && (releasedY <= 410) && (releasedY >= 60)) {
				if(!(originate.getLayoutX() == 237)) {
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
						tableauPiles.get(1).toFront();
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
			} else if ((releasedX >= 394) && (releasedX <= 540) && (releasedY <= 410) && (releasedY >= 60)) {
				if(!(originate.getLayoutX() == 394)) {
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
						tableauPiles.get(2).toFront();
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
			} else if ((releasedX >= 551) && (releasedX <= 697) && (releasedY <= 410) && (releasedY >= 60)) {
				if(!(originate.getLayoutX() == 551)) {
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
						tableauPiles.get(3).toFront();
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
			} else if ((releasedX >= 708) && (releasedX <= 854) && (releasedY <= 410) && (releasedY >= 60)) {
				if(!(originate.getLayoutX() == 708)) {
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
						tableauPiles.get(4).toFront();
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
			} else if ((releasedX >= 865) && (releasedX <= 1011) && (releasedY <= 410) && (releasedY >= 60)) {
				if(!(originate.getLayoutX() == 865)) {
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
						tableauPiles.get(5).toFront();
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
			} else if ((releasedX >= 1022) && (releasedX <= 1168) && (releasedY <= 410) && (releasedY >= 60) ) {
				if(!(originate.getLayoutX() == 1022)) {
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
						tableauPiles.get(6).toFront();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
				
			// Released Over Tableau Pile #8 Row 2 	
			} else if ((releasedX >= 155) && (releasedX <= 301) && (releasedY >= 410)) {
				if(!(originate.getLayoutX() == 155)) {
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
						tableauPiles.get(7).toFront();
						enableTopCards();
						this.tempCard.setVisible(false);
						this.tempCard = null;
					} else {
						ExitWindow.display("Illegal Move", "Illegal Move");
						tempCard.setLayoutX(oldX);
						tempCard.setLayoutY(oldY);
					}
				}
			
			// Released Over Tableau Pile #9 Row 2 	
			} else if ((releasedX >= 312) && (releasedX <= 458) && (releasedY >= 410)) {
					if(!(originate.getLayoutX() == 312)) {
						Card test = originate.getTopCard();
						if(addCard(8,1,test)) {
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
									moveTempCard(temp, tableauPiles.get(8));	
								}
							});
							originate.removeCard();
							tableauPiles.get(8).toFront();
							enableTopCards();
							this.tempCard.setVisible(false);
							this.tempCard = null;
						} else {
							ExitWindow.display("Illegal Move", "Illegal Move");
							tempCard.setLayoutX(oldX);
							tempCard.setLayoutY(oldY);
						}
					}
					
				// Released Over Tableau Pile #10 Row 2 	
				} else if ((releasedX >= 469) && (releasedX <= 615) && (releasedY >= 410)) {
										if(!(originate.getLayoutX() == 469)) {
											Card test = originate.getTopCard();
											if(addCard(9,1,test)) {
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
														moveTempCard(temp, tableauPiles.get(9));	
													}
												});
												originate.removeCard();
												tableauPiles.get(9).toFront();
												enableTopCards();
												this.tempCard.setVisible(false);
												this.tempCard = null;
											} else {
												ExitWindow.display("Illegal Move", "Illegal Move");
												tempCard.setLayoutX(oldX);
												tempCard.setLayoutY(oldY);
											}
										}
				// Released Over Tableau Pile #11 Row 2 	
				} else if ((releasedX >= 626) && (releasedX <= 772) && (releasedY >= 410)) {
						if(!(originate.getLayoutX() == 626)) {
							Card test = originate.getTopCard();
							if(addCard(10,1,test)) {
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
										moveTempCard(temp, tableauPiles.get(10));	
									}
								});
								originate.removeCard();
								tableauPiles.get(10).toFront();
								enableTopCards();
								this.tempCard.setVisible(false);
								this.tempCard = null;
							} else {
								ExitWindow.display("Illegal Move", "Illegal Move");
								tempCard.setLayoutX(oldX);
								tempCard.setLayoutY(oldY);
							}
						}
						
				// Released Over Tableau Pile #12 Row 2 	
				} else if ((releasedX >= 783) && (releasedX <= 929) && (releasedY >= 410)) {
						if(!(originate.getLayoutX() == 783)) {
							Card test = originate.getTopCard();
							if(addCard(11,1,test)) {
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
										moveTempCard(temp, tableauPiles.get(11));	
									}
								});
								originate.removeCard();
								tableauPiles.get(11).toFront();
								enableTopCards();
								this.tempCard.setVisible(false);
								this.tempCard = null;
							} else {
								ExitWindow.display("Illegal Move", "Illegal Move");
								tempCard.setLayoutX(oldX);
								tempCard.setLayoutY(oldY);
							}
						}
						
				// Released Over Tableau Pile #13 Row 2 	
				} else if ((releasedX >= 940) && (releasedX <= 1086) && (releasedY >= 410)) {
						if(!(originate.getLayoutX() == 940)) {
							Card test = originate.getTopCard();
							if(addCard(12,1,test)) {
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
										moveTempCard(temp, tableauPiles.get(12));	
									}
								});
								originate.removeCard();
								tableauPiles.get(12).toFront();
								enableTopCards();
								this.tempCard.setVisible(false);
								this.tempCard = null;
							} else {
								ExitWindow.display("Illegal Move", "Illegal Move");
								tempCard.setLayoutX(oldX);
								tempCard.setLayoutY(oldY);
							}
						}
				
				
			//Released Over Homecell Pile #1
			} else if ((releasedX >= 1283) && (releasedX <= 1439) && (releasedY >= 25) && (releasedY <= 229)) {
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
			} else if ((releasedX >= 1283) && (releasedX <= 1439) && (releasedY >= 229) && (releasedY <= 433)) {
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
			} else if ((releasedX >= 1283) && (releasedX <= 1439) && (releasedY >= 443) && (releasedY <= 637)) {
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
			} else if ((releasedX >= 1283) && (releasedX <= 1439) && (releasedY >= 637) && (releasedY <= 841)) {
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
					ExitWindow.display("Illegal Move", "Illegal Move");
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
		} return false;	
	}
	
	private boolean removeCard(int index, int pile) {
		if (pile == 1) {
			if(tableauPiles.get(index).removeTest()) {
				tableauPiles.get(index).removeCard();
				return true;
			} else {return false;}
		} else if (pile == 2) {
			if(homecellPiles.get(index).removeCard()) {
				homecellPiles.get(index).removeCard();
				return true;
			} else {return false;}
		} return false;
	}
	
	public int getNumberOfCards(int index, int pile) {
		int numberOfCards = 0;
		if (pile == 1) {
			TableauPile testPile1 = tableauPiles.get(index);
			numberOfCards = testPile1.getNumberOfCards();
		} else if (pile == 2) {
			HomecellPile testPile2 = homecellPiles.get(index);
			numberOfCards = testPile2.getNumberOfCards();
		} return numberOfCards;
	}
	
	public Card getTopCard(int index, int pile) {
		Card topCard = null;
		if (pile == 1) {
			topCard = tableauPiles.get(index).getTopCard();
		} else if (pile == 2) {
			topCard = homecellPiles.get(index).getTopCard();
		} return topCard;
	}
	
	public void defaultSkin() {
		super.setStyle(" -fx-background-color: #008000;");
		
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
