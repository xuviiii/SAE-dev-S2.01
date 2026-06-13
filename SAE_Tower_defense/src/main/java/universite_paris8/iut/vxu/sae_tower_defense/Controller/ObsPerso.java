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
                    sprite.fitWidthProperty().bind(personnage.tailleProperty());
                }
                personnage.tempEnflamerProperty().addListener((obs,old,nv)-> {
                    ColorAdjust colorAdjust = new ColorAdjust();
                    if ( nv.intValue() > 0) {
                        colorAdjust.setHue(0.05);
                    }
                    else {
                        colorAdjust.setHue(0.00);
                    }
                    sprite.setEffect(colorAdjust);
                });

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
