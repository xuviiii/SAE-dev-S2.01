package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class GameLoop {
    private Timeline gameLoop;
    private int temps;
    private Environnement map;

    public GameLoop(Environnement map) {
        this.map = map;
    }

    public void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017/6),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if (temps % 5 == 0){    // A modifier pour les persos
                        map.faireUnTour(temps);
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);

    }

    public void lancer(){
        gameLoop.play();
    }
}
