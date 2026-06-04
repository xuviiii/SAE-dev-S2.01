package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.vxu.sae_tower_defense.modele.*;

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

        Tour t_test;

        t_test = new TourDeFleche(200,20,map,100,10,10,100);
        map.ajouterTour(t_test);
        t_test = new Catapulte(200,240,map,100,10,10,400,240);
        map.ajouterTour(t_test);

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
