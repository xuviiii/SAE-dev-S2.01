package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;

public class ObsPerso implements ListChangeListener<Personnage> {

    private Pane terrain;

    public ObsPerso(Pane terrain) {
        this.terrain = terrain;
    }

    @Override
    public void onChanged(Change<? extends Personnage> change) {
        change.next();
        if (change.wasAdded()){
            for (Personnage personnage : change.getAddedSubList()){
                ImageView sprite;
                sprite =new ImageView();
                sprite.setImage(new Image(getClass().getResourceAsStream("/image/perso/gobelin_vert/gobelin-4.png")));
                sprite.setFitWidth(personnage.getTaille());
                sprite.setPreserveRatio(true);
                sprite.translateXProperty().bind(personnage.getXProperty());
                sprite.translateYProperty().bind(personnage.getYProperty());
                sprite.setId(personnage.getId());
                terrain.getChildren().add(sprite);
            }
        }

        if (change.wasRemoved()){
            for (Personnage personnage : change.getRemoved()){
                terrain.getChildren().remove(terrain.lookup("#"+personnage.getId()));
            }
        }
    }
}
