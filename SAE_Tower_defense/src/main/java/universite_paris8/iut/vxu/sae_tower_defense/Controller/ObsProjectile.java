package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Projectile;

public class ObsProjectile implements ListChangeListener<Projectile> {
    private Pane terrain;

    public ObsProjectile(Environnement map, Pane terrain) {
        this.terrain = terrain;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> change) {
        change.next();
        if (change.wasAdded()){
            for (Projectile projectile : change.getAddedSubList()){
                Rectangle sprite;
                sprite =new Rectangle(projectile.getTaille(),projectile.getTaille());
                sprite.setFill (Color.BLUE);
                sprite.translateXProperty().bind(projectile.getXProperty());
                sprite.translateYProperty().bind(projectile.getYProperty());
                sprite.setId(projectile.getId());
                terrain.getChildren().add(sprite);
            }
        }

        if (change.wasRemoved()){
            for (Projectile projectile : change.getRemoved()){
                terrain.getChildren().remove(terrain.lookup("#"+projectile.getId()));
            }
        }
    }
}
