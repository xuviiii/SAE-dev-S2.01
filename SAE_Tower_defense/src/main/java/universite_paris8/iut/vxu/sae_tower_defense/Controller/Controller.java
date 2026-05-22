package universite_paris8.iut.vxu.sae_tower_defense.Controller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.GameLoop;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Map map;
    private Achat achat;
    private Drag drag;
    private Drop drop;
    GameLoop gameLoop;

    @FXML
    private TilePane tile;

    @FXML
    private Pane terrain;

    @FXML
    private Pane flêche;

    private GameLoop loop;




    public void créerTerrain() {
        Rectangle tuille;
        for(int i=0; i<map.getMap().length; i++){
            for(int j=0; j<map.getMap().length; j++){
                tuille= new Rectangle(100,100);
                switch (map.getMap()[i][j]){
                    case 1: tuille.setFill(Color.BROWN); break;
                    default: tuille.setFill(Color.GREEN); break;
                }
                tile.getChildren().add(tuille);
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Map();
        achat = new Achat(map);
        créerTerrain();
        drag = new Drag();
        drop = new Drop(achat, terrain);
        map.getTours().addListener(new ObsTour(terrain));
        map.getPersonnages().addListener(new ObsPerso(terrain));
        map.getProjectiles().addListener(new ObsProjectile(map,terrain));

        flêche.setOnDragDetected(e ->  drag.handle(e));
        terrain.setOnDragDropped(e -> drop.handle(e));


        terrain.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });


        /*Pane menu = new Pane();
        terrain.getChildren().add(menu);
        Label stats = new Label("Stats");
        Button améliorer = new Button("Améliorer");
        Button vendre = new Button("Vendre");
        VBox menuContenu = new VBox(stats,améliorer,vendre);
        menu.getChildren().add(menuContenu);
        menu.setStyle("-fx-background-color: #c19a9a;");
        menu.setDisable(true);
        menu.setOpacity(0);

        terrain.setOnMouseClicked(new Menu(map,menu,stats));*/


        terrain.setOnMouseClicked(new Menu(map,terrain));



        gameLoop=new GameLoop(map);
        gameLoop.initAnimation();
        gameLoop.lancer();
    }
}
