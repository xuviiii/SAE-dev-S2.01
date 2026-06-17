package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.vxu.sae_tower_defense.modele.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeMage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDHuile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDeFleche;

public class GameLoop {
    private Timeline gameLoop;
    private int temps;
    private Environnement map;
    private IntegerProperty vittesse;

    public GameLoop(Environnement map) {
        this.map = map;
        gameLoop = new Timeline();
        temps=0;
        vittesse= new SimpleIntegerProperty(1);
    }

    public Timeline getGameLoop() {
        return gameLoop;
    }

    public IntegerProperty vittesseProperty() {
        return vittesse;
    }

    public void changerVitesse(){
        if (vittesse.get() >= 4){
            vittesse.set(1);
        }
        else {
            vittesse.set(vittesse.get()*2);
        }
    }

    public void mettrePause(){
        if (gameLoop.getStatus().name().equals("PAUSED")){
            gameLoop.play();
        }
        else {
            gameLoop.pause();
        }

    }

    public void initAnimation() {

        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017/6),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if (temps % (5/vittesse.get()) == 0){    // A modifier pour les persos
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
