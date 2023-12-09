package application;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController implements Initializable   {

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
	private AnchorPane addStudents_form;

	@FXML
	private ComboBox<String> addStudents_gender;

	@FXML
	private ImageView addStudents_imageView;

	@FXML
	private Button addStudents_insertBtn;

//	@FXML
//	private TextField addStudents_search;

	@FXML
	private ComboBox<?> addStudents_status;

	@FXML
	private TableView<Student> addStudents_tableView;

	@FXML
	private Button addStudents_updateBtn;
	
	@FXML
	private Button studentGrade_downloadBtn;

	@FXML
	private ComboBox<?> addStudents_year;

	@FXML
	private Button availableCourse_addBtn;

	@FXML
	private Button availableCourse_clearBtn;
	
	@FXML
	private Button availableCourse_downloadBtn;

	@FXML
	private TableColumn<Course, String> availableCourse_col_course;

	@FXML
	private TableColumn<Course, String> availableCourse_col_degree;

	@FXML
	private TableColumn<Course, String> availableCourse_col_description;
	
	@FXML
	private Button availableCourse_deleteBtn;

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
	private BarChart<?, ?> home_totalEnrolledChart;

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
	private AnchorPane studentGrade_form;

//	@FXML
//	private TextField studentGrade_search;

	@FXML
	private TableView<Student> studentGrade_tableView;

	@FXML
	private Button studentGrade_updateBtn;
	
	@FXML
	private Button studentTranscript_downloadBtn;
	
	@FXML
	private Button addStudents_downloadBtn;

	@FXML
	private Label username;
	
	@FXML
	private TextField addStudents_firstName;
	
	@FXML
	private TextField addStudents_lastName;
	
	@FXML
	private TextField addStudents_studentNum;
	
	@FXML
	private TextField availableCourse_course;

	@FXML
	private TextField availableCourse_degree;
	
	@FXML
	private TextField availableCourse_description;
	
	@FXML
	private TextField studentGrade_course;

	@FXML
	private TextField studentGrade_firstsem;

	@FXML
	private TextField studentGrade_secondsem;

	@FXML
	private TextField studentGrade_studentNum;

	@FXML
	private TextField studentGrade_year;

	private Connection connect;
	private PreparedStatement prepare;
	private java.sql.Statement statement;
	private ResultSet result;

	private Image image;
	

	private String[] yearList = { "First Year", "Second Year", "Third Year", "Fourth Year" };

	private ObservableList<Student> studentGradesList;
	
	@FXML
	private void addStudentsDownload(ActionEvent event) {
		String path="/Users/yashpawar/eclipse-workspace/OOD_Project/PDFReports/";
		Document doc=new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path+"CurStudents.pdf"));
			doc.open();
			PdfPTable tbl=new PdfPTable(8);
			PdfPCell titleCell = new PdfPCell(new Phrase("Current Students"));
	        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        titleCell.setColspan(8);
	        tbl.addCell(titleCell);
			tbl.addCell("StudentID");
			tbl.addCell("Year");
			tbl.addCell("Course");
			tbl.addCell("FirstName");
			tbl.addCell("LastName");
			tbl.addCell("Gender");
			tbl.addCell("BirthDate");
			tbl.addCell("Status");
			addStudents_tableView.getItems().forEach(row -> {
			tbl.addCell(new PdfPCell(new Phrase(Integer.toString(row.getStudentNum()))));
			tbl.addCell(new PdfPCell(new Phrase(row.getYear())));
			tbl.addCell(new PdfPCell(new Phrase(row.getCourse())));
			tbl.addCell(new PdfPCell(new Phrase(row.getFirstName())));
			tbl.addCell(new PdfPCell(new Phrase(row.getLastName())));
			tbl.addCell(new PdfPCell(new Phrase(row.getGender())));
			tbl.addCell(new PdfPCell(new Phrase(row.getBirth().toString())));
			tbl.addCell(new PdfPCell(new Phrase(row.getStatus())));
		});
			doc.add(tbl);
			doc.close();
			Alert al=new Alert(AlertType.INFORMATION);
			al.setTitle("Current Students Data Downloaded");
			al.setContentText("Current Students Data Downloaded Successfully");
			al.show();
		}catch(Exception e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setTitle("Error in PDF Download");
			al.show();
		}
	}
	
	@FXML
	private void studentGradesDownload(ActionEvent event) {
		String path="/Users/yashpawar/eclipse-workspace/OOD_Project/PDFReports/";
		Document doc=new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path+"CurStudentsGrade.pdf"));
			doc.open();
			PdfPTable tbl=new PdfPTable(6);
			PdfPCell titleCell = new PdfPCell(new Phrase("Current Student Grades"));
	        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        titleCell.setColspan(6);
	        tbl.addCell(titleCell);
			tbl.addCell("StudentID");
			tbl.addCell("Year");
			tbl.addCell("Course");
			tbl.addCell("FirstSem");
			tbl.addCell("SecondSem");
			tbl.addCell("Final");
			studentGrade_tableView.getItems().forEach(row -> {
			tbl.addCell(new PdfPCell(new Phrase(Integer.toString(row.getStudentNum()))));
			tbl.addCell(new PdfPCell(new Phrase(row.getYear())));
			tbl.addCell(new PdfPCell(new Phrase(row.getCourse())));
			tbl.addCell(new PdfPCell(new Phrase(Double.toString(row.getFirstSem()))));
			tbl.addCell(new PdfPCell(new Phrase(Double.toString(row.getSecondSem()))));
			tbl.addCell(new PdfPCell(new Phrase(Double.toString(row.getFinals()))));
		});
			doc.add(tbl);
			doc.close();
			Alert al=new Alert(AlertType.INFORMATION);
			al.setTitle("Students Grade Downloaded");
			al.setContentText("Current Students Grade Downloaded Successfully");
			al.show();
		}catch(Exception e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setTitle("Error in PDF Download");
			al.show();
		}
	}
	
	@FXML
	private void generateCoursePDF(ActionEvent event) {
		String path="/Users/yashpawar/eclipse-workspace/OOD_Project/PDFReports/";
		Document doc=new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path+"availableCourses.pdf"));
			doc.open();
			PdfPTable tbl=new PdfPTable(3);
			PdfPCell titleCell = new PdfPCell(new Phrase("Current Available Courses"));
	        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        titleCell.setColspan(3);
	        tbl.addCell(titleCell);
			tbl.addCell("Course");
			tbl.addCell("Description");
			tbl.addCell("Degree");
			availableCourse_tableView.getItems().forEach(row -> {
			tbl.addCell(new PdfPCell(new Phrase(row.getCourse())));
			tbl.addCell(new PdfPCell(new Phrase(row.getDescription())));
			tbl.addCell(new PdfPCell(new Phrase(row.getDegree())));
			
		});
			doc.add(tbl);
			doc.close();
			Alert al=new Alert(AlertType.INFORMATION);
			al.setTitle("PDF Downloaded");
			al.setContentText("Available Courses PDF Downloaded Successfully");
			al.show();
		}catch(Exception e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setTitle("Error in PDF Download");
			al.show();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		homeDisplayTotalEnrolledStudents();
		homeDisplayMaleEnrolled();
		homeDisplayFemaleEnrolled();
		homeDisplayEnrolledMaleChart();
		homeDisplayFemaleEnrolledChart();
		homeDisplayTotalEnrolledChart();

		addStudentsShowListData();
		addStudentsYearList();
		addStudentsGenderList();
		addStudentsStatusList();
		addStudentsCourseList();

		availableCourseShowListData();

		studentGradesShowListData();
		
		
		//Add validations
		addStudents_firstName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (!newValue.matches("[a-zA-Z]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter Alphabets Only");
//                alert.showAndWait();
                alert.show();
                addStudents_firstName.setText(oldValue);
            }
        });
		
		addStudents_lastName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (!newValue.matches("[a-zA-Z]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter Alphabets Only");
                alert.show();
                addStudents_lastName.setText(oldValue);
            }
        });
		
		addStudents_studentNum.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (!newValue.matches("[0-9]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter Digits Only");
                alert.show();
                addStudents_studentNum.setText(oldValue);
            }
        });
		
		availableCourse_course.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (!newValue.matches("^[a-zA-Z][a-zA-Z0-9]*$")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Special Characters not allowed");
                alert.setContentText("Please enter Digits or Alphabets Only");
                alert.show();
                availableCourse_course.setText(oldValue);
            }
        });
		
		availableCourse_degree.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (!newValue.matches("[a-zA-Z0-9 ]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Special Characters not allowed");
                alert.setContentText("Please enter Digits or Alphabets Only");
                alert.show();
                availableCourse_degree.setText(oldValue);
            }
        });
		
		availableCourse_description.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (!newValue.matches("[a-zA-Z0-9 ]+")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Special Characters not allowed");
                alert.setContentText("Please enter Digits or Alphabets Only");
                alert.show();
                availableCourse_description.setText(oldValue);
            }
        });
		
		studentGrade_firstsem.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (newValue.matches(".*[a-zA-Z].*")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter grage between 0.0 - 4.0");
                alert.show();
                studentGrade_firstsem.setText(oldValue);
            }
        });
		
		studentGrade_secondsem.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
	            return;
	        }
			if (newValue.matches(".*[a-zA-Z].*")) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter grage between 0.0 - 4.0");
                alert.show();
                studentGrade_secondsem.setText(oldValue);
            }
        });
		
		addStudents_birth.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the selected date is in the future
            if (newValue != null && newValue.getYear()>2005) {
                // If it is, reset the value to the current date
//                addStudents_birth.setValue(LocalDate.now());
            	Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter Birth Year before 2006");
                alert.show();
                addStudents_birth.setValue(oldValue);
            }
        });
	}
		

	public ObservableList<Student> studentGradesListData() {

		ObservableList<Student> listData = FXCollections.observableArrayList();

		String sql = "SELECT * FROM grades";

		connect = Database.conn();

		try {
			Student studentD;

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				studentD = new Student(result.getInt("studentNum"), result.getString("cur_year"),
						result.getString("course"), result.getDouble("first_sem"), result.getDouble("second_sem"),
						result.getDouble("final"));

				listData.add(studentD);
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

	public void addStudentsAdd() throws AddressException, MessagingException {
		String insertData="INSERT INTO student (studentNum, cur_year, course, firstName, lastName, gender, birth, status, image) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		connect = Database.conn();

		try {
			Alert alert;

			if (addStudents_studentNum.getText().isEmpty()
					|| addStudents_year.getSelectionModel().getSelectedItem() == null
					|| addStudents_course.getSelectionModel().getSelectedItem() == null
					|| addStudents_firstName.getText().isEmpty() || addStudents_lastName.getText().isEmpty()
					|| addStudents_gender.getSelectionModel().getSelectedItem() == null
					|| addStudents_birth.getValue() == null
					|| addStudents_status.getSelectionModel().getSelectedItem() == null || Data.path == null
					|| Data.path == "") {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			} else {
				String checkData = "SELECT studentNum FROM student WHERE studentNum = '"
						+ addStudents_studentNum.getText() + "'";

				statement = connect.createStatement();
				result = statement.executeQuery(checkData);

				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Student #" + addStudents_studentNum.getText() + " already exists!");
					alert.showAndWait();
				} else {
					prepare = connect.prepareStatement(insertData);
					prepare.setString(1, addStudents_studentNum.getText());
					prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
					prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
					prepare.setString(4, addStudents_firstName.getText());
					prepare.setString(5, addStudents_lastName.getText());
					prepare.setString(6, (String) addStudents_gender.getSelectionModel().getSelectedItem());
					prepare.setString(7, String.valueOf(addStudents_birth.getValue()));
					prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());

					String uri = Data.path;
					uri = uri.replace("\\", "\\\\");
					prepare.setString(9, uri);
					prepare.executeUpdate();
					
					String insertAdmin = "Insert into admindata"
					+ "(id,username, password)" + "values(?,?,?)";
					prepare = connect.prepareStatement(insertAdmin);
					prepare.setString(1, addStudents_studentNum.getText());
					String username = addStudents_firstName.getText()+addStudents_studentNum.getText();
					String password = addStudents_firstName.getText()+"123";
					prepare.setString(2, username);
					prepare.setString(3, password);
					prepare.executeUpdate();

					String insertStudentGrade = "INSERT INTO grades "
							+ "(studentNum,cur_year,course,first_sem,second_sem,final) " + "VALUES(?,?,?,?,?,?)";

					prepare = connect.prepareStatement(insertStudentGrade);
					prepare.setString(1, addStudents_studentNum.getText());
					prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
					prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
					prepare.setString(4, "0");
					prepare.setString(5, "0");
					prepare.setString(6, "0");
					prepare.executeUpdate();

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Added!");
					alert.showAndWait();

					addStudentsShowListData();
					addStudentsClear();
			   }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudentsUpdate() {

		String uri = Data.path;
		uri = uri.replace("\\", "\\\\");

		String updateData = "UPDATE student SET " + "cur_year = '" + addStudents_year.getSelectionModel().getSelectedItem()
				+ "', course = '" + addStudents_course.getSelectionModel().getSelectedItem() + "', firstName = '"
				+ addStudents_firstName.getText() + "', lastName = '" + addStudents_lastName.getText() + "', gender = '"
				+ addStudents_gender.getSelectionModel().getSelectedItem() + "', birth = '"
				+ addStudents_birth.getValue() + "', status = '"
				+ addStudents_status.getSelectionModel().getSelectedItem() + "', image = '" + uri
				+ "' WHERE studentNum = '" + addStudents_studentNum.getText() + "'";
		
		connect = Database.conn();

		try {
			Alert alert;
			if (addStudents_studentNum.getText().isEmpty()
					|| addStudents_year.getSelectionModel().getSelectedItem() == null
					|| addStudents_course.getSelectionModel().getSelectedItem() == null
					|| addStudents_firstName.getText().isEmpty() || addStudents_lastName.getText().isEmpty()
					|| addStudents_gender.getSelectionModel().getSelectedItem() == null
					|| addStudents_birth.getValue() == null
					|| addStudents_status.getSelectionModel().getSelectedItem() == null) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			} else {

				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(updateData);
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Updated!");
					alert.showAndWait();

					addStudentsShowListData();
					addStudentsClear();

				} else {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudentsDelete() {
		String deleteData = "DELETE s, a FROM student s JOIN admindata a ON s.studentNum = a.id WHERE s.studentNum = '" + addStudents_studentNum.getText() + "'";

		connect = Database.conn();

		try {
			Alert alert;
			if (addStudents_studentNum.getText().isEmpty()
					|| addStudents_firstName.getText().isEmpty() || addStudents_lastName.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {

					statement = connect.createStatement();
					statement.executeUpdate(deleteData);

//					String checkData = "SELECT studentNum FROM moviereview " + "WHERE studentNum = '"
//							+ addStudents_studentNum.getText() + "'";
//
//					prepare = connect.prepareStatement(checkData);
//					result = prepare.executeQuery();
//
//					if (result.next()) {
//						String deleteGrade = "DELETE FROM moviereview WHERE " + "studentNum = '"
//								+ addStudents_studentNum.getText() + "'";
//
//						statement = connect.createStatement();
//						statement.executeUpdate(deleteGrade);
//					} 

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Deleted!");
					alert.showAndWait();

					addStudentsShowListData();
					addStudentsClear();

				} else {
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void addStudentsClear() {
		addStudents_studentNum.setText("");
		addStudents_year.getSelectionModel().clearSelection();
		addStudents_course.getSelectionModel().clearSelection();
		addStudents_firstName.setText("");
		addStudents_lastName.setText("");
		addStudents_gender.getSelectionModel().clearSelection();
		addStudents_birth.setValue(null);
		addStudents_status.getSelectionModel().clearSelection();
		addStudents_imageView.setImage(null);
		Data.path = "";
	}

	public void addStudentsInsertImage() {

		FileChooser open = new FileChooser();
		open.setTitle("Open Image File");
		open.getExtensionFilters().add(new ExtensionFilter("Image File", "*.jpg", "*.png"));

		File file = open.showOpenDialog(main_form.getScene().getWindow());

		if (file != null) {

			image = new Image(file.toURI().toString(), 120, 149, false, true);
			addStudents_imageView.setImage(image);
			Data.path = file.getAbsolutePath();

		}
	}

	public void addStudentsCourseList() {

		String listCourse = "SELECT * FROM course";

		connect = Database.conn();

		try {

			ObservableList listC = FXCollections.observableArrayList();

			prepare = connect.prepareStatement(listCourse);
			result = prepare.executeQuery();

			while (result.next()) {
				listC.add(result.getString("course"));
			}
			addStudents_course.setItems(listC);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void availableCourseAdd() {
		
		String insertData = "INSERT INTO course (course,description,degree) VALUES(?,?,?)";

		connect = Database.conn();

		try {
			Alert alert;

			if (availableCourse_course.getText().isEmpty() || availableCourse_description.getText().isEmpty()
					|| availableCourse_degree.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			} else {
				String checkData = "SELECT course FROM course WHERE course = '" + availableCourse_course.getText()
						+ "'";

				statement = connect.createStatement();
				result = statement.executeQuery(checkData);

				if (result.next()) {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Course: " + availableCourse_course.getText() + " was already exist!");
					alert.showAndWait();
				} else {
					prepare = connect.prepareStatement(insertData);
					prepare.setString(1, availableCourse_course.getText());
					prepare.setString(2, availableCourse_description.getText());
					prepare.setString(3, availableCourse_degree.getText());

					prepare.executeUpdate();

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Added!");
					alert.showAndWait();

					availableCourseShowListData();
					availableCourseClear();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void availableCourseUpdate() {

		String updateData = "UPDATE course SET description = '" + availableCourse_description.getText()
				+ "', degree = '" + availableCourse_degree.getText() + "' WHERE course = '"
				+ availableCourse_course.getText() + "'";

		connect = Database.conn();

		try {
			Alert alert;

			if (availableCourse_course.getText().isEmpty() || availableCourse_description.getText().isEmpty()
					|| availableCourse_degree.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			} else {

				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to UPDATE Course: " + availableCourse_course.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(updateData);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Updated!");
					alert.showAndWait();

					// TO BECOME UPDATED OUR TABLEVIEW ONCE THE DATA WE GAVE SUCCESSFUL
					availableCourseShowListData();
					// TO CLEAR THE TEXT FIELDS
					availableCourseClear();

				} else {
					return;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void availableCourseDelete() {

		String deleteData = "DELETE FROM course WHERE course = '" + availableCourse_course.getText() + "'";

		connect = Database.conn();

		try {
			Alert alert;

			if (availableCourse_course.getText().isEmpty() || availableCourse_description.getText().isEmpty()
					|| availableCourse_degree.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			} else {
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to DELETE Course: " + availableCourse_course.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(deleteData);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Deleted!");
					alert.showAndWait();

					availableCourseShowListData();
					availableCourseClear();

				} else {
					return;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void availableCourseClear() {
		availableCourse_course.setText("");
		availableCourse_description.setText("");
		availableCourse_degree.setText("");
//		generateCoursePDF();
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

	public void availableCourseSelect() {
		Course course = availableCourse_tableView.getSelectionModel().getSelectedItem();
		int num = availableCourse_tableView.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}

		availableCourse_course.setText(course.getCourse());
		availableCourse_description.setText(course.getDescription());
		availableCourse_degree.setText(course.getDegree());

	}

	public void addStudentsYearList() {

		List<String> year = new ArrayList<>();

		for (String data : yearList) {
			year.add(data);
		}

		ObservableList ObList = FXCollections.observableArrayList(year);
		addStudents_year.setItems(ObList);

	}

	private String[] genderList = { "Male", "Female", "Others" };

	public void addStudentsGenderList() {
		List<String> genderL = new ArrayList<>();

		for (String data : genderList) {
			genderL.add(data);
		}

		ObservableList ObList = FXCollections.observableArrayList(genderL);
		addStudents_gender.setItems(ObList);
	}

	private String[] statusList = { "Enrolled", "Not Enrolled", "Inactive" };

	public void addStudentsStatusList() {
		List<String> statusL = new ArrayList<>();

		for (String data : statusList) {
			statusL.add(data);
		}

		ObservableList ObList = FXCollections.observableArrayList(statusL);
		addStudents_status.setItems(ObList);
	}

	public ObservableList<Student> addStudentsListData() {

		ObservableList<Student> listStudents = FXCollections.observableArrayList();

		String sql = "SELECT * FROM student";

		connect = Database.conn();

		try {
			Student student;
			prepare = connect.prepareStatement(sql);
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

		addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
		addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
		addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
		addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		addStudents_col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birth"));
		addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

		addStudents_tableView.setItems(addStudentsList);
	}

	public void addStudentsSelect() {

		Student student = addStudents_tableView.getSelectionModel().getSelectedItem();
		int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}

		addStudents_studentNum.setText(String.valueOf(student.getStudentNum()));
		addStudents_firstName.setText(student.getFirstName());
		addStudents_lastName.setText(student.getLastName());
//        addStudents_gender.setValue(student.getGender());
		addStudents_birth.setValue(LocalDate.parse(String.valueOf(student.getBirth())));

		String uri = "file:" + student.getImage();

		image = new Image(uri, 120, 149, false, true);
		addStudents_imageView.setImage(image);

		Data.path = student.getImage();

	}

	public void studentGradesSelect() {

		Student studentD = studentGrade_tableView.getSelectionModel().getSelectedItem();
		int num = studentGrade_tableView.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1) {
			return;
		}

		studentGrade_studentNum.setText(String.valueOf(studentD.getStudentNum()));
		studentGrade_year.setText(studentD.getYear());
		studentGrade_course.setText(studentD.getCourse());
		studentGrade_firstsem.setText(String.valueOf(studentD.getFirstSem()));
		studentGrade_secondsem.setText(String.valueOf(studentD.getSecondSem()));
	}

//	public void studentGradesSearch() {
//
//		FilteredList<Student> filter = new FilteredList<>(studentGradesList, e -> true);
//
//		studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {
//
//			filter.setPredicate(predicateStudentData -> {
//
//				if (newValue.isEmpty() || newValue == null) {
//					return true;
//				}
//				String searchKey = newValue.toLowerCase();
//				String firstSem = String.valueOf(predicateStudentData.getFirstSem());
//				String secondSem = String.valueOf(predicateStudentData.getSecondSem());
//				String finals = String.valueOf(predicateStudentData.getFinals());
//
//				if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
//					return true;
//				} else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
//					return true;
//				} else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
//					return true;
//				} else if (firstSem.contains(searchKey)) {
//					return true;
//				} else if (secondSem.contains(searchKey)) {
//					return true;
//				} else if (finals.contains(searchKey)) {
//					return true;
//				} else {
//					return false;
//				}
//			});
//		});
//
//		SortedList<Student> sortList = new SortedList<>(filter);
//
//		sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
//		studentGrade_tableView.setItems(sortList);
//
//	}

	public void studentGradesUpdate() {

		String checkData = "SELECT * FROM grades WHERE studentNum = '" + studentGrade_studentNum.getText() + "'";

		connect = Database.conn();

		double finalResult = 0;

		try {

			prepare = connect.prepareStatement(checkData);
			result = prepare.executeQuery();

			double total = Double.parseDouble(studentGrade_firstsem.getText())
					+ Double.parseDouble(studentGrade_secondsem.getText());
			finalResult = (total / 2);

			String updateData = "UPDATE grades SET " + " cur_year = '" + studentGrade_year.getText() + "', course = '"
					+ studentGrade_course.getText() + "', first_sem = '" + studentGrade_firstsem.getText()
					+ "', second_sem = '" + studentGrade_secondsem.getText() + "', final = '" + finalResult
					+ "' WHERE studentNum = '" + studentGrade_studentNum.getText() + "'";

			Alert alert;

			if (studentGrade_studentNum.getText().isEmpty() || studentGrade_year.getText().isEmpty()
					|| studentGrade_course.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();

			} else {

				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Message");
				alert.setHeaderText(null);
				alert.setContentText(
						"Are you sure you want to UPDATE Student #" + studentGrade_studentNum.getText() + "?");
				Optional<ButtonType> option = alert.showAndWait();

				if (option.get().equals(ButtonType.OK)) {
					statement = connect.createStatement();
					statement.executeUpdate(updateData);

					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Updated!");
					alert.showAndWait();

					studentGradesShowListData();
				} else {
					return;
				}

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void studentGradesClear() {
		studentGrade_studentNum.setText("");
		studentGrade_year.setText("");
		studentGrade_course.setText("");
		studentGrade_firstsem.setText("");
		studentGrade_secondsem.setText("");
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

//                root.setOnMousePressed((MouseEvent event) -> {
//                    x = event.getSceneX();
//                    y = event.getSceneY();
//                });

//                root.setOnMouseDragged((MouseEvent event) -> {
//                    stage.setX(event.getScreenX() - x);
//                    stage.setY(event.getScreenY() - y);
//
//                    stage.setOpacity(.8);
//                });

//                root.setOnMouseReleased((MouseEvent event) -> {
//                    stage.setOpacity(1);
//                });

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
		if (event.getSource() == home_btn) {
			home_form.setVisible(true);
			addStudents_form.setVisible(false);
			availableCourse_form.setVisible(false);
			studentGrade_form.setVisible(false);

			homeDisplayTotalEnrolledStudents();
			homeDisplayMaleEnrolled();
			homeDisplayFemaleEnrolled();
			homeDisplayEnrolledMaleChart();
			homeDisplayFemaleEnrolledChart();
			homeDisplayTotalEnrolledChart();

		} else if (event.getSource() == addStudents_btn) {
			home_form.setVisible(false);
			addStudents_form.setVisible(true);
			availableCourse_form.setVisible(false);
			studentGrade_form.setVisible(false);

			addStudentsShowListData();
			addStudentsYearList();
			addStudentsGenderList();
			addStudentsStatusList();
			addStudentsCourseList();

		} else if (event.getSource() == availablecourses_btn) {
			
			home_form.setVisible(false);
			addStudents_form.setVisible(false);
			availableCourse_form.setVisible(true);
			studentGrade_form.setVisible(false);

			availableCourseShowListData();

		} else if (event.getSource() == studentGrade_btn) {
			home_form.setVisible(false);
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

	public void homeDisplayTotalEnrolledStudents() {

		String sql = "SELECT COUNT(studentNum) FROM student where status = 'Enrolled'";

		connect = Database.conn();

		int countEnrolled = 0;

		try {
			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				countEnrolled = result.getInt("COUNT(studentNum)");
			}

			home_totalEnrolled.setText(String.valueOf(countEnrolled));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void homeDisplayFemaleEnrolled() {

		String sql = "SELECT COUNT(studentNum) FROM student WHERE gender = 'Female' and status = 'Enrolled'";

		connect = Database.conn();

		try {
			int countFemale = 0;

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				countFemale = result.getInt("COUNT(studentNum)");
			}

			home_totalFemale.setText(String.valueOf(countFemale));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void homeDisplayMaleEnrolled() {

		String sql = "SELECT COUNT(studentNum) FROM student WHERE gender = 'Male' and status = 'Enrolled'";

		connect = Database.conn();

		try {
			int countMale = 0;

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				countMale = result.getInt("COUNT(studentNum)");
			}
			home_totalMale.setText(String.valueOf(countMale));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void homeDisplayTotalEnrolledChart() {

		home_totalEnrolledChart.getData().clear();

	      String sql = "SELECT cur_year, COUNT(studentNum) FROM student WHERE status = 'Enrolled' GROUP BY cur_year ";

		connect = Database.conn();

		try {
			XYChart.Series chart = new XYChart.Series();

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
			}

			home_totalEnrolledChart.getData().add(chart);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void homeDisplayFemaleEnrolledChart() {

		home_totalFemaleChart.getData().clear();

//        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date ORDER BY TIMESTAMP(date)";
		String sql = "SELECT cur_year, COUNT(studentNum) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY cur_year ORDER BY TIMESTAMP(cur_year)";
        connect = Database.conn();

		try {
			XYChart.Series chart = new XYChart.Series();

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
			}

			home_totalFemaleChart.getData().add(chart);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void homeDisplayEnrolledMaleChart() {

		home_totalMaleChart.getData().clear();

        String sql = "SELECT cur_year, COUNT(studentNum) FROM student WHERE status = 'Enrolled' and gender = 'Male' GROUP BY cur_year ORDER BY TIMESTAMP(cur_year)";	//changed	

		connect = Database.conn();

		try {
			XYChart.Series chart = new XYChart.Series();

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			while (result.next()) {
				chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
			}

			home_totalMaleChart.getData().add(chart);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	
	public void sendEmail(String Subject, String text) {
		
			// email ID of Recipient.
			String recipient = "pawar.ya@northeastern.edu";

			// email ID of Sender.
			String sender = "pawaryash837@gmail.com";

			// using host as localhost
			String host = "127.0.0.1";

			// Getting system properties
			Properties properties = System.getProperties();

			// Setting up mail server
			properties.setProperty("mail.smtp.host", host);

			// creating session object to get properties
			Session session = Session.getDefaultInstance(properties);

			try
			{
				// MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From Field: adding senders email to from field.
				message.setFrom(new InternetAddress(sender));

				// Set To Field: adding recipient's email to from field.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

				// Set Subject: subject of the email
				message.setSubject(Subject);

				// set body of the email.
				message.setText(text);

				// Send email.
				Transport.send(message);
				System.out.println("Mail successfully sent");
			}
			catch (MessagingException mex)
			{
				mex.printStackTrace();
			}
			
}
}
