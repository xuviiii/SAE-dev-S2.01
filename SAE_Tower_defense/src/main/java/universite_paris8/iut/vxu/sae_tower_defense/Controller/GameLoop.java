package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

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
        System.out.println(gameLoop.getStatus().name());

    }

    public void initAnimation() {

        gameLoop.setCycleCount(Timeline.INDEFINITE);

        Personnage p_test;
        Tour t_test = new Tour(200,260,100,10,32,map,100,10);
        map.ajouterTour(t_test);

//        for (int i=0;i<50;i++){
//            p_test = new Personnage(10,(int) (Math.random()*500),(int) (Math.random()*500),3,2,32,32);
//            map.ajouterPersonnage(p_test);
//            p_test.getPvProperty().addListener(new ObsPersoPvAZero(map,p_test));
//        }

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
