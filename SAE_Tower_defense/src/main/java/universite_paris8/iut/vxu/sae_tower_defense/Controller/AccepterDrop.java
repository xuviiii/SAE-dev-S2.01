package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Placement;


public class AccepterDrop implements EventHandler<DragEvent>{
    private Placement selection;
    private Pane terrain;
    private Drag drag;

    public AccepterDrop(Placement selection, Pane terrain, Drag drag) {
        this.selection = selection;
        this.terrain= terrain;
        this.drag = drag;
    }

    @Override
    public void handle(DragEvent event) {

        // vérifie si le curseur est sur le terrain et arrête l'envenement si il ne l'est pas
        if (event.getX() < 0 ||
                event.getY() < 0 ||
                event.getX() > terrain.getWidth() ||
                event.getY() > terrain.getHeight()) {

            event.consume();
            return;
        }

        Dragboard db = event.getDragboard();
        // met la preview a l'emplacement du curseur
        Node preview = terrain.lookup("#"+db.getString());
        preview.setTranslateX(event.getX());
        preview.setTranslateY(event.getY());

        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        event.consume();
    }
}
