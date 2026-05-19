package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Drop implements EventHandler<DragEvent> {
@Override
public void handle(DragEvent event) {
    Rectangle t;
    Dragboard db = event.getDragboard();
    boolean success = false;
        if (db.hasString()) {
            if(db.getString().equals("flêche")){
                t = new Rectangle(10,10, Color.BLACK);
                ((Pane)event.getTarget()).getChildren().add(t);
                t.setTranslateX(event.getX());
                t.setTranslateY(event.getY());
            }
        }
    event.setDropCompleted(success);
    event.consume();
    }

}
