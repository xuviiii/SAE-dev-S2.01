package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public class Améliorer implements EventHandler<ActionEvent> {

    private Tour tour;

    public Améliorer(Tour tour) {
        this.tour = tour;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!tour.estAuNiveauMax() && tour.getEnv().getArgent()>tour.prixAmelioration()){
            tour.ameliorer();
        }
    }
}
