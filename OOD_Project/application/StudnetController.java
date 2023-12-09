package application;


import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class StudnetController implements Initializable{
	@FXML
	private Button addStudents_addBtn;

	@FXML
	private DatePicker addStudents_birth;

	@FXML
	private Button addStudents_btn;

	@FXML
	private Button addStudents_clearBtn;

	@FXML
	private TableColumn<Student, String> addStudents_col_birthdate;

	@FXML
	private TableColumn<Student, String> addStudents_col_course;

	@FXML
	private TableColumn<Student, String> addStudents_col_firstName;

	@FXML
	private TableColumn<Student, String> addStudents_col_gender;

	@FXML
	private TableColumn<Student, String> addStudents_col_lastName;

	@FXML
	private TableColumn<Student, String> addStudents_col_status;

	@FXML
	private TableColumn<Student, String> addStudents_col_studentNum;

	@FXML
	private TableColumn<Student, String> addStudents_col_year;

	@FXML
	private ComboBox<?> addStudents_course;

	@FXML
	private Button addStudents_deleteBtn;

	@FXML
	private TextField addStudents_firstName;

	@FXML
	private AnchorPane addStudents_form;

	@FXML
	private ComboBox<String> addStudents_gender;

	@FXML
	private ImageView addStudents_imageView;

	@FXML
	private Button addStudents_insertBtn;

	@FXML
	private TextField addStudents_lastName;

	@FXML
	private TextField addStudents_search;

	@FXML
	private ComboBox<?> addStudents_status;

	@FXML
	private TextField addStudents_studentNum;

	@FXML
	private TableView<Student> addStudents_tableView;

	@FXML
	private Button addStudents_updateBtn;

	@FXML
	private ComboBox<?> addStudents_year;

	@FXML
	private Button availableCourse_addBtn;

	@FXML
	private Button availableCourse_clearBtn;

	@FXML
	private TableColumn<Course, String> availableCourse_col_course;

	@FXML
	private TableColumn<Course, String> availableCourse_col_degree;

	@FXML
	private TableColumn<Course, String> availableCourse_col_description;

	@FXML
	private TextField availableCourse_course;

	@FXML
	private TextField availableCourse_degree;

	@FXML
	private Button availableCourse_deleteBtn;

	@FXML
	private TextField availableCourse_description;

	@FXML
	private AnchorPane availableCourse_form;

	@FXML
	private TableView<Course> availableCourse_tableView;

	@FXML
	private Button availableCourse_updateBtn;

	@FXML
	private Button availablecourses_btn;

	@FXML
	private FontAwesomeIconView close;

	@FXML
	private Button home_btn;

	@FXML
	private AnchorPane home_form;

	@FXML
	private Label home_totalEnrolled;

	@FXML
	private AreaChart<?, ?> home_totalEnrolledChart;

	@FXML
	private Label home_totalFemale;

	@FXML
	private AreaChart<?, ?> home_totalFemaleChart;

	@FXML
	private Label home_totalMale;

	@FXML
	private AreaChart<?, ?> home_totalMaleChart;

	@FXML
	private Button logout;

	@FXML
	private AnchorPane main_form;

	@FXML
	private FontAwesomeIconView minimize;

	@FXML
	private Button studentGrade_btn;

	@FXML
	private Button studentGrade_clearBtn;

	@FXML
	private TableColumn<Student, String> studentGrade_col_course;

	@FXML
	private TableColumn<Student, String> studentGrade_col_final;

	@FXML
	private TableColumn<Student, String> studentGrade_col_firstsem;

	@FXML
	private TableColumn<Student, String> studentGrade_col_secondsem;

	@FXML
	private TableColumn<Student, String> studentGrade_col_studentNum;

	@FXML
	private TableColumn<Student, String> studentGrade_col_year;

	@FXML
	private TextField studentGrade_course;

	@FXML
	private TextField studentGrade_firstsem;

	@FXML
	private AnchorPane studentGrade_form;

	@FXML
	private TextField studentGrade_search;

	@FXML
	private TextField studentGrade_secondsem;

	@FXML
	private TextField studentGrade_studentNum;

	@FXML
	private TableView<Student> studentGrade_tableView;

	@FXML
	private Button studentGrade_updateBtn;

	@FXML
	private TextField studentGrade_year;

	@FXML
	private Label username;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;	
	private ObservableList<Student> studentGradesList;
	private String course;
	

	@FXML
	private void generateTranscriptPDF(ActionEvent event) {
		String path="/Users/yashpawar/eclipse-workspace/OOD_Project/PDFReports/";
		Document doc=new Document();
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream(path+username.getText()+"Transcript.pdf"));
			doc.open();
			PdfPTable tbl=new PdfPTable(6);
			PdfPCell titleCell = new PdfPCell(new Phrase("Transcript"));
	        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        titleCell.setColspan(6);
	        tbl.addCell(titleCell);
			tbl.addCell("StudentName");
			tbl.addCell("Year");
			tbl.addCell("Course");
			tbl.addCell("FirstSem");
			tbl.addCell("SecondSem");
			tbl.addCell("Final");
			studentGrade_tableView.getItems().forEach(row -> {
			tbl.addCell(new PdfPCell(new Phrase(username.getText())));
			tbl.addCell(new PdfPCell(new Phrase(row.getYear())));
			tbl.addCell(new PdfPCell(new Phrase(row.getCourse())));
			tbl.addCell(new PdfPCell(new Phrase(Double.toString(row.getFirstSem()))));
			tbl.addCell(new PdfPCell(new Phrase(Double.toString(row.getSecondSem()))));
			tbl.addCell(new PdfPCell(new Phrase(Double.toString(row.getFinals()))));
		});
			doc.add(tbl);
			doc.close();
			Alert al=new Alert(AlertType.INFORMATION);
			al.setTitle("Transcript Downloaded");
			al.setContentText("Transcript Downloaded Successfully");
			al.show();
			
		}catch(Exception e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setTitle("Error in PDF Download");
			al.show();
		}
	}
	
	public ResultSet getStudentData() {
		String sql = "select * from student where studentNum = ?";

		connect = Database.conn();
		try {
			prepare = connect.prepareStatement(sql);
			prepare.setInt(1,Data.id);
			result = prepare.executeQuery();
			if(result.next()){
				username.setText(result.getString("firstName"));
				course = result.getString("course");
				}
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ObservableList<Student> studentGradesListData() {

		ObservableList<Student> listData = FXCollections.observableArrayList();

		String sql = "SELECT * FROM grades where studentNum = ?";

		connect = Database.conn();

		try {
			Student student;

			prepare = connect.prepareStatement(sql);
			prepare.setInt(1,Data.id);
			result = prepare.executeQuery();
			

			while (result.next()) {
				student = new Student(result.getInt("studentNum"), result.getString("cur_year"),
						result.getString("course"), result.getDouble("first_sem"), result.getDouble("second_sem"),
						result.getDouble("final"));

				listData.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	public void studentGradesShowListData() {
		studentGradesList = studentGradesListData();

		studentGrade_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
		studentGrade_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
		studentGrade_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
		studentGrade_col_firstsem.setCellValueFactory(new PropertyValueFactory<>("firstSem"));
		studentGrade_col_secondsem.setCellValueFactory(new PropertyValueFactory<>("secondSem"));
		studentGrade_col_final.setCellValueFactory(new PropertyValueFactory<>("finals"));
		studentGrade_tableView.setItems(studentGradesList);

	}


	public ObservableList<Course> availableCourseListData() {

		ObservableList<Course> listData = FXCollections.observableArrayList();

		String sql = "SELECT * FROM course";

		connect = Database.conn();

		try {
			Course course;
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				course = new Course(result.getString("course"), result.getString("description"),
						result.getString("degree"));

				listData.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	private ObservableList<Course> availableCourseList;

	public void availableCourseShowListData() {
		availableCourseList = availableCourseListData();

		availableCourse_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
		availableCourse_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
		availableCourse_col_degree.setCellValueFactory(new PropertyValueFactory<>("degree"));

		availableCourse_tableView.setItems(availableCourseList);

	}
	

	public ObservableList<Student> addStudentsListData() {

		ObservableList<Student> listStudents = FXCollections.observableArrayList();
		
		
		String sql = "SELECT * FROM student where course = ?";

		connect = Database.conn();

		try {
			
			Student student;
			prepare = connect.prepareStatement(sql);		
			
			prepare.setString(1,course);

			result = prepare.executeQuery();

			while (result.next()) {
				student = new Student(result.getInt("studentNum"), result.getString("cur_year"), result.getString("course"),
						result.getString("firstName"), result.getString("lastName"), result.getString("gender"),
						result.getDate("birth"), result.getString("status"), result.getString("image"));

				listStudents.add(student);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStudents;
	}

	private ObservableList<Student> addStudentsList;

	public void addStudentsShowListData() {

		addStudentsList = addStudentsListData();

		addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
		addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
		addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

		addStudents_tableView.setItems(addStudentsList);
	}


	public void logout() {

		try {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Message");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to logout?");

			Optional<ButtonType> option = alert.showAndWait();

			if (option.get().equals(ButtonType.OK)) {

				logout.getScene().getWindow().hide();

				Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);

				stage.initStyle(StageStyle.TRANSPARENT);

				stage.setScene(scene);
				stage.show();

			} else {
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void switchForm(ActionEvent event) {
		
		 if (event.getSource() == addStudents_btn) {
			addStudents_form.setVisible(true);
			availableCourse_form.setVisible(false);
			studentGrade_form.setVisible(false);

			addStudentsShowListData();


		} else if (event.getSource() == availablecourses_btn) {
			addStudents_form.setVisible(false);
			availableCourse_form.setVisible(true);
			studentGrade_form.setVisible(false);

			availableCourseShowListData();

		} else if (event.getSource() == studentGrade_btn) {
			addStudents_form.setVisible(false);
			availableCourse_form.setVisible(false);
			studentGrade_form.setVisible(true);

			studentGradesShowListData();

		}

	}

	public void close() {
		System.exit(0);
	}

	public void minimize() {
		Stage stage = (Stage) main_form.getScene().getWindow();
		stage.setIconified(true);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		getStudentData();
		addStudentsShowListData();
		availableCourseShowListData();
		studentGradesShowListData();
		
	}

}
