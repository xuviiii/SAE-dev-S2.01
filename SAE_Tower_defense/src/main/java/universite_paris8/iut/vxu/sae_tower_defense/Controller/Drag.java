package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.TourHorsChemin;

public class Drag implements EventHandler<MouseEvent> {
    private Pane terrain;
    private Environnement env;
    private Tour tour;
    private Achat achat;


    public Drag(Pane terrain, Environnement env,Achat achat) {
        this.terrain = terrain;
        this.env = env;
        this.achat = achat;
        this.tour=null;
    }

    @Override
    public void handle(MouseEvent event) {
        Pane preview = new Pane();
        Circle rayon = new Circle();

        tour = achat.selctionerTour(((Node)event.getSource()).getId(),0,0);
        ImageView img = BankImage.getImgView(tour.getClass(), tour.getTaille());
        img.setOpacity(0.5);

        if (tour instanceof TourHorsChemin){
            rayon.setRadius(((TourHorsChemin)tour).getPortee());
            rayon.setCenterX(tour.getX());
            rayon.setCenterY(tour.getY());
            rayon.setOpacity(0.4);
        }


        preview.getChildren().add(rayon);
        preview.getChildren().add(img);

        preview.setId("preview");
        terrain.getChildren().add(preview);

        Node source = (Node) event.getSource();

        source.setOnDragDone(e -> {

            if (preview != null) {
                terrain.getChildren().remove(preview);
            }

            viderTour();
            e.consume();
        });

        Dragboard db = ((Node)event.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(preview.getId());
        db.setContent(content);
        event.consume();
    }

    private void finDrag(DragEvent event) {
        Node preview = terrain.lookup("#preview");

        if (preview != null) {
            terrain.getChildren().remove(preview);
        }

        tour = null;
    }

    public Tour getTour() {
        return tour;
    }

    public void viderTour(){
        tour = null;
    }
}
