package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
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
            //System.out.println("Ajouter : "+change.getAddedSubList());
            for (Projectile projectile : change.getAddedSubList()){
                ImageView sprite;
                sprite = BankImage.getImgView(projectile.getClass(), 20);
                sprite.translateXProperty().bind(projectile.getXProperty());
                sprite.translateYProperty().bind(projectile.getYProperty());
                sprite.setId(projectile.getId());
                sprite.setRotate(projectile.getAngle());
                terrain.getChildren().add(sprite);
            }
        }

        if (change.wasRemoved()){
            //System.out.println("Retirer : " +change.getRemoved());
            for (Projectile projectile : change.getRemoved()){
                terrain.getChildren().remove(terrain.lookup("#"+projectile.getId()));
            }
        }
    }
}
