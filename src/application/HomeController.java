package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController  {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXPasswordField pass;
    @FXML
    private Label check;
    @FXML
    public void statusDB() throws IOException,SQLException {
    	if(!AdminsDb.getConnection().isClosed()) {
    		check.setText("success");
    	}else {
    		check.setText("check your connection");
    	}
    }
    @FXML
    public void doLogin(ActionEvent event) throws IOException, SQLException{
    	List<Admins> list = AdminsDb.getAdmins();
    	Map<String,String> map = new HashMap<String,String>();
    	for(Admins a: list) { 
    		map.put(a.getUsername(), a.getPassword());   		
    	}
    	if (map.containsKey(name.getText())) {
    		String val2 = map.get(name.getText());
    		if (val2.equals(pass.getText())) {
    			statusDB();
    			btnLogin.getScene().getWindow().hide();
    	        Parent root=FXMLLoader.load(getClass().getResource("Menu.fxml"));
    	        Stage mainStage=new Stage();
    	        Scene scene=new Scene(root);
    	        mainStage.setScene(scene);
    	        mainStage.show(); 
    		}
    	}
    }
    
}
