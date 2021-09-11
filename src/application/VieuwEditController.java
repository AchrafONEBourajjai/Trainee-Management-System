package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class VieuwEditController implements Initializable  {
	private ObservableList<TableVieuwInfo> data;
	@FXML
	private JFXButton edit,delete; 
	@FXML
	private MaterialDesignIconView iconeClose;
	@FXML
	private JFXTextField cin,nom,prenom,ville,service,agrement,theme,supervisor,find;
	@FXML
	private TableView <TableVieuwInfo>table;
	@FXML
	private TableColumn<TableVieuwInfo,String> ccin,cnom,cprenom,cville,cservice,cagrement,ctheme,cid,csupervisor;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		try {
			setCellTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
    private void closeStage(MouseEvent event) {
		iconeClose.getScene().getWindow().hide();
    }
	private void updateTrainee() throws SQLException {
		String oldCin = cin.getText().toUpperCase();
		String sql = "UPDATE Trainee SET cin='"+cin.getText().toUpperCase()+"',`nom`='"+nom.getText().toUpperCase()+"',`prenom`='"+prenom.getText().toUpperCase()+"',`ville`='"+ville.getText().toUpperCase()+"' WHERE cin='"+oldCin+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void updateStage() throws SQLException {
		TableVieuwInfo info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		String sql = "UPDATE stage SET service='"+service.getText().toUpperCase()+"',agrement='"+agrement.getText().toUpperCase()+"',theme='"+theme.getText().toUpperCase()+"' WHERE id='"+info.getIdStage()+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void updateSupervisor() throws SQLException {
		TableVieuwInfo info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		String oldName = info.getSupervisor();
		String oldid= info.getIdStage();
		String sql ="UPDATE encadrant SET nom='"+supervisor.getText()+"' WHERE id='"+oldid+"' AND nom='"+oldName+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void deleteTrainee() throws SQLException {
		String sql ="DELETE FROM Trainee WHERE cin='"+cin.getText()+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void deleteSchool() throws SQLException {
		TableVieuwInfo info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		String sql ="DELETE FROM school WHERE id='"+info.getIdStage()+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void deleteSupervisor() throws SQLException {
		TableVieuwInfo info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		String sql ="DELETE FROM encadrant WHERE nom='"+supervisor.getText()+"' AND id='"+info.getIdStage()+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void deleteStage() throws SQLException {
		TableVieuwInfo info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		String sql ="DELETE FROM stage WHERE id='"+info.getIdStage()+"'";
		Connection con = SqlInteraction.getConnection();
		PreparedStatement stmp = (PreparedStatement) con.prepareStatement(sql);
		stmp.execute();
		con.close();
	}
	private void setCellFromTablevieuwToTextField() {
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				TableVieuwInfo info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
				cin.setText(info.getCin());
				nom.setText(info.getNom());
				prenom.setText(info.getPrenom());
				ville.setText(info.getVille());
				service.setText(info.getService());
				agrement.setText(info.getAgrement());
				theme.setText(info.getTheme());
				supervisor.setText(info.getSupervisor());
			}
		});
	}
	private void setCellTable() throws SQLException {
		data = FXCollections.observableArrayList();
		ccin.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("cin"));
		cnom.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("nom"));
		cprenom.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("prenom"));
		cville.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("ville"));
		cservice.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("service"));
		cagrement.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("agrement"));
		ctheme.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("theme"));
		cid.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("idStage"));
		csupervisor.setCellValueFactory(new PropertyValueFactory<TableVieuwInfo,String>("supervisor"));
		loadData();
		search();
		setCellFromTablevieuwToTextField();
	}
	public void search() {
		FilteredList<TableVieuwInfo> filteredata = new FilteredList<>(data,b -> true);
		find.textProperty().addListener((observable,oldValue,newValue) -> {
		filteredata.setPredicate(info -> {
			if(newValue==null || newValue.isEmpty()) {
				return true;
			}
			String lowerCaseFilter = newValue.toLowerCase();
			if (info.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (info.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if (info.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if (info.getService().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if (info.getSupervisor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if(info.getTheme().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if(info.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) 
				return true;
			else 
				return false;
			
			
			});
		});
		SortedList<TableVieuwInfo> sorteddata = new SortedList<>(filteredata);
		sorteddata.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sorteddata);
	}
	private void loadData() throws SQLException {
		Connection con = SqlInteraction.getConnection();
		String sql = "SELECT Trainee.cin,Trainee.nom,Trainee.prenom,Trainee.ville,service,agrement,theme,stage.id,encadrant.nom from Trainee,stage,encadrant WHERE Trainee.id = stage.id AND Trainee.id=encadrant.id";
		try {
			PreparedStatement  stmp = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset = stmp.executeQuery();
			while(resultset.next()) {
				data.add(new TableVieuwInfo(resultset.getString(1),resultset.getString(2),resultset.getString(3),
						resultset.getString(4),resultset.getString(5),resultset.getString(6),resultset.getString(7),
						""+resultset.getInt(8),resultset.getString(9)));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setItems(data);	
	}
	public void infodialog() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data insert");
		alert.setHeaderText("information dialog");
		alert.setContentText("Updated successfully");
		alert.showAndWait();
	}
	public void warningodialog(String a) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Data");
		alert.setHeaderText("check !!");
		alert.setContentText(a);
		alert.showAndWait();
		
	}
	@FXML
	private void updateData() throws SQLException {
		updateStage();
		updateTrainee();
		updateSupervisor();
		infodialog();
		setCellTable();
	}
	@FXML
	private void deleteData() throws SQLException {
		deleteTrainee();
		deleteSupervisor();
		deleteSchool();
		deleteStage();
		warningodialog("Deleted successfully");
		setCellTable();
	}
	
}
