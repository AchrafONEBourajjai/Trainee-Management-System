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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class RepportController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setCellTable();
		try {
			loadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private ObservableList<Repport> data;
	@FXML
	private MaterialDesignIconView iconeClose;
	@FXML
	private TableView<Repport> table;
	@FXML
	private TableColumn<Repport,String> ccin,cnom,cprenom,cville,cniveau,cspecialite,cservice,caccord,ctheme,cdebut,cfin,cencadrant;
	@FXML
	private JFXTextField find;
	@FXML
	private JFXButton print;
	@FXML
    private void closeStage(MouseEvent event) {
		iconeClose.getScene().getWindow().hide();
    }
	
	private void setCellTable()  {
		data=FXCollections.observableArrayList();
		ccin.setCellValueFactory(new PropertyValueFactory<Repport,String>("cin"));
		cnom.setCellValueFactory(new PropertyValueFactory<Repport,String>("nom"));
		cprenom.setCellValueFactory(new PropertyValueFactory<Repport,String>("prenom"));
		cville.setCellValueFactory(new PropertyValueFactory<Repport,String>("ville"));
		cniveau.setCellValueFactory(new PropertyValueFactory<Repport,String>("niveau"));
		cspecialite.setCellValueFactory(new PropertyValueFactory<Repport,String>("specialite"));
		cservice.setCellValueFactory(new PropertyValueFactory<Repport,String>("service"));
		caccord.setCellValueFactory(new PropertyValueFactory<Repport,String>("accord"));
		ctheme.setCellValueFactory(new PropertyValueFactory<Repport,String>("theme"));
		cdebut.setCellValueFactory(new PropertyValueFactory<Repport,String>("debut"));
		cfin.setCellValueFactory(new PropertyValueFactory<Repport,String>("fin"));
		cencadrant.setCellValueFactory(new PropertyValueFactory<Repport,String>("encadrant"));
	}
	
	private void loadData() throws SQLException {
		Connection con = SqlInteraction.getConnection();
		String sql = "SELECT Trainee.cin,Trainee.nom,Trainee.prenom,Trainee.ville,school.niveau,school.specialite,stage.service,stage.agrement,stage.theme,stage.dated,stage.datef,encadrant.nom FROM Trainee,school,stage,encadrant WHERE stage.id=Trainee.id AND Trainee.id=school.id AND school.id=encadrant.id";
		try {
			PreparedStatement  stmp = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultset = stmp.executeQuery();
			while(resultset.next()) {
				data.add(new Repport(resultset.getString(1),resultset.getString(2),resultset.getString(3),
						resultset.getString(4),resultset.getString(5),resultset.getString(6),resultset.getString(7),
						resultset.getString(8),resultset.getString(9),resultset.getString(10),resultset.getString(11)
						,resultset.getString(12)));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setItems(data);	
	}
	public void search() {
		FilteredList<Repport> filteredata = new FilteredList<>(data,b -> true);
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
			}else if (info.getDebut().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if(info.getTheme().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if(info.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if(info.getFin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}else if(info.getEncadrant().toLowerCase().indexOf(lowerCaseFilter) != -1)
				return true;
				
			else 
				return false;
			
			
			});
		});
		SortedList<Repport> sorteddata = new SortedList<>(filteredata);
		sorteddata.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sorteddata);
	}
	@FXML
	public void generateRepport() throws JRException, SQLException {
		/*Repport info = table.getItems().get(table.getSelectionModel().getSelectedIndex());
		String sql = "SELECT Trainee.cin,Trainee.nom,Trainee.prenom,Trainee.ville,school.nom,school.ville,school.niveau,school.specialite,stage.service,stage.agrement,stage.theme,stage.dated,stage.datef,encadrant.nom FROM Trainee,school,stage,encadrant WHERE Trainee.cin='"+info.getCin()+"' stage.id=Trainee.id AND Trainee.id=school.id AND school.id=encadrant.id";
		Connection con =SqlInteraction.getConnection();
		JasperDesign jd = JRXmlLoader.load("repport.jrxml");
		JRDesignQuery query = new JRDesignQuery();
		query.setText(sql);
		jd.setQuery(query);
		JasperReport jr = JasperCompileManager.compileReport(jd);
		JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
		JasperViewer.viewReport(jp);
		setCellTable(); */
		Connection con =SqlInteraction.getConnection();
		String path = "repport.jrxml";
		JasperReport jr = JasperCompileManager.compileReport(path);
		JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
		JasperViewer.viewReport(jp,true);
		
	} 
}
