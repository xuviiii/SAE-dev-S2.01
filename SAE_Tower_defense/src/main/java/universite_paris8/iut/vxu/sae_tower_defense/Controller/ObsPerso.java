package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.scene.CacheHint;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
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

//                personnage.getPvProperty().addListener((obs, oldVal, newVal) -> {
//                    sprite.setClip(new ImageView(new Image(getClass().getResourceAsStream("/image/perso/gobelin_vert/gobelin.gif"))));
//
//                    ColorAdjust ca1 = new ColorAdjust();
//                    ca1.setSaturation(-1);
//                    ColorAdjust ca2 = new ColorAdjust();
//                    ca2.setBrightness(0.5);
//
//                    Blend blend = new Blend(
//                            BlendMode.COLOR_BURN,
//                            ca1,
//                            new ColorInput(
//                                    0,
//                                    0,
//                                    sprite.getImage().getWidth(),
//                                    sprite.getImage().getHeight(),
//                                   Color.RED
//                              )
//                    );
//
//                    if(newVal.doubleValue() < oldVal.doubleValue()){
//                        sprite.effectProperty().bind(blend.topInputProperty());
//                    }
//                    if(newVal.doubleValue() > oldVal.doubleValue()){
//                        sprite.effectProperty().bind(blend.bottomInputProperty());
//                    }
//                    sprite.setCache(true);
//                    sprite.setCacheHint(CacheHint.SPEED);
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
