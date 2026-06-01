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
    private Achat achat;
    private Drag drag;
    private Drop drop;
    private GameLoop loop;

    @FXML
    private TilePane tile;

    @FXML
    private Pane terrain;

    @FXML
    private Pane flêche;

    @FXML
    private Label labelArgent;


    public void créerTerrain() {
        Rectangle tuille;

        tile.setMinWidth(map.getLongueurMap()*map.getTailleTile());
        for(int i=0; i<map.getMap().size(); i++){
            tuille= new Rectangle(map.getTailleTile(),map.getTailleTile());
            switch (map.getMap().get(i)){
                case 1: tuille.setFill(Color.BROWN); break;
                default: tuille.setFill(Color.GREEN); break;
            }
            tile.getChildren().add(tuille);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Environnement();
        achat = new Achat(map);
        créerTerrain();
        drag = new Drag();
        drop = new Drop(achat, terrain);
        map.getTours().addListener(new ObsTour(terrain));
        map.getProjectiles().addListener(new ObsProjectile(map,terrain));
        map.getPersonnages().addListener(new ObsPerso(terrain));

        flêche.setOnDragDetected(e ->  drag.handle(e));
        terrain.setOnDragDropped(e -> drop.handle(e));
        map.argentProperty().addListener((ob,old,nv) -> labelArgent.setText(nv.toString()));
        map.argentDeBase();

        terrain.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        terrain.setOnMouseClicked(new Menu(map,terrain));

        loop=new GameLoop(map);
        loop.initAnimation();
        loop.lancer();
    }


}
