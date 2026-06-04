package universite_paris8.iut.vxu.sae_tower_defense.Controller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Environnement map;


    private GameLoop loop;
    private DragAndDrop dragAndDrop;
    private VueTerrain vueTerrain;

    @FXML
    private TilePane tile;

    @FXML
    private Pane terrainEntite;


    @FXML
    private Pane magasin;

    @FXML
    private Label labelArgent;

    @FXML
    private Label labelVie;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Environnement();
        dragAndDrop = new DragAndDrop(map, terrainEntite);
        vueTerrain = new VueTerrain(tile, map.getTerrain());

        map.getTours().addListener(new ObsTour(terrainEntite));
        map.getProjectiles().addListener(new ObsProjectile(map,terrainEntite));
        map.getPersonnages().addListener(new ObsPerso(terrainEntite));

        dragAndDrop.objetdrag(magasin);

        map.argentProperty().addListener((ob,old,nv) -> labelArgent.setText(nv.toString()));
        map.argentDeBase();

        map.vieProperty().addListener((ob,old,nv) -> labelVie.setText(nv.toString()));
        map.vieDeBase();


        terrainEntite.setOnMouseClicked(new Menu(map,terrainEntite));

        loop=new GameLoop(map);
        loop.initAnimation();
        loop.lancer();
    }


}
