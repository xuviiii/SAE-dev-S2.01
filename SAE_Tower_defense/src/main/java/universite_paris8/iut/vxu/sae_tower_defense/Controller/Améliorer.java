package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class Améliorer implements EventHandler<ActionEvent> {

    private Tour tour;
    private Label stats;

    public Améliorer(Tour tour, Label stats) {
        this.tour = tour;
        this.stats = stats;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        tour.ameliorer();
        stats.setText("Stats :\ndegat : "+tour.getDégât()+", Portée : "+tour.getPortée()+", Position : x"+tour.getX()+", y"+tour.getY());

    }
}
