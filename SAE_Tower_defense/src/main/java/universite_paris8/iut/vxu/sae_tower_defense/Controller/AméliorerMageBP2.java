package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public class AméliorerMageBP2 implements EventHandler<ActionEvent> {

    private Tour tour;

    public AméliorerMageBP2(Tour tour) {
        this.tour = tour;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!tour.estAuNiveauMax() && tour.getEnv().getArgent()>tour.prixAmelioration()){
            tour.ameliorer();
            tour.ameliorer();
        }
    }
}
