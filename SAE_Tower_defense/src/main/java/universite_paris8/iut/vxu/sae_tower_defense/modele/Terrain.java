package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.List;

public class Terrain {
    private int indiceCible = 25;
    private int[] indicesDepart = {28, 168, 398, 391};
    private int longueurMap; // static ?
    private int hauteurMap; // static ?
    private int tailleTile; // static ?
    private ObservableList<Integer> map;


    public Terrain() {
        map = FXCollections.observableArrayList(List.of(
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,
                1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,1,1,1,0,
                0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,0,1,0,
                0,0,1,1,1,0,0,0,1,1,0,0,1,1,1,1,1,1,1,/*1*/1,0,1,0,1,0,0,1,0,
                0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,0,0,1,0,
                0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,1,1,0,0,1,0,
                1,1,1,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,
                0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,
                0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,0,0,0,
                0,0,1,1,1,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,0,1,0,1,0,0,0,
                0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0,/*1*/1,1,1,0,0,0,
                0,0,1,1,1,0,0,1,1,1,1,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,
                0,0,1,0,1,0,0,1,0,0,1,1,1,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,
                0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,1,1,1,1,0,1,1,1,1,1,
                0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0));

        longueurMap =28;
        hauteurMap=15;
        tailleTile = 60;
    }

    public double toX(int indiceTerrain){
        return (indiceTerrain % longueurMap) * tailleTile;
    }

    public double toY(int indiceTerrain){
        return (indiceTerrain / longueurMap) * tailleTile;
    }

//    public int indiceTerrain(double x, double y){
//        int indiceTerrain = 0;
//        indiceTerrain = (int) (indiceTerrain + (longueurMap * (y / tailleTile)));
//        indiceTerrain = (int) (indiceTerrain + (x / tailleTile));
//        return indiceTerrain;
//    }

    public int getTailleTile() {
        return tailleTile;
    }

    public int getHauteurMap() {
        return hauteurMap;
    }

    public int getLongueurMap() {
        return longueurMap;
    }

    public ObservableList<Integer> getMap() {
        return map;
    }

    public  int genererIndiceDepartAlea(){
        int i = (int) (Math.random() * indicesDepart.length);
        return indicesDepart[i];
    }

    public  int getIndiceCible() {
        return indiceCible;
    }

    public boolean estIndiceDepart(int indice) {
        for (int i : indicesDepart) {
            if (i == indice)
                return true;
        }
        return false;
    }
}
