//
// file name: client.java
// programmer: Sean Yang
// date: April 17, 2018
// desc: A socket program for multi-client server.
//

import java.io.*;	
import java.net.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class client extends Application
{
	
	public static boolean prog_end_flag = false;
	public static boolean send_flag = false;
	public VBox vboxContainer;
	public VBox vboxTextArea;
	public HBox hboxClientArea;
	
	@Override
	public void start(Stage stage){
		System.out.println("Test");
		vboxContainer = new VBox(5);
		vboxContainer.setAlignment(Pos.CENTER);
		vboxContainer.setPadding(new Insets(0, 50, 0 , 50));
		
		
		
		//Children of Container
		ScrollPane scrollTextArea = new ScrollPane();
		
		vboxTextArea = new VBox();
		scrollTextArea.setContent(vboxTextArea);
		vboxTextArea.setStyle("	-fx-background-color: royalblue;");
			// TextArea Styles
			scrollTextArea.setMinSize(405,300);
			scrollTextArea.setMaxSize(405,300);
			
		     vboxTextArea.setPrefWidth(385);
		     vboxTextArea.setPrefHeight(300);



		hboxClientArea = new HBox();
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
		
		
		//Create sockets and readers. Connect to port
		try {
			Socket s = new Socket("localhost", 2010);

			SocketReader1 s_reader = new SocketReader1(s, this);
			System.out.println(this);
			DataOutputStream s_writer = new DataOutputStream(s.getOutputStream());
			
			Thread t = new Thread(s_reader);
			t.start();

		
			//Add button functionality for send message
			sendBtn.setOnAction(new EventHandler<ActionEvent>() {
	    	  
	    	  @Override
	    	  public void handle(ActionEvent e)
	    	  {
	    		String sendTxt = clientText.getText();
	  			if(sendTxt.length() > 0) {
	  				MessageHBox sendMessage = new MessageHBox(sendTxt, false);
	  				vboxTextArea.getChildren().add(sendMessage);
	  				
					try {
					s_writer.writeBytes(sendTxt+"\n");
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
	  				}
	  			}
	    	  }
			});
			
			} catch (IOException e) {
				System.out.println("Error in connecting to the server");
				System.out.println(e);
				System.exit(1);
			};
		
		
		
		Scene scene = new Scene(vboxContainer, 500 , 400);
		scene.getStylesheets().add("./FXstylesheet.css");
		stage.setScene(scene);
		stage.setTitle("JavaFX Messenger");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String args[])
	{
		
		Application.launch(args);

	}

}


