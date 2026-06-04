package universite_paris8.iut.vxu.sae_tower_defense.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Environnement {


    private Terrain terrain;
    private IntegerProperty argent;
    private ObservableList<Integer> map;
    private ObservableList<Personnage> personnages;
    private ObservableList<Tour> tours;
    private ObservableList<Projectile> projectiles;
    private Parcours parcours;

    public Environnement(){

        personnages= FXCollections.observableArrayList();
        tours=FXCollections.observableArrayList();
        projectiles = FXCollections.observableArrayList();
        argent = new SimpleIntegerProperty();
        terrain = new Terrain();
        parcours = new Parcours(this);
    }


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

    public Terrain getTerrain() {
        return terrain;
    }

    public IntegerProperty argentProperty() {
        return argent;
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

    public Parcours getParcours() {
        return parcours;
    }

    public void ajouterPersonnage(Personnage p){
        personnages.add(p);
    }

    public void ajouterTour(Tour t){
        tours.add(t);
    }

    public void faireUnTour(int temps){

        int j;

        int indDepart = Terrain.genererIndiceDepartAlea();

        if(temps % 100 == 0) {

//            ajouterPersonnage(new Personnage(10,
//                    terrain.toX(indDepart),
//                    terrain.toY(indDepart),
//                    1,
//                    10,
//                    32,
//                    indDepart,
//                    this));
            ajouterPersonnage(new GobelinVert(terrain.toX(indDepart), terrain.toY(indDepart), indDepart, this));
        }

        if(temps % 200 == 0){
            ajouterPersonnage(new Pretre(terrain.toX(indDepart), terrain.toY(indDepart), indDepart, this, 100, 1));
        }



        for (int i=0;i<personnages.size();i++){
            personnages.get(i).action(temps);
        }
        //if (temps%50==0)
        for (int i=0;i<tours.size();i++){
            tours.get(i).action(temps);
        }
        for (int i=0;i<projectiles.size();i++){
            projectiles.get(i).action(temps);
        }

        for (int i=0;i<personnages.size();i++){
            if (personnages.get(i).estMort())
                personnages.remove(personnages.get(i));
        }
    }

}
