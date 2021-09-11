package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SupervisorInfoController {
	Connection con = SqlInteraction.getConnection();
	ResultSet result,result1=null;
	@FXML
	private JFXTextField sname;
	@FXML
	private JFXDatePicker sdate;
	@FXML
	private MaterialDesignIconView iconeClose;
	@FXML
	private Label nbr,srv;
	@FXML JFXButton find;
	
	@FXML
    private void closeStage(MouseEvent event) {
		iconeClose.getScene().getWindow().hide();
    }

	public String getNbrTrainee() throws SQLException {
		String A="";
		try {
			String sql =" SELECT count(*) FROM encadrant,stage,Trainee WHERE encadrant.nom='"+sname.getText().toUpperCase()+"' AND encadrant.id=stage.id AND encadrant.id=Trainee.id AND stage.dated >= '"+sdate.getValue()+"'";
			PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
			 result = stmp.executeQuery();
			 result.next();
			 A =result.getString(1);
			 result.close();
			}catch(Exception e) {
			e.printStackTrace();
		}
			return A;
			
	}
	public String getService() throws SQLException {
		String A="";
		try {
			String sql ="SELECT service FROM encadrant,stage WHERE encadrant.id=stage.id AND encadrant.nom='"+sname.getText().toUpperCase()+"'";
			PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
			result1 = stmp.executeQuery();
			result1.next();
			A=result1.getString(1);
			result1.close();
			con.close();
			}catch(Exception e) {
			e.printStackTrace();
		}
		return A;
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
	 public void warningodialog(String a) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Data not Entred");
			alert.setHeaderText("check !!");
			alert.setContentText(a);
			alert.showAndWait();
			
		}
	 public boolean checkSupervisor() throws SQLException {
		Connection c2 = SqlInteraction.getConnection();
		String sql ="SELECT nom FROM encadrant,stage WHERE encadrant.nom='"+sname.getText().toUpperCase()+"'";
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		ResultSet rs = stmp.executeQuery();
		if(rs.next()) {
			rs.close();
			c2.close();
			return true;
		}else {
			rs.close();
			c2.close();
			return false;
		}
	 }
	 @FXML
	 private void search() throws SQLException {
		 if (sname.getText().isEmpty()) {
			 warningodialog("supervisor name empty");
		 }else if(sdate.getValue().equals("")) {
			 warningodialog("Date is empty");
		 }else if (!checkSupervisor()) {
			 warningodialog("user NOT found");
		 }else {
			 nbr.setText(getNbrTrainee());
			 srv.setText(getService());
			 
		 }
		 }
	 }

