import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class MessageHBox extends HBox{
	StackPane stackP = new StackPane();
	Rectangle rectBG = new Rectangle();
	Text message;
	String textMsg;
	
	public MessageHBox(String textMsg, boolean alignment) {
		super();
		this.textMsg = textMsg;
		message = new Text(textMsg);
		message.setWrappingWidth(175);
		
		double textLengthW = message.getLayoutBounds().getWidth() + 10;
		double textLengthH = message.getLayoutBounds().getHeight() + 10;
		
		rectBG.setWidth(textLengthW);
		rectBG.setHeight(textLengthH);
		rectBG.setArcWidth(20);
		rectBG.setArcHeight(20);
		
		stackP.getChildren().addAll(rectBG, message);
		
		this.getChildren().add(stackP);
		
		if(alignment) {
			this.setAlignment(Pos.BOTTOM_LEFT);
		}else {
			this.setAlignment(Pos.BOTTOM_RIGHT);

		}
		
		
		
		rectBG.getStyleClass().add("rect");
		message.getStyleClass().add("messages");
	}
	
}
