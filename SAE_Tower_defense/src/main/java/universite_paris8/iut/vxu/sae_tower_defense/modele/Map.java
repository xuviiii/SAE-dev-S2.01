package universite_paris8.iut.vxu.sae_tower_defense.modele;


import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Map {
    private int[][] map;
    private ObservableList<Personnage> personnages;
    private ObservableList<Tour> tours;

    public Map(){
        map = new int[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if (j==2){
                    map[i][j] = 1;
                }
                else {
                    map[i][j] = 0;
                }
            }
        }
        personnages= FXCollections.observableArrayList();
        tours=FXCollections.observableArrayList();
    }

    public int[][] getMap() {
        return map;
    }

    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public void ajouterPersonnage(Personnage p){
        personnages.add(p);
    }

    public void ajouterTour(Tour t){
        tours.add(t);
    }

    public void faireUnTour(){
        System.out.println("Un tour ---------------------------------------------------------\n");
        for (int i=0;i<personnages.size();i++){
            personnages.get(i).action();
        }
        for (int i=0;i<tours.size();i++){
            tours.get(i).action();
        }
    }
}
