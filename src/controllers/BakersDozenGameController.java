package controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import GUI.ExitWindow;
import code.BakersDozenGame;
import GUI.AboutWindow;



public class BakersDozenGameController {
	
	@FXML VBox scene;
	@FXML MenuBar _gameMenu;
	private BakersDozenGame newGame = new BakersDozenGame();
	
	public void initialize() {
		scene.getChildren().add(newGame);
		scene.setMinHeight(900);
	}
	
	
	@FXML protected void freecellMenuClick() throws IOException {
		boolean response = ExitWindow.display("Confirm", "Are you sure you would like to exit out of your current game and start a new Freecell Game?");
		if (response) {
			Stage main = (Stage) _gameMenu.getScene().getWindow();
			Parent freecellFXML = FXMLLoader.load(getClass().getResource("/fxml/freecellGame.fxml"));
			freecellFXML.getStylesheets().add("/stylesheets/freecellGame.css");
			Scene freecellScene = new Scene(freecellFXML);
			main.setScene(freecellScene);
			main.show();
		}	
	}
	
	@FXML protected void bakersDozenMenuClick() throws  IOException {
		boolean response = ExitWindow.display("Confirm", "Are you sure you would like to exit out of your current game and start a new Baker's Dozen Game?");
		if (response) {
			Stage main = (Stage) _gameMenu.getScene().getWindow();
			Parent bakersDozenFXML = FXMLLoader.load(getClass().getResource("/fxml/bakersDozenGame.fxml"));
			bakersDozenFXML.getStylesheets().add("/stylesheets/bakersDozenGame.css");
			Scene bakersDozenScene = new Scene(bakersDozenFXML);
			main.setScene(bakersDozenScene);
			main.show();
		}
	}
	
	@FXML protected void acesUpMenuClick() throws IOException {
		boolean response = ExitWindow.display("Confirm", "Are you sure you would like to exit out of your current game and start a new Baker's Dozen Game?");
		if (response) {
			Stage main = (Stage) _gameMenu.getScene().getWindow();
			Parent acesUpFXML = FXMLLoader.load(getClass().getResource("/fxml/acesUpGame.fxml"));
			acesUpFXML.getStylesheets().add("/stylesheets/acesUpGame.css");
			Scene acesUpScene = new Scene(acesUpFXML);
			main.setScene(acesUpScene);
			main.show();
		}
	}
	
	@FXML protected void exitMenuClick() {
		boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit your Baker's Dozen Game?");
		if (response) {
			Stage main = (Stage) _gameMenu.getScene().getWindow();
			main.close();
		} 
	}
	
	@FXML protected void defaultSkin() {
		newGame.defaultSkin();
	}
	
	@FXML protected void koiSkin() {
		newGame.koiSkin();
	}
	
	@FXML protected void jungleSkin() {
		newGame.jungleSkin();
	}
	
	@FXML protected void desertSkin() {
		newGame.desertSkin();
	}
	
	@FXML protected void aboutMenuClick() {
		AboutWindow.display();
	}
	
	@FXML protected void gameInfoMenuClick() {
		
	}

}
