package universite_paris8.iut.vxu.sae_tower_defense;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timeline gameLoop;

    private int temps;

    private Map map;

    @FXML
    private Pane terrain;


    public void créerTerrain() {
        HBox ligne;
        Rectangle tuile;
        VBox v= new VBox();
        for(int i=0; i<map.getMap().length; i++){
            ligne = new HBox();
            for(int j=0; j<map.getMap().length; j++){
                tuile = new Rectangle(100,100);
                switch (map.getMap()[i][j]){
                    case 1: tuile.setFill(Color.BROWN); break;
                    default: tuile.setFill(Color.GREEN); break;
                }
                ligne.getChildren().add(tuile);
            }
            v.getChildren().add(ligne);
        }
        terrain.getChildren().add(v);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map = new Map();
        créerTerrain();
        initAnimation();
        // demarre l'animation
        gameLoop.play();
    }

    private void initAnimation() {
        Circle cercle = new Circle(10,10,10);
        terrain.getChildren().add(cercle);
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
                        cercle.setTranslateX(cercle.getTranslateX()+10);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}
