package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Projectile;

public class ObsProjectile implements ListChangeListener<Projectile> {
    private Map map;
    private Pane terrain;

    public ObsProjectile(Map map,Pane terrain) {
        this.map = map;
        this.terrain = terrain;
    }

    @Override
    public void onChanged(ListChangeListener.Change<? extends Projectile> change) {
        change.next();
        if (change.wasAdded()){
            for (Projectile projectile : change.getAddedSubList()){
                ObsIntersectProjectilePerso obs = new ObsIntersectProjectilePerso(map,terrain,projectile);
                Rectangle sprite;
                sprite =new Rectangle(5,5);
                sprite.setFill (Color.BLUE);
                sprite.translateXProperty().bind(projectile.getXProperty());
                projectile.getXProperty().addListener(obs);
                sprite.translateYProperty().bind(projectile.getYProperty());
                projectile.getYProperty().addListener(obs);
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
