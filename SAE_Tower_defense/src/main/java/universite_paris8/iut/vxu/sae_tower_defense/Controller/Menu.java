package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

public class Menu implements EventHandler<MouseEvent> {

    private Environnement map;
    private Pane terrain;
    private static Pane menu;
    private Label stats;
    private Button améliorer;
    private Button vendre;
    private Button choisirDirection;
    private VBox menuContenu;

    public Menu(Environnement map, Pane terrain) {
        this.map = map;
        this.terrain = terrain;
        menu = new Pane();
        menu.setStyle("-fx-background-color: #c19a9a;");
        stats = new Label("Stats");
        améliorer = new Button("Améliorer");
        vendre = new Button("Vendre");
        choisirDirection = new Button("Choisir direction");
        menuContenu = new VBox(stats,améliorer,vendre);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Tour tour;
        boolean tourClique = false;
        int i=0;
        while (i<map.getTours().size()&&!tourClique){
            tour = map.getTours().get(i);
            if (mouseEvent.getX()>=tour.getX()&&mouseEvent.getX()<=tour.getX()+tour.getTaille()&&mouseEvent.getY()>=tour.getY()&&mouseEvent.getY()<=tour.getY()+tour.getTaille()){

                if (!terrain.getChildren().contains(menu))
                    terrain.getChildren().add(menu);

                System.out.println("Tour clique");

                if (!menu.getChildren().contains(menuContenu))
                    menu.getChildren().add(menuContenu);
                stats.setText(tour.toString());
                menu.setTranslateX(tour.getX()+tour.getTaille());
                menu.setTranslateY(tour.getY()+tour.getTaille());
                améliorer.setOnAction(new Améliorer(tour,stats));
                vendre.setOnAction(new Vendre(map,tour,menu));

                if (tour instanceof Catapulte){
                    if (!menuContenu.getChildren().contains(choisirDirection))
                        menuContenu.getChildren().add(choisirDirection);
                    choisirDirection.setOnAction(new ChoisirDirection((Catapulte) tour,menu,terrain));
                }
                else
                    menuContenu.getChildren().remove(choisirDirection);

                tourClique = true;
            }
            i++;
        }
        if (!tourClique){
            terrain.getChildren().remove(menu);
        }
    }
}