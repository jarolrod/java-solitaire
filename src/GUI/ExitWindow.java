package GUI;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;

import com.sun.prism.paint.Color;

import javafx.geometry.*;




public class ExitWindow {
	
	private static boolean _answer;
	
	public static boolean display(String title, String message) {
		Stage exit = new Stage();
		exit.initModality(Modality.APPLICATION_MODAL);
		exit.setTitle(title);
		exit.setMinWidth(500);
		exit.setMinHeight(300);
		Label exitMessage = new Label(message);
		exitMessage.setFont(Font.font("Arial", 30));
		
		Button yes = new Button("Yes");
		yes.setStyle("-fx-background-color: red;");
		yes.setFont(Font.font("Arial", 30));
		yes.setPrefSize(120, 50);
		
		
		Button no = new Button("No");
		no.setStyle("-fx-background-color: green;");
		no.setFont(Font.font("Arial", 30));
		no.setPrefSize(120, 50);
		
		yes.setOnAction(e -> {
			_answer = true;
			exit.close();
		});
		
		yes.setOnMouseEntered(e -> {
			yes.setCursor(Cursor.HAND);
		});
		
		
		
		no.setOnAction(e -> {
			_answer = false;
			exit.close();
		});
		
		no.setOnMouseEntered(e -> {
			no.setCursor(Cursor.HAND);
		});
		
		VBox layout = new VBox(10);
		HBox layout2 = new HBox();
		layout.setSpacing(40);
		layout2.setSpacing(20);
		layout2.getChildren().addAll(yes, no);
		layout2.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(exitMessage, layout2);
		layout.setAlignment(Pos.CENTER);
		
		Scene confirmScene = new Scene(layout);
		exit.setScene(confirmScene);
		exit.showAndWait();
		
		return _answer;
	}
}
