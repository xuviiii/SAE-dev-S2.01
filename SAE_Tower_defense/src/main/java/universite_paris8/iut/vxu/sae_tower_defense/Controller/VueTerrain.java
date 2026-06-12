package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;

public class VueTerrain {
    private TilePane tile;
    private Terrain terrain;

    public VueTerrain(TilePane tile, Terrain terrain) {
        this.tile = tile;
        this.terrain = terrain;
        creeVueTerrain();
    }

    private void creeVueTerrain() {

        double aléatoire;
        ImageView sprite;

        tile.setMinWidth(terrain.getLongueurMap() * terrain.getTailleTile());
        tile.setMaxWidth(terrain.getLongueurMap() * terrain.getTailleTile());
        for (int i = 0; i < terrain.getMap().size(); i++) {
            if (terrain.getMap().get(i) == 1)
                sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/chemin.png")));
            else{
                aléatoire = Math.random();

                if (aléatoire<0.3333)
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/herbe1.png")));
                else if (aléatoire<0.6666)
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/herbe2.png")));
                else
                    sprite = new ImageView(new Image(getClass().getResourceAsStream("/image/terrain/herbe3.png")));
            }
            sprite.setFitHeight(terrain.getTailleTile());
            sprite.setPreserveRatio(true);
            tile.getChildren().add(sprite);
        }
    }
}
