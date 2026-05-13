package universite_paris8.iut.vxu.sae_tower_defense;

import javafx.beans.property.IntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.security.cert.CertificateNotYetValidException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Map map;

    @FXML
    private TilePane terrain;

    @FXML
    private Pane tour;


    public void créerTerrain() {
        Rectangle tuille;
        for(int i=0; i<map.getMap().length; i++){
            for(int j=0; j<map.getMap().length; j++){
                tuille= new Rectangle(100,100);
                switch (map.getMap()[i][j]){
                    case 1: tuille.setFill(Color.BROWN); break;
                    default: tuille.setFill(Color.GREEN); break;
                }
                terrain.getChildren().add(tuille);
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
                    System.out.println(db.getString());
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }
}
