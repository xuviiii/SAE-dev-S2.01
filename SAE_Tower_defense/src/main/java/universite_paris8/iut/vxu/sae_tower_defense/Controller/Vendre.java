package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.Camp;

public class Vendre implements EventHandler<ActionEvent> {

    private Environnement map;
    private Tour tour;
    private Pane menu;


    public Vendre( Tour tour, Pane menu) {
        this.tour = tour;
        this.menu = menu;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        menu.getChildren().clear();
        Environnement.getEnvironnement().getTours().remove(tour);
        Environnement.getEnvironnement().ajouterArgent(tour.getPrix()/2);
        if (tour instanceof Camp)
            ((Camp)tour).enleverBoost();
    }
}
