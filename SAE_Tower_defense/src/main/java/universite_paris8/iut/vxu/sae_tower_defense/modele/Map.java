package universite_paris8.iut.vxu.sae_tower_defense.modele;


import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Map {
    private int longueurMap;
    private ObservableList<Integer> map;
    private ObservableList<Personnage> personnages;
    private ObservableList<Tour> tours;

    public Map(){
        map = FXCollections.observableArrayList(List.of(
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,
                0,0,1,1,1,0,0,0,1,1,0,0,1,1,1,1,1,1,
                0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,0,0,
                0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,
                1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,
                0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,
                0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,
                0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0));
        personnages= FXCollections.observableArrayList();
        tours=FXCollections.observableArrayList();
        longueurMap =18;
    }

    public int getLongueurMap() {
        return longueurMap;
    }

    public ObservableList<Integer> getMap() {
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
