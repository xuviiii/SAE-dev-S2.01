package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Achat;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public class DragAndDrop {
    private Drag drag;
    private Drop drop;
    private Achat achat;
    private AccepterDrop ad;

    public DragAndDrop(Environnement env, Pane terrain) {
        achat = new Achat(env);
        drag = new Drag(terrain,env);
        drop = new Drop(achat, terrain,drag, env);
        ad = new AccepterDrop(achat, terrain,drag);
        terrain.setOnDragDropped(e -> drop.handle(e));
        terrain.setOnDragOver(e -> ad.handle(e));

    }

    public void objetdrag(Pane magasin){
        for(Node p:magasin.getChildren()){
            p.setOnDragDetected(e ->  drag.handle(e));
        }
    }
}
