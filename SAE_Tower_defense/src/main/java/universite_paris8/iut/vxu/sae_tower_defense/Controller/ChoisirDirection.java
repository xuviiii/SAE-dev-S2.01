package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class ChoisirDirection implements EventHandler<ActionEvent> {

    private Catapulte catapulte;
    private Pane menu;
    private Pane terrain;

    public ChoisirDirection(Catapulte catapulte, Pane menu, Pane terrain) {
        this.catapulte = catapulte;
        this.menu = menu;
        this.terrain = terrain;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        Circle cible = new Circle(10, Color.BLACK);
        cible.setOpacity(0.5);
        terrain.getChildren().add(cible);

        EventHandler<MouseEvent> moveHandler = e -> {
            cible.setCenterX(e.getX());
            cible.setCenterY(e.getY());
            terrain.getChildren().remove(menu);
        };

        EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                terrain.getChildren().remove(cible);

                catapulte.setxCible(e.getX());
                catapulte.setyCible(e.getY());

                terrain.removeEventHandler(MouseEvent.MOUSE_MOVED, moveHandler);
                terrain.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        };

        terrain.addEventHandler(MouseEvent.MOUSE_MOVED, moveHandler);
        terrain.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);

        actionEvent.consume();
    }
}
