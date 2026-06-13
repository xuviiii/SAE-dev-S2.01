package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import universite_paris8.iut.vxu.sae_tower_defense.Application;


import java.net.URL;
import java.util.ResourceBundle;

public class ControlerDemarage implements Initializable {
    @FXML
    private Button boutonDeDemarage;


    @FXML
    public void Demarer() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/vxu/sae_tower_defense/view.fxml"));
        Stage stage = (Stage) boutonDeDemarage.getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setMaximized(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
