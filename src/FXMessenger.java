import javafx.application.Application;		
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class FXMessenger extends Application {

	@Override
	public void start(Stage stage){
		
		VBox vboxContainer = new VBox(5);
		vboxContainer.setAlignment(Pos.CENTER);
		vboxContainer.setPadding(new Insets(0, 50, 0 , 50));
		
		
		
		//Children of Container
		ScrollPane scrollTextArea = new ScrollPane();
		
		VBox vboxTextArea = new VBox();
		scrollTextArea.setContent(vboxTextArea);
		vboxTextArea.setStyle("	-fx-background-color: royalblue;");
			// TextArea Styles
			scrollTextArea.setMinSize(405,300);
			scrollTextArea.setMaxSize(405,300);
			
		     vboxTextArea.setPrefWidth(385);
		     vboxTextArea.setPrefHeight(300);



			
			// ------------TEST MESSAGE -----------
			// Message Containers
			MessageHBox messages1 = new MessageHBox("Received messages", true);
			MessageHBox messages2= new MessageHBox("Client text messagsssssssssssssssssssssssssssssssssssssssssssssssssss", false);			
			


		vboxTextArea.getChildren().addAll(messages1,messages2);
		HBox hboxClientArea = new HBox();
		hboxClientArea.setPadding(new Insets(5, 0, 0, 0));
		hboxClientArea.setSpacing(10);
		
		//Children of client area
		TextField clientText = new TextField();
		Button sendBtn = new Button("Send");
		Button endBtn = new Button("End Chat");
		
		clientText.setPromptText("Send message");
		clientText.setMinSize(230,50);
		clientText.setMaxSize(230,50);
		
		
		//CSS style sheet classes
		sendBtn.getStyleClass().add("buttons");
		endBtn.getStyleClass().add("buttons");
		sendBtn.getStyleClass().add("sendBtn");
		endBtn.getStyleClass().add("endBtn");
		clientText.getStyleClass().add("clientText");
		scrollTextArea.getStyleClass().add("textArea");
		
		//Add children
		vboxContainer.getChildren().addAll(scrollTextArea, hboxClientArea);
		hboxClientArea.getChildren().addAll(clientText, sendBtn, endBtn);
		
		
		
		Scene scene = new Scene(vboxContainer, 500 , 400);
		scene.getStylesheets().add("./FXstylesheet.css");
		stage.setScene(scene);
		stage.setTitle("JavaFX Messenger");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

