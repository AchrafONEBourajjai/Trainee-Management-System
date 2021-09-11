package application;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author danml
 */
public class AboutController implements Initializable{
	@FXML
	private MaterialDesignIconView iconClose;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void closeStage(MouseEvent event) {
        iconClose.getScene().getWindow().hide();
    }
   
}
