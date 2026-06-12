package universite_paris8.iut.vxu.sae_tower_defense.Controller;


import javafx.collections.ListChangeListener;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import universite_paris8.iut.vxu.sae_tower_defense.modele.*;

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
                sprite = BankImage.getImgView(personnage.getClass(), personnage.getTaille());
                sprite.translateXProperty().bind(personnage.getXProperty());
                sprite.translateYProperty().bind(personnage.getYProperty());
                if(personnage.isCamoufles()){
                    sprite.setOpacity(0.5);
                }
                if (personnage.isCuirasses()){
                    sprite.setFitWidth(personnage.getTaille()*1.3);
                }

//                personnage.getPvProperty().addListener((obs, oldVal, newVal) -> {
//                    ColorAdjust colorAdjust = new ColorAdjust();
//
//                    if(newVal.doubleValue() < oldVal.doubleValue()){
//                        colorAdjust.setHue(-0.4);
//                    }
//
//                    if(newVal.doubleValue() > oldVal.doubleValue()){
//                        colorAdjust.setHue(0.4);
//                        // System.out.println("EFFECT ???");
//                    }
//                    sprite.setEffect(colorAdjust);
//                    // sprite.setEffect(null);
//               });

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
