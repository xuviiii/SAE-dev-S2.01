package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;

public class VueTerrain {
    private TilePane tile;

    public VueTerrain(TilePane tile) {
        this.tile = tile;
        creeVueTerrain();
    }

    private void creeVueTerrain() {

        double aléatoire;
        ImageView sprite;

        tile.setMinWidth(Terrain.getTerrain().getLongueurMap() * Terrain.getTerrain().getTailleTile());
        tile.setMaxWidth(Terrain.getTerrain().getLongueurMap() * Terrain.getTerrain().getTailleTile());
        for (int i = 0; i < Terrain.getTerrain().getMap().size(); i++) {
            aléatoire = Math.random();

            if (Terrain.getTerrain().getMap().get(i) == 1)
                if (aléatoire<0.5)
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/chemin1.png")));
                else
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/chemin2.png")));
            else{

                if (aléatoire<0.3333)
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/herbe1.png")));
                else if (aléatoire<0.6666)
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/herbe2.png")));
                else
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/herbe3.png")));
            }
            sprite.setFitHeight(Terrain.getTerrain().getTailleTile());
            sprite.setPreserveRatio(true);
            tile.getChildren().add(sprite);
        }
    }
}
