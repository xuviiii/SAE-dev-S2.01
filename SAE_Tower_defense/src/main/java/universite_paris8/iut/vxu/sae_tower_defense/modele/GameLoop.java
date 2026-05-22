package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameLoop {
    private Timeline gameLoop;
    private int temps;
    private Map map;

    public GameLoop(Map map) {
        this.map = map;
    }

    public void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        Personnage p_test = new Personnage(10,230,230,3,2,10,32);
        Tour t_test = new Tour("a",240,240,50,1,32);

        map.ajouterPersonnage(p_test);
        map.ajouterTour(t_test);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if (temps%5==0){    // A modifier pour les persos
                        map.faireUnTour();
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
