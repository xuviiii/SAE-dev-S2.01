package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameLoop {
    private Timeline gameLoop;
    private int temps;


    public GameLoop() {
    }

    public void initAnimation() {
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

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);

    }
}
