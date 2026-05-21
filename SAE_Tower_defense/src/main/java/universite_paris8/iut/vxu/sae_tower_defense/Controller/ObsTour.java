package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class ObsTour implements ListChangeListener<Tour> {

    private Pane terrain;

    public ObsTour(Pane terrain) {
        this.terrain = terrain;
    }

    @Override
    public void onChanged(Change<? extends Tour> change) {
        change.next();
        if (change.wasAdded()){
            for (Tour tour : change.getAddedSubList()){
                Rectangle sprite;
                sprite =new Rectangle(10,10);
                sprite.setFill (Color.BLACK);
                sprite.translateXProperty().bind(tour.getXProperty());
                sprite.translateYProperty().bind(tour.getYProperty());
                sprite.setId(tour.getId());
                terrain.getChildren().add(sprite);
            }
        }

        if (change.wasRemoved()){
            for (Tour tour : change.getRemoved()){
                terrain.getChildren().remove(terrain.lookup("#"+ tour.getId()));
            }
        }
    }
}
