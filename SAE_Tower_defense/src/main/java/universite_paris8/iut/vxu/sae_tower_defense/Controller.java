package universite_paris8.iut.vxu.sae_tower_defense;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Map map;

    @FXML
    private Pane terrain;


    public void créerTerrain() {
        HBox ligne;
        Rectangle tuille;
        VBox v= new VBox();
        for(int i=0; i<map.getMap().length; i++){
            ligne = new HBox();
            for(int j=0; j<map.getMap().length; j++){
                tuille= new Rectangle(100,100);
                switch (map.getMap()[i][j]){
                    case 1: tuille.setFill(Color.BROWN); break;
                    default: tuille.setFill(Color.GREEN); break;
                }
                ligne.getChildren().add(tuille);
            }
            v.getChildren().add(ligne);
        }
        terrain.getChildren().add(v);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Map();
        créerTerrain();
    }
}
