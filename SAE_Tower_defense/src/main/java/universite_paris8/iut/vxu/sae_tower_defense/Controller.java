package universite_paris8.iut.vxu.sae_tower_defense;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

import java.net.URL;
import java.util.ArrayList;
import java.security.cert.CertificateNotYetValidException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timeline gameLoop;

    private int temps;

    private Map map;

    @FXML
    private TilePane tile;

    @FXML
    private Pane terrain;

    @FXML
    private Pane tour;

    private Personnage perso_test;
    private Tour tour_test;


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

        perso_test = new Personnage(20, 3, 3, 1, 1, 0);
        tour_test = new Tour("azerty",240,240,3,2);

        initAnimation();
        // demarre l'animation
        gameLoop.play();


    }

    private void initAnimation() {

        ArrayList<Personnage> ennemis = new ArrayList<>();
        ennemis.add(perso_test);
        Circle tour = new Circle(240,240,10,Color.BLUE);
        Circle cercle = new Circle(10,10,10);
        cercle.translateXProperty().bind(perso_test.getXProperty());
        cercle.translateYProperty().bind(perso_test.getYProperty());
        terrain.getChildren().add(cercle);
        terrain.getChildren().add(tour);


        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if (temps%5==0){    // A modifier pour les persos
                        // cercle.setTranslateX(cercle.getTranslateX()+10);
                        perso_test.setX((perso_test.getX() + 10) % 500);
                        perso_test.setY((perso_test.getY() + 10) % 500);

                        if (tour_test.ennemiACible(ennemis)!=null)
                            tour_test.attaquer(tour_test.ennemiACible(ennemis));

                        System.out.println("\ntour à cibler : "+tour_test.ennemiACible(ennemis)+"\nperso test : "+perso_test+"\n");

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);

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
