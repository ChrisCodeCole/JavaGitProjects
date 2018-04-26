import javafx.application.Application;	
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class FXMessenger extends Application {

	public void start(Stage stage){
		
		VBox vboxContainer = new VBox(5);
		vboxContainer.setAlignment(Pos.CENTER);
		vboxContainer.setPadding(new Insets(0, 50, 0 , 50));

		
		
		//Children of Container
		VBox vboxTextArea = new VBox();
			// TextArea Styles
			vboxTextArea.setStyle("-fx-background-color: rgb(230,230,230);"
								+ "-fx-height: 10em;"
								+ "-fx-width: 10em;");
			
			// ------------TEST MESSAGE -----------
			// Message Containers
			MessageHBox messages1 = new MessageHBox("Client sent out messages", true);
			MessageHBox messages2= new MessageHBox("Received text messagsssssssssssssssssssssssssssssssssssssssssssssssssss", false);
			
			vboxTextArea.getChildren().addAll(messages1, messages2);

			
			
		HBox hboxClientArea = new HBox();
		
		//Children of client area
		TextField clientText = new TextField();
		Button sendBtn = new Button("Send");
		Button endBtn = new Button("End Chat");
		
		//Add children
		vboxContainer.getChildren().addAll(vboxTextArea, hboxClientArea);
		hboxClientArea.getChildren().addAll(clientText, sendBtn, endBtn);
		
		
		
		Scene scene = new Scene(vboxContainer, 500 , 400);
		scene.getStylesheets().add("./FXstylesheet.css");
		stage.setScene(scene);
		stage.setTitle("JavaFX Messenger");
		stage.show();
		System.out.println(vboxTextArea.widthProperty());
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

