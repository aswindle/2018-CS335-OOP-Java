import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.*;

public class JavaFXTest extends Application {

	public static void main(String[] args){
		launch();
	}
	
	
	public void start(Stage testStage) {
		testStage.setTitle("Test Application");
		
		FlowPane rootNode = new FlowPane();
		
		Scene testScene = new Scene(rootNode, 300, 200);
		
		testStage.setScene(testScene);
		
		Label text1 = new Label("Here's some text");
		Label text2 = new Label("Here's some more text");
		
		rootNode.getChildren().addAll(text1, text2);
		
		testStage.show();
		
		
	}

	

}
