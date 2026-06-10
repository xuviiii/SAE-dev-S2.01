package universite_paris8.iut.vxu.sae_tower_defense.Controller;

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
        Rectangle tuille;

        tile.setMinWidth(terrain.getLongueurMap() * terrain.getTailleTile());
        tile.setMaxWidth(terrain.getLongueurMap() * terrain.getTailleTile());
        for (int i = 0; i < terrain.getMap().size(); i++) {
            tuille = new Rectangle(terrain.getTailleTile(), terrain.getTailleTile());
            switch (terrain.getMap().get(i)) {
                case 1:
                    tuille.setFill(Color.BROWN);
                    break;
                default:
                    tuille.setFill(Color.GREEN);
                    break;
            }
            tile.getChildren().add(tuille);
        }
    }
}
