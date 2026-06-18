package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Placement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public class DragAndDrop {
    private Drag drag;
    private Drop drop;
    private Placement achat;
    private AccepterDrop ad;

    public DragAndDrop(Environnement env, Pane terrain, BorderPane borderPane) {
        achat = new Placement(env);
        drag = new Drag(terrain,achat);
        drop = new Drop(achat,drag, env);
        ad = new AccepterDrop(terrain);
        terrain.setOnDragDropped(e -> drop.handle(e));
        terrain.setOnDragOver(e -> ad.handle(e));


    }

    //met en place le drag pour tout les objet a drag
    public void objetdrag(Pane magasin){
        for(Node p:magasin.getChildren()){
            p.setOnDragDetected(e ->  drag.handle(e));
        }
    }
}
