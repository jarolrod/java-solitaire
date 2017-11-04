package controllers;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import GUI.ExitWindow;
import GUI.AboutWindow;

public class root {

	@FXML MenuBar _gameMenu;
	@FXML Button freecell;
	@FXML Button bakers;
	@FXML Button acesUp;
	@FXML VBox scene;
	@FXML AnchorPane content;
	

	
	public void initialize() {
		scene.setPrefSize(1464, 900);
		scene.setMinSize(1464, 900);
		scene.setMinHeight(900);
		content.setPrefSize(1464, 900);
		content.setMinSize(1464, 900);
		content.setMinHeight(900);

		String freecellLocation = "/assets/root_assets/root_freecell.png";
		freecell.setStyle("-fx-background-image: url(" + freecellLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		
		freecell.setOnMouseEntered(e -> {
			freecell.setCursor(Cursor.HAND);
			freecell.setStyle("-fx-border-style: solid; -fx-border-color: #ffcc00;" + "-fx-background-image: url(" + freecellLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		});
		
		freecell.setOnMouseExited(e -> {
			freecell.setStyle("-fx-border-color: black;" + "-fx-background-image: url(" + freecellLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		});
	
		String bakersLocation = "/assets/root_assets/bakers_dozen.png";
		bakers.setStyle("-fx-background-image: url(" + bakersLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		bakers.setOnMouseEntered(e -> {
			bakers.setCursor(Cursor.HAND);
			bakers.setStyle("-fx-border-style: solid; -fx-border-color: #ffcc00;" + "-fx-background-image: url(" + bakersLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		});
		
		bakers.setOnMouseExited(e -> {
			bakers.setStyle("-fx-border-color: black;" + "-fx-background-image: url(" + bakersLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		});
		
		String infoLocation = "/assets/root_assets/aces_up.png";
		acesUp.setStyle("-fx-background-image: url(" + infoLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		acesUp.setOnMouseEntered(e -> {
			acesUp.setCursor(Cursor.HAND);
			acesUp.setStyle("-fx-border-style: solid; -fx-border-color: #ffcc00;" + "-fx-background-image: url(" + infoLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		});
		
		acesUp.setOnMouseExited(e -> {
			acesUp.setStyle("-fx-border-color: black;" + "-fx-background-image: url(" + infoLocation +"); -fx-background-repeat: no-repeat;-fx-background-size: contain;" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		});
	}
	
	// Game Methods
	@FXML protected void freecellButtonClick(Event e) throws IOException {
		Node node = (Node) e.getSource();
		Stage main = (Stage) node.getScene().getWindow();
		Parent freecellFXML = FXMLLoader.load(getClass().getResource("/fxml/freecellGame.fxml"));
		freecellFXML.getStylesheets().add("/stylesheets/freecellGame.css");
		Scene freecellScene = new Scene(freecellFXML);
		main.setOnCloseRequest(b -> {
			boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
			if(response) main.close();
			b.consume();
		});
		main.setScene(freecellScene);
		main.show();
	}
	
	
	@FXML protected void freecellMenuClick() throws IOException {
		Stage main = (Stage) _gameMenu.getScene().getWindow();
		Parent freecellFXML = FXMLLoader.load(getClass().getResource("/fxml/freecellGame.fxml"));
		freecellFXML.getStylesheets().add("/stylesheets/freecellGame.css");
		Scene freecellScene = new Scene(freecellFXML);
		main.setOnCloseRequest(b -> {
			boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
			if(response) main.close();
			b.consume();
		});
		main.setScene(freecellScene);
		main.show();
	}
	
	@FXML protected void bakersDozenButtonClick(Event e) throws IOException {
		Node node = (Node) e.getSource();
		Stage main = (Stage) node.getScene().getWindow();
		Parent bakersDozenFXML = FXMLLoader.load(getClass().getResource("/fxml/bakersDozenGame.fxml"));
		bakersDozenFXML.getStylesheets().add("/stylesheets/bakersDozenGame.css");
		Scene bakersDozenScene = new Scene(bakersDozenFXML);
		main.setOnCloseRequest(b -> {
			boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
			if(response) main.close();
			b.consume();
		});
		main.setScene(bakersDozenScene);
		main.show();
	}
	
	@FXML protected void bakersDozenMenuClick() throws IOException {
		Stage main = (Stage) _gameMenu.getScene().getWindow();
		Parent bakersDozenFXML = FXMLLoader.load(getClass().getResource("/fxml/bakersDozenGame.fxml"));
		bakersDozenFXML.getStylesheets().add("/stylesheets/bakersDozenGame.css");
		Scene bakersDozenScene = new Scene(bakersDozenFXML);
		main.setOnCloseRequest(b -> {
			boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
			if(response) main.close();
			b.consume();
		});	
		main.setScene(bakersDozenScene);
		main.show();
	}
	
	@FXML protected void acesUpButtonClick(Event e) throws IOException {
		Node node = (Node) e.getSource();
		Stage main = (Stage) node.getScene().getWindow();
		Parent acesUpFXML = FXMLLoader.load(getClass().getResource("/fxml/acesUpGame.fxml"));
		acesUpFXML.getStylesheets().add("/stylesheets/acesUpGame.css");
		Scene acesUpScene = new Scene(acesUpFXML);
		main.setOnCloseRequest(b -> {
			boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
			if(response) main.close();
			b.consume();
		});
		main.setScene(acesUpScene);
		main.show();
	}
	
	@FXML protected void acesUpMenuClick() throws IOException {
		Stage main = (Stage) _gameMenu.getScene().getWindow();
		Parent acesUpFXML = FXMLLoader.load(getClass().getResource("/fxml/acesUpGame.fxml"));
		acesUpFXML.getStylesheets().add("/stylesheets/acesUpGame.css");
		Scene acesUpScene = new Scene(acesUpFXML);
		main.setOnCloseRequest(b -> {
			boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
			if(response) main.close();
			b.consume();
		});	
		main.setScene(acesUpScene);
		main.show();
	}
	
	@FXML protected void exitMenuClick() {
		boolean response = ExitWindow.display("Confirm", "Are you sure you want to quit now?");
		if (response) {
			Stage main = (Stage) _gameMenu.getScene().getWindow();
			main.close();
		} 
	}
	
	@FXML protected void aboutMenuClick() {
		AboutWindow.display();
	}
}
