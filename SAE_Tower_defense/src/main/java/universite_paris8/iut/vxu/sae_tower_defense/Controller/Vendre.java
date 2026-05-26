package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class Vendre implements EventHandler<ActionEvent> {

    private Map map;
    private Tour tour;

    public Vendre(Map map, Tour tour) {
        this.map = map;
        this.tour = tour;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        map.getTours().remove(tour);
    }
}
