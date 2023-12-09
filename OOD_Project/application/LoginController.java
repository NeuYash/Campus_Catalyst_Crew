package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

	@FXML
	private Button close;

	@FXML
	private Button loginBtn;

	@FXML
	private AnchorPane main_form;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	public void close() {
		System.exit(0);
	}

	private Connection connect;
	private PreparedStatement preparedStatment;
	private ResultSet result;
	
	private double x = 0;
	private double y = 0;
	
	
	private String pageView = "StudentView.fxml";
	

	public void loginAdmin() {

		Alert alert;

		String sql = "SELECT * FROM admindata WHERE username = ? and password = ?";

		connect = Database.conn();

		try {

			preparedStatment = connect.prepareStatement(sql);
			preparedStatment.setString(1, username.getText());
			preparedStatment.setString(2, password.getText());

			result = preparedStatment.executeQuery();
			
			if (username.getText().isEmpty() || password.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();

			} else {

			}
			if (result.next()) {

	            Data.username = username.getText();
	            Data.id = result.getInt("id");

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Message");
				alert.setHeaderText(null);
				alert.setContentText("Successfully Login!");
				alert.showAndWait();

				loginBtn.getScene().getWindow().hide();
				
				if(Data.username.equals("admin"))
					pageView = "Dashboard.fxml";
				else {
					new JavaEmailSender().createAndSendEmail("pawaryash837@gmail.com", "Student ID:"+ Data.id+" Login",
			                Data.username+" has LoggedIn at: "+LocalDateTime.now());
				}
				
				Parent root = FXMLLoader.load(getClass().getResource(pageView));

				Stage stage = new Stage();
				Scene scene = new Scene(root);

	                       root.setOnMousePressed((MouseEvent event) ->{
	                           x = event.getSceneX();
	                           y = event.getSceneY();
                       });	                       
	                       root.setOnMouseDragged((MouseEvent event) ->{
	                           stage.setX(event.getScreenX() - x);
	                           stage.setY(event.getScreenY() - y);
	                       });
	                       
				
				stage.initStyle(StageStyle.TRANSPARENT);
				
				

				stage.setScene(scene);
				stage.show();

			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Wrong Username/Password");
				alert.showAndWait();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
