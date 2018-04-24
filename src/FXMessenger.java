import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class FXMessenger extends Application {

	public void start(Stage stage){
		
		VBox vboxContainer = new VBox(5);
		vboxContainer.setAlignment(Pos.CENTER);
		
		
		
		//Children of Container
		VBox vboxTextArea = new VBox();
		
		HBox hboxClientArea = new HBox();
		
		//Children of client area
		TextField clientText = new TextField();
		Button sendBtn = new Button("Send");
		Button endBtn = new Button("End Chat");
		
		//Add children
		vboxContainer.getChildren().addAll(vboxTextArea, hboxClientArea);
		hboxClientArea.getChildren().addAll(clientText, sendBtn, endBtn);
		
		
		
		Scene scene = new Scene(vboxContainer);
		stage.setScene(scene);
		stage.setTitle("JavaFX Messenger");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

