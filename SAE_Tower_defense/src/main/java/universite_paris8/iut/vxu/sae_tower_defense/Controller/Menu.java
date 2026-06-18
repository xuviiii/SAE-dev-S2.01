package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.TourHorsChemin;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeMage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;

public class Menu implements EventHandler<MouseEvent> {

    private Environnement map;
    private Pane terrain;
    private Pane menu;
    private Label stats;
    private Button améliorer;
    private Button améliorerMage2;
    private Button vendre;
    private Button choisirDirection;
    private VBox menuContenu;
    private Circle rayon;

    public Menu(Environnement map, Pane terrain) {
        this.map = map;
        this.terrain = terrain;
        stats = new Label("Stats");
        améliorer = new Button("Améliorer");
        améliorerMage2 = new Button("Améliorer 2");
        vendre = new Button("Vendre");
        choisirDirection = new Button("Choisir direction");
        menuContenu = new VBox(stats,améliorer,vendre);
        menu = new Pane(menuContenu);
        menu.setStyle("-fx-background-color: #c19a9a;");
        rayon = new Circle(10);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Tour tour;
        boolean tourClique = false;
        int i=0;

        terrain.getChildren().remove(menu);

        while (i<map.getTours().size()&&!tourClique){
            tour = map.getTours().get(i);
            if (mouseEvent.getX()>=tour.getX()&&mouseEvent.getX()<=tour.getX()+tour.getTaille()&&mouseEvent.getY()>=tour.getY()&&mouseEvent.getY()<=tour.getY()+tour.getTaille()){


                if (tour instanceof TourHorsChemin){
                    rayon.setRadius(((TourHorsChemin)tour).getPortee());
                    rayon.setCenterX(tour.getX()+ (double) tour.getTaille() /2);
                    rayon.setCenterY(tour.getY()+ (double) tour.getTaille() /2);
                    rayon.setOpacity(0.3);
                    rayon.setId(tour.getId()+"r");
                }

                if (!terrain.getChildren().contains(rayon))
                    terrain.getChildren().add(rayon);

                //Ajoute au terrain le menu
                terrain.getChildren().add(menu);

                //Ajoute au menu les données et boutons associés à la tour
                if (!menu.getChildren().contains(menuContenu))
                    menu.getChildren().add(menuContenu);

                //Mets le menu à l'emplacement de la tour
                menu.setTranslateX(tour.getX()+tour.getTaille());
                menu.setTranslateY(tour.getY()+tour.getTaille());

                améliorer.setOnAction(new Améliorer(tour));
                Tour finalTour = tour;
                améliorer.setOnMouseClicked(e -> refreshMenu(finalTour));

                vendre.setOnAction(new Vendre(map,tour,menu));

                if (tour instanceof Catapulte){
                    if (!menuContenu.getChildren().contains(choisirDirection))
                        menuContenu.getChildren().add(choisirDirection);
                    choisirDirection.setOnAction(new ChoisirDirection((Catapulte) tour,menu,terrain));
                }
                else
                    menuContenu.getChildren().remove(choisirDirection);

                refreshMenu(tour);

                tourClique = true;
            }
            i++;
        }
        if (!tourClique){
            terrain.getChildren().remove(menu);
            terrain.getChildren().remove(rayon);
        }
    }


    //Permet de rafraichir les statisques, désactiver/activer les boutons
    public void refreshMenu(Tour tour){
        if (tour.estAuNiveauMax()){
            stats.setText(tour+"\nPlus d'amélioration");
            améliorer.setDisable(true);
        }
        else {
            améliorer.setDisable(false);
            if (tour.getEnv().getArgent()>tour.prixAmelioration())
                stats.setText(tour+"\nPrix amélioration : "+tour.prixAmelioration());
            else
                stats.setText(tour+"\nPrix amélioration : "+tour.prixAmelioration()+"\nPas assez d'argent");
        }

        if (tour instanceof TourDeMage && tour.getNiveau() == 1){
            if (!menuContenu.getChildren().contains(améliorerMage2))
                menuContenu.getChildren().add(améliorerMage2);
            améliorerMage2.setOnAction(new AméliorerMageBP2(tour));
            améliorerMage2.setOnMouseClicked(e -> {
                refreshMenu(tour);
            });
        }
        else
            menuContenu.getChildren().remove(améliorerMage2);
    }
}