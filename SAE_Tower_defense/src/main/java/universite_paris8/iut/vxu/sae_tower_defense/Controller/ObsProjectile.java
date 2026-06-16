package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

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
                ImageView sprite;
                sprite = BankImage.getImgView(projectile.getClass(), projectile.getTaille());
                sprite.translateXProperty().bind(projectile.getXProperty());
                sprite.translateYProperty().bind(projectile.getYProperty());
                sprite.fitHeightProperty().bind((projectile.getTailleProperty()));
                sprite.setPreserveRatio(true);
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
