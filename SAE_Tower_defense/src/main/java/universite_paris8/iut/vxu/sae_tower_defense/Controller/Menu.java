package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;

public class Menu implements EventHandler<MouseEvent> {

    private Map map;
    private Pane menu;
    private Label stats;

    public Menu(Map map, Pane menu, Label stats) {
        this.map = map;
        this.menu = menu;
        this.stats = stats;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("click");
        boolean tourClique = false;
        int i=0;
        while (i<map.getTours().size()&&!tourClique){
            if (mouseEvent.getX()>=map.getTours().get(i).getX()&&mouseEvent.getX()<=map.getTours().get(i).getX()+map.getTours().get(i).getTaille()&&mouseEvent.getY()>=map.getTours().get(i).getY()&&mouseEvent.getY()<=map.getTours().get(i).getY()+map.getTours().get(i).getTaille()){
                stats.setText("Stats :\ndegat : "+map.getTours().get(i).getDégât()+", Portée : "+map.getTours().get(i).getPortée()+", Position : x"+map.getTours().get(i).getX()+", y"+map.getTours().get(i).getY());
                menu.setTranslateX(mouseEvent.getX());
                menu.setTranslateY(mouseEvent.getY());
                menu.setDisable(false);
                menu.setOpacity(100);
                tourClique = true;
            }
            i++;
        }
        if (!tourClique){
            menu.setDisable(true);
            menu.setOpacity(0);
        }
    }
}