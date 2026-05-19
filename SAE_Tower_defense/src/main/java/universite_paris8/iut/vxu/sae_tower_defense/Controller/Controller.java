package universite_paris8.iut.vxu.sae_tower_defense.Controller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.GameLoop;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Map map;

    @FXML
    private TilePane tile;

    @FXML
    private Pane terrain;

    @FXML
    private Pane tour;

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
        créerTerrain();


        tour.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = tour.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("flêche");
                db.setContent(content);
                event.consume();
            }
        });

        terrain.setOnDragOver(new EventHandler<DragEvent>() {
            @Override public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        terrain.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Rectangle t;
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    if(db.getString().equals("flêche")){
                        t = new Rectangle(10,10,Color.BLACK);
                        terrain.getChildren().add(t);
                        t.setTranslateX(event.getX());
                        t.setTranslateY(event.getY());

                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });


    }


}
