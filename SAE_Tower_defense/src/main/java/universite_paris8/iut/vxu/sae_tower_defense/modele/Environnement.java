package universite_paris8.iut.vxu.sae_tower_defense.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Environnement {

    private static int indiceCible = 71;
    private static int[] indicesDepart = {18, 108, 164};

    private int longueurMap; // static ?
    private int tailleTile; // static ?
    private IntegerProperty argent;
    private ObservableList<Integer> map;
    private ObservableList<Personnage> personnages;
    private ObservableList<Tour> tours;
    private ObservableList<Projectile> projectiles;

    public Environnement(){
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
        projectiles = FXCollections.observableArrayList();
        longueurMap =18;
        tailleTile = 60;
        argent = new SimpleIntegerProperty();

    }

    public static int getIndiceCible() {
        return indiceCible;
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

    public void argentDeBase(){
        argent.set(999999999);
    }

    public void ajouterArgent(int ajout) {
        if(argent.get()+ajout > 0) {
            argent.set(argent.get() + ajout);
        }
    }

    public void enleverArgent(int enlever) {
        if(argent.get()-enlever > 0) {
            argent.set(argent.get() - enlever);
        }
    }

    public int getArgent() {
        return argent.get();
    }

    public IntegerProperty argentProperty() {
        return argent;
    }

    public int getTailleTile() {return tailleTile;}

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

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void ajouterPersonnage(Personnage p){
        personnages.add(p);
    }

    public void ajouterTour(Tour t){
        tours.add(t);
    }

    public void faireUnTour(int temps){

        int j;

        int indDepart = genererIndiceDepartAlea();

        if(temps % 20 == 0) {

            ajouterPersonnage(new Personnage(10,
                    toX(indDepart),
                    toY(indDepart),
                    1,
                    10,
                    32,
                    indDepart));
        }

        for (int i=0;i<personnages.size();i++){
            personnages.get(i).action(this);
        }
        if (temps%50==0)
            for (int i=0;i<tours.size();i++){
                tours.get(i).action();
            }
        for (int i=0;i<projectiles.size();i++){
            projectiles.get(i).avancer();

            j=0;
            boolean arret=false;
            while (j<getPersonnages().size()&&!arret){
                if (getPersonnages().get(j).estTouché(projectiles.get(i).getX(), projectiles.get(i).getY())){
                    getPersonnages().get(j).subirDegat(projectiles.get(i).getDegat());
                    getProjectiles().remove(projectiles.get(i));
                    arret=true;
                }
                else if (projectiles.get(i).horsPortee()){
                    getProjectiles().remove(projectiles.get(i));
                    arret=true;
                }
                j++;
            }
        }

        for (int i=0;i<personnages.size();i++){
            if (personnages.get(i).estMort())
                personnages.remove(personnages.get(i));
        }
    }

    private static int genererIndiceDepartAlea(){
        int i = (int) (Math.random() * indicesDepart.length);
        return indicesDepart[i];
    }
}
