package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class TraineeController implements Initializable {
	@FXML
	private JFXButton btnSave;
	@FXML
	private MaterialDesignIconView iconeClose;
	@FXML
	private JFXTextField cin,fname,lname,city,school,speciality,scity,supervisor,assign,theme;
	@FXML
	private JFXComboBox<String> level,agrement;
	@FXML
	private JFXDatePicker from,to;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		level.getItems().addAll("BAC+2","BAC+3","BAC+5","MORE");
		agrement.getItems().addAll("YES","NO");
	}
    @FXML
    private void verifyText(KeyEvent event) {
    char c = event.getCharacter().charAt(0);
    if((!Character.isLetter(c))) {
    	if((!Character.isSpaceChar(c))) {
    	event.consume();
    }
    }
    }
    @FXML
    private void verifyNum(KeyEvent event) {
    char c = event.getCharacter().charAt(0);
    if(!Character.isDigit(c)) {
    	event.consume();
    }
    }
	@FXML
    private void closeStage(MouseEvent event) {
		iconeClose.getScene().getWindow().hide();
    }
	@FXML
	private void insertData(ActionEvent event) throws SQLException,IOException {
		if(cin.getText().isEmpty()) {
			warningodialog("cin is empty");
			cin.requestFocus();
		}else if(fname.getText().isEmpty()) {
			warningodialog("first name is empty");
			fname.requestFocus();
		}else if(lname.getText().isEmpty()) {
			warningodialog("last name is empty");
			lname.requestFocus();
		}else if(city.getText().isEmpty()) {
			warningodialog("city info is empty");
			city.requestFocus();
		}else if(school.getText().isEmpty()) {
			warningodialog("name of school is empty");
			school.requestFocus();
		}else if (speciality.getText().isEmpty()) {
			warningodialog("speciality is empty");
			speciality.requestFocus();
		}else if(scity.getText().isEmpty()) {
			warningodialog("location school is empty");
		}else if(supervisor.getText().isEmpty()) {
			warningodialog("supervisor info is empty");
			supervisor.requestFocus();
		}else if(assign.getText().isEmpty()) {
			warningodialog("assignement service info is empty");
			assign.requestFocus();
		}else if(theme.getText().isEmpty()) {
			warningodialog("theme is empty");
			theme.requestFocus();
		
		}else if(from.getValue().isBefore(LocalDate.now())) {
			warningodialog("check Date");
			from.requestFocus();
		}else if(to.getValue().isBefore(LocalDate.now())) {
			warningodialog("check Date");
			to.requestFocus();
		}else if(from.getValue().equals("")) {
			warningodialog("Date empty");
			to.requestFocus();
		}else if(to.getValue().equals("")) {
			warningodialog("Date empty");
			to.requestFocus();
		}else if(from.getValue().isAfter(to.getValue())) {
			warningodialog("Date invalid");
			to.requestFocus();
		}else if(level.getValue().equals("")) {
			warningodialog("Level empty");
			to.requestFocus();
		}else if(agrement.getValue().equals("")) {
			warningodialog("agreement empty");
			to.requestFocus();
		}else {
			insertStage();
			insertTrainnee();
			insertSupervisor();
			insertSchool(); 
			infodialog();
		}
	}

	public  void insertStage() throws SQLException {
		String sql = "INSERT INTO stage (service,agrement,theme,dated,datef) VALUES ('"+assign.getText().toUpperCase()+"','"+agrement.getValue().toUpperCase()+"','"+theme.getText().toUpperCase()+"','"+from.getValue()+"','"+to.getValue()+"')";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		try {
			stmp.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getIdStage() throws SQLException {
		String id="";
		String sql = "select * from stage ";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		try {
			ResultSet resultset =  stmp.executeQuery();
			while(resultset.next()) {
				Stages stage = new Stages();
				stage.setId(resultset.getString(1));
				id = stage.getId();
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	public void insertSchool() throws SQLException {
		int a = Integer.parseInt(TraineeController.getIdStage());
		String sql = "INSERT INTO school (id,nom,ville,niveau,specialite) VALUES ('"+a+"','"+school.getText().toUpperCase()+"','"+scity.getText().toUpperCase()+"','"+level.getValue().toUpperCase()+"','"+speciality.getText().toUpperCase()+"')";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		try {
			stmp.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insertSupervisor() throws SQLException {
		int a = Integer.parseInt(TraineeController.getIdStage());
		String sql = "INSERT INTO encadrant (id,nom) VALUES ('"+a+"','"+supervisor.getText().toUpperCase()+"')";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		try {
			stmp.execute();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void warningodialog(String a) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Data not Entred");
		alert.setHeaderText("check !!");
		alert.setContentText(a);
		alert.showAndWait();
		
	}
	public void infodialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data insert");
		alert.setHeaderText("information dialog");
		alert.setContentText("Record saved successfully");
		alert.showAndWait();
		
	}
	public void insertTrainnee() throws SQLException {
		int a= Integer.parseInt(TraineeController.getIdStage());
		String sql = "INSERT INTO Trainee (cin,id,nom,prenom,ville) VALUES ('"+cin.getText().toUpperCase()+"','"+a+"','"+fname.getText().toUpperCase()+"','"+lname.getText().toUpperCase()+"','"+city.getText().toUpperCase()+"')";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		try {
			stmp.execute();
			con.close();
		}catch(SQLException  e) {
			e.printStackTrace();
		}
	}
	
}
