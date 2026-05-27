package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class Menu implements EventHandler<MouseEvent> {

    private Map map;
    private Pane terrain;
    private static Pane menu = new Pane();

    public Menu(Map map, Pane terrain) {
        this.map = map;
        this.terrain = terrain;
        menu.setStyle("-fx-background-color: #c19a9a;");
        terrain.getChildren().add(menu);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("click");
        boolean tourClique = false;
        int i=0;
        while (i<map.getTours().size()&&!tourClique){
            if (mouseEvent.getX()>=map.getTours().get(i).getX()&&mouseEvent.getX()<=map.getTours().get(i).getX()+map.getTours().get(i).getTaille()&&mouseEvent.getY()>=map.getTours().get(i).getY()&&mouseEvent.getY()<=map.getTours().get(i).getY()+map.getTours().get(i).getTaille()){

                System.out.println("Tour clique");
                Label stats = new Label("Stats");
                Button améliorer = new Button("Améliorer");
                Button vendre = new Button("Vendre");
                VBox menuContenu = new VBox(stats,améliorer,vendre);

                if (!menu.getChildren().contains(menuContenu))
                    menu.getChildren().add(menuContenu);
                stats.setText("Stats :\ndegat : "+map.getTours().get(i).getDégât()+", Portée : "+map.getTours().get(i).getPortée()+", Position : x"+map.getTours().get(i).getX()+", y"+map.getTours().get(i).getY());
                menu.setTranslateX(map.getTours().get(i).getX()+map.getTours().get(i).getTaille());
                menu.setTranslateY(map.getTours().get(i).getY()+map.getTours().get(i).getTaille());
                améliorer.setOnAction(new Améliorer(map.getTours().get(i),stats));
                vendre.setOnAction(new Vendre(map,map.getTours().get(i),menu));
                tourClique = true;
            }
            i++;
        }
        if (!tourClique){
            menu.getChildren().clear();
        }
    }
}