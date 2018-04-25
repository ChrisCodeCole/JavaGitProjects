import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.scene.text.Text;

public class FXMessenger extends Application {

	public void start(Stage stage){
		Text clientMSG = new Text("Test client message");
		Text receivedMSG = new Text("Test received message");
		
		VBox vboxContainer = new VBox(5);
		vboxContainer.setAlignment(Pos.CENTER);
		vboxContainer.setPadding(new Insets(50));

//        vboxContainer.setStyle("-fx-padding: 0 10pc 0 2pc;");

		
		
		//Children of Container
		VBox vboxTextArea = new VBox();
			// TextArea Styles
			vboxTextArea.setStyle("-fx-background-color: rgb(230,230,230);"
								+ "-fx-height: 10em;"
								+ "-fx-width: 10em;");
			
			// ------------TEST MESSAGE -----------
			// Message Containers
			HBox messages1 = new HBox();
			HBox messages2= new HBox();
			StackPane stackTest1 = new StackPane();
			StackPane stackTest2 = new StackPane();
			
			Rectangle rectTest = new Rectangle();
			rectTest.setX(50);
			rectTest.setY(50);
			rectTest.setWidth(120);
			rectTest.setHeight(25);
			rectTest.setArcWidth(20);
			rectTest.setArcHeight(20);
			
			Rectangle rectTest2 = new Rectangle();
			rectTest2.setX(50);
			rectTest2.setY(50);
			rectTest2.setWidth(120);
			rectTest2.setHeight(25);
			rectTest2.setArcWidth(20);
			rectTest2.setArcHeight(20);
			
			stackTest1.getChildren().addAll(rectTest,clientMSG);
			stackTest2.getChildren().addAll(rectTest2,receivedMSG);
			
			
			messages1.getChildren().add(stackTest1);
			messages1.setAlignment(Pos.BOTTOM_LEFT);
			
			messages2.getChildren().add(stackTest2);
			messages2.setAlignment(Pos.BOTTOM_RIGHT);
			
			vboxTextArea.getChildren().addAll(messages1, messages2);
			
			//Message styles
			rectTest.getStyleClass().add("rect");
			rectTest2.getStyleClass().add("rect");
			clientMSG.getStyleClass().add("messages");
			receivedMSG.getStyleClass().add("messages");
			
			
		HBox hboxClientArea = new HBox();
		
		//Children of client area
		TextField clientText = new TextField();
		Button sendBtn = new Button("Send");
		Button endBtn = new Button("End Chat");
		
		//Add children
		vboxContainer.getChildren().addAll(vboxTextArea, hboxClientArea);
		hboxClientArea.getChildren().addAll(clientText, sendBtn, endBtn);
		
		
		
		Scene scene = new Scene(vboxContainer, 500 , 500);
		scene.getStylesheets().add("./FXstylesheet.css");
		stage.setScene(scene);
		stage.setTitle("JavaFX Messenger");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}

