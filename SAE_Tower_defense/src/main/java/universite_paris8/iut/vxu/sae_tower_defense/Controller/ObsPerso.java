package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;

public class ObsPerso implements ListChangeListener<Personnage> {

    private Pane terrain;
    private Map map;

    public ObsPerso(Pane terrain, Map map) {
        this.terrain = terrain;
        this.map = map;
    }

    @Override
    public void onChanged(Change<? extends Personnage> change) {
        change.next();
        if (change.wasAdded()){
            for (Personnage personnage : change.getAddedSubList()){
                Rectangle sprite;
                sprite = new Rectangle(10,10);
                sprite.setFill (Color.WHITE);
//                sprite.translateXProperty().bind(personnage.getXProperty());
//                sprite.translateYProperty().bind(personnage.getYProperty());

                personnage.getIndiceTerrainProperty().addListener((obs, oldVal, newVal) -> {
                    sprite.setTranslateX(((int) newVal % map.getLongueurMap()) * map.getTailleTile());
                    sprite.setTranslateY(((int) newVal / map.getLongueurMap()) * map.getTailleTile());
                });

                sprite.setId(personnage.getId());
                terrain.getChildren().add(sprite);
            }
        }

        if (change.wasRemoved()){
            for (Personnage personnage : change.getRemoved()){
                terrain.getChildren().remove(terrain.lookup("#" + personnage.getId()));
            }
        }
    }
}
