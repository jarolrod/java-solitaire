package GUI;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertWindow {
	
	public static void display(String title, String message) {
		Stage alertWindow = new Stage();
		alertWindow.initModality(Modality.APPLICATION_MODAL);
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(300);
		
		Label messageLabel = new Label(message);
		Button closeButton = new Button("Ok");
		closeButton.setOnAction(e -> alertWindow.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(messageLabel, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene alertScene = new Scene(layout);
		alertWindow.setScene(alertScene);
		alertWindow.showAndWait();
	}
}
