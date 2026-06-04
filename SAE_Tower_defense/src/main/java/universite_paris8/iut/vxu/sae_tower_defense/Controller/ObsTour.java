package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
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
                Circle rayon = new Circle(tour.getPortee());
                rayon.setCenterX(tour.getX()+ (double) tour.getTaille() /2);
                rayon.setCenterY(tour.getY()+ (double) tour.getTaille() /2);
                rayon.setOpacity(0.4);

                ImageView sprite;
                sprite = BankImage.getImgView(tour.getClass(), tour.getTaille());
                sprite.translateXProperty().bind(tour.getXProperty());
                sprite.translateYProperty().bind(tour.getYProperty());
                sprite.setId(tour.getId());

                rayon.setId(tour.getId()+"r");
                terrain.getChildren().add(rayon);
                terrain.getChildren().add(sprite);
            }
        }

        if (change.wasRemoved()){
            for (Tour tour : change.getRemoved()){
                terrain.getChildren().remove(terrain.lookup("#"+ tour.getId()));
                terrain.getChildren().remove(terrain.lookup("#"+ tour.getId()+"r"));
            }
        }
    }
}
