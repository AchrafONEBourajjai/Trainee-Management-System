
package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author AchrafOneBourajjai
 */
public class MenuController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane menuAbout;
    @FXML
    private AnchorPane supervisor;
    @FXML
    private AnchorPane addtrainee;
    @FXML
    private AnchorPane traineeinfo;
    @FXML
    private AnchorPane menuReports;
    @FXML
    private VBox groupTraining;
    @FXML
    private VBox groupReports;
    @FXML
    private VBox groupSettings;
    @FXML
    private VBox groupAbout;
    @FXML
    private AnchorPane menuedit;
    public static AnchorPane staticPane;
    public static StackPane mainRootPane;
    @FXML
    private Pane popUpHolder;
    @FXML
    private JFXButton btnLogoff;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnAccount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staticPane=addtrainee;
        mainRootPane=rootPane;
        setUpRipples();
    }

    @FXML
    public void showAbout() {
        setStage("About.fxml");
    }

    @FXML
    private void showSupervisorInfo(MouseEvent event) {
        setStage("SupervisorInfo.fxml");
    }

    @FXML
    private void addTrainee(MouseEvent event) throws IOException {
        setStage("addTrainee.fxml");

    }

    private void setUpRipples() {
        JFXRippler fXRippler = new JFXRippler(menuAbout, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupAbout.getChildren().addAll(fXRippler);

        JFXRippler fXRippler3 = new JFXRippler(menuedit, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler4 = new JFXRippler(addtrainee, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupSettings.getChildren().addAll(fXRippler3, fXRippler4);

        JFXRippler fXRippler5 = new JFXRippler(menuReports, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler6 = new JFXRippler(supervisor, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupReports.getChildren().addAll(fXRippler5, fXRippler6);

        JFXRippler fXRippler8 = new JFXRippler(traineeinfo, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupTraining.getChildren().addAll(fXRippler8);


    }

    @FXML
    private void showTraineeInfo(MouseEvent event) throws IOException {
        setStage("traineeInfo.fxml");
    }


    private void setStage(String fxml) {
        try {
            //dim overlay on new stage opening
            Region veil = new Region();
            veil.setPrefSize(1100, 650);
            veil.setStyle("-fx-background-color:rgba(0,0,0,0.3)");
            Stage newStage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource(fxml));
            
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.getScene().getRoot().setEffect(new DropShadow());
            newStage.showingProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    rootPane.getChildren().add(veil);
                } else if (rootPane.getChildren().contains(veil)) {
                    rootPane.getChildren().removeAll(veil);
                }
                
            });
            newStage.centerOnScreen();
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showReports(MouseEvent event) {
    	setStage("Repport.fxml");
    }

    @FXML
    private void Edit(MouseEvent event) {
    	setStage("VieuwEdit.fxml");
    }

    @FXML
    private void loggOff(ActionEvent event) throws IOException {
        btnAccount.getScene().getWindow().hide();
        Stage news=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene s=new Scene(root);
        news.setScene(s);
        news.show();
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void showActions(ActionEvent event) {
        JFXPopup popup=new JFXPopup();
        popup.setPopupContent(popUpHolder);
        popup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -45, 65);
    }

}
