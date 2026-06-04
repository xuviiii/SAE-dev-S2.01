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
    private Label stats = new Label("Stats");
    private Button améliorer = new Button("Améliorer");
    private Button vendre = new Button("Vendre");
    private VBox menuContenu = new VBox(stats,améliorer,vendre);

    public Menu(Environnement map, Pane terrain) {
        this.map = map;
        this.terrain = terrain;
        menu.setStyle("-fx-background-color: #c19a9a;");
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("click");
        boolean tourClique = false;
        int i=0;
        while (i<map.getTours().size()&&!tourClique){
            if (mouseEvent.getX()>=map.getTours().get(i).getX()&&mouseEvent.getX()<=map.getTours().get(i).getX()+map.getTours().get(i).getTaille()&&mouseEvent.getY()>=map.getTours().get(i).getY()&&mouseEvent.getY()<=map.getTours().get(i).getY()+map.getTours().get(i).getTaille()){

                if (!terrain.getChildren().contains(menu))
                    terrain.getChildren().add(menu);

                System.out.println("Tour clique");

                if (!menu.getChildren().contains(menuContenu))
                    menu.getChildren().add(menuContenu);
                stats.setText(map.getTours().get(i).toString());
                menu.setTranslateX(map.getTours().get(i).getX()+map.getTours().get(i).getTaille());
                menu.setTranslateY(map.getTours().get(i).getY()+map.getTours().get(i).getTaille());
                améliorer.setOnAction(new Améliorer(map.getTours().get(i),stats));
                vendre.setOnAction(new Vendre(map,map.getTours().get(i),menu));
                tourClique = true;
            }
            i++;
        }
        if (!tourClique){
            terrain.getChildren().remove(menu);
        }
    }
}