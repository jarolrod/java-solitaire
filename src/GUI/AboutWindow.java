package GUI;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AboutWindow {

	public static void display() {
		Stage aboutWindow = new Stage();
		aboutWindow.initModality(Modality.APPLICATION_MODAL);
		aboutWindow.setTitle("About");
		aboutWindow.setMinWidth(300);
		Label messageLabel = new Label("Hello");
		
		Button close = new Button("close");
		
		close.setOnAction(e -> {
			aboutWindow.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(messageLabel, close);
		layout.setAlignment(Pos.CENTER);
		
		Scene confirmScene = new Scene(layout);
		aboutWindow.setScene(confirmScene);
		aboutWindow.showAndWait();
	}
}
