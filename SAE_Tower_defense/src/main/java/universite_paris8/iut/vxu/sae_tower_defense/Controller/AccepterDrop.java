package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;


public class AccepterDrop implements EventHandler<DragEvent>{
    private Achat selection;
    private Pane terrain;
    private Drag drag;

    public AccepterDrop(Achat selection, Pane terrain,Drag drag) {
        this.selection = selection;
        this.terrain= terrain;
        this.drag = drag;
    }

    @Override
    public void handle(DragEvent event) {

        Dragboard db = event.getDragboard();
        Node preview = terrain.lookup("#"+db.getString());

        preview.setLayoutX(event.getX());
        preview.setLayoutY(event.getY());


        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        event.consume();
    }
}
