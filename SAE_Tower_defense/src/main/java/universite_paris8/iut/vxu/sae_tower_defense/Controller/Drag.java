package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class Drag implements EventHandler<MouseEvent> {
    private Pane terrain;
    private Environnement env;
    private Tour tour;


    public Drag(Pane terrain, Environnement env) {
        this.terrain = terrain;
        this.env = env;
        this.tour=null;
    }

    @Override
    public void handle(MouseEvent event) {
        Pane preview = new Pane();
        tour = new Tour(0,0,100,10,32,env,100);
        Circle rayon = new Circle();
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/image/tour/archer/archer.gif")));
        img.setFitWidth(tour.getTaille());
        img.setPreserveRatio(true);
        img.setOpacity(0.5);
        rayon.setRadius(tour.getPortée());
        rayon.setCenterX(tour.getX()+ (double) tour.getTaille() /2);
        rayon.setCenterY(tour.getY()+ (double) tour.getTaille() /2);
        rayon.setOpacity(0.4);
        preview.getChildren().add(rayon);
        preview.getChildren().add(img);

        preview.setId("preview");
        terrain.getChildren().add(preview);

        Dragboard db = ((Node)event.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(preview.getId());
        db.setContent(content);
        event.consume();
    }

    public Tour getTour() {
        return tour;
    }

    public void viderTour(){
        tour = null;
    }
}
