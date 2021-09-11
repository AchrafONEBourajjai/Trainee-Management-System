
package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author AchrafOneBourajjai
 */
public class TraineeInfoController {
	@FXML
	private MaterialDesignIconView iconeClose;
	@FXML
	private Label fname,lname,city,nbr,school,speciality,niveau;
	@FXML
	private JFXTextField cin;
	@FXML
	private JFXButton find;

	@FXML
    private void closeStage(MouseEvent event) {
		iconeClose.getScene().getWindow().hide();
    }
	@FXML
	public void getTraineeInfo() {
		try {
			String sql = "SELECT Trainee.nom,prenom,Trainee.ville,school.nom,specialite,niveau FROM `Trainee`,`school` WHERE Trainee.cin='"+cin.getText().toUpperCase()+"' AND Trainee.id=school.id";
			Connection con = SqlInteraction.getConnection();
			PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset =stmp.executeQuery();
			resultset.next();
			fname.setText(resultset.getString(1));
			lname.setText(resultset.getString(2));
			city.setText(resultset.getString(3));
			nbr.setText(getNbrStage());
			school.setText(resultset.getString(4));
			speciality.setText(resultset.getString(5));
			niveau.setText(resultset.getString(6));
			resultset.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getNbrStage() {
		String nbr="";
		try {
		String sql = "SELECT COUNT(Trainee.id) FROM Trainee,stage WHERE Trainee.id=stage.id AND Trainee.cin='"+cin.getText().toUpperCase()+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		ResultSet resultset =stmp.executeQuery();
		resultset.next();
		nbr=resultset.getString(1);
		resultset.close();
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nbr;
	}
	

}
