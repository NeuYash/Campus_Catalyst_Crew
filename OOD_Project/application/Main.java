package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


public class Main extends Application {
	
	private double x = 0;
	private double y = 0;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
//		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//		Scene scene = new Scene(root);
//		
//		root.setOnMousePressed((MouseEvent event) ->{
//			x = event.getSceneX();
//			y = event.getSceneY();
//		});
//		
//		
//		primaryStage.initStyle(StageStyle.TRANSPARENT);
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
		Parent root = new FXMLLoader(getClass().getResource("Login.fxml")).load();
		Scene scene = new Scene(root, 600, 400);
		
		root.setOnMousePressed((MouseEvent event) ->{
		x = event.getSceneX();
		y = event.getSceneY();
		});

		
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Campus Catalyst");
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
