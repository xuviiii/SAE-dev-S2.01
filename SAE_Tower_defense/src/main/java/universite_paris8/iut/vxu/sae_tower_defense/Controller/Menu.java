package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public class Menu implements EventHandler<MouseEvent> {

    private Environnement map;
    private Pane terrain;
    private static Pane menu = new Pane();
    private static Label stats = new Label("Stats");
    private static Button améliorer = new Button("Améliorer");
    private static Button vendre = new Button("Vendre");
    private static VBox menuContenu = new VBox(stats,améliorer,vendre);

    public Menu(Environnement map, Pane terrain) {
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

                if (!menu.getChildren().contains(menuContenu))
                    menu.getChildren().add(menuContenu);
                stats.setText("Stats :\ndegat : "+map.getTours().get(i).getDégât()+", Portée : "+map.getTours().get(i).getPortée()+", Position : x"+map.getTours().get(i).getX()+", y"+map.getTours().get(i).getY());
                menu.setTranslateX(mouseEvent.getX());
                menu.setTranslateY(mouseEvent.getY());
                améliorer.setOnAction(new Améliorer(map.getTours().get(i),stats));
                vendre.setOnAction(new Améliorer(map.getTours().get(i),stats));
                tourClique = true;
            }
            i++;
        }
        if (!tourClique){
            menu.getChildren().clear();
        }
    }
}