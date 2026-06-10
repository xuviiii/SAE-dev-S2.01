package universite_paris8.iut.vxu.sae_tower_defense.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Environnement {


    private Terrain terrain;
    private IntegerProperty argent;
    private IntegerProperty vie;
    private ObservableList<Integer> map;
    private ObservableList<Personnage> personnages;
    private ObservableList<Tour> tours;
    private ObservableList<Projectile> projectiles;
    private Vague vague;

    public Environnement(){

        personnages= FXCollections.observableArrayList();
        tours=FXCollections.observableArrayList();
        projectiles = FXCollections.observableArrayList();
        argent = new SimpleIntegerProperty();
        vie = new SimpleIntegerProperty();
        terrain = new Terrain();
        vague = new Vague(this);
    }

    public void vieDeBase(){vie.set(10);}

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

    public Vague getVague() {
        return vague;
    }

    public int getVie() {
        return vie.get();
    }

    public IntegerProperty vieProperty() {
        return vie;
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

    public void ajouterPersonnage(Personnage p){
        personnages.add(p);
    }

    public void ajouterTour(Tour t){
        tours.add(t);
    }

    public void faireUnTour(int temps){

        ArrayList<Personnage> aEnlever = new ArrayList<>();

        if (temps % 50 == 0) {
            vague.libererVague();
        }

        for (int i=0;i<personnages.size();i++) {
            personnages.get(i).action();
            if (personnages.get(i).getIndiceTerrain() == terrain.getIndiceCible()) {
                vie.set(vie.get() - 1);
                aEnlever.add(personnages.get(i));
            }
        }

        for (Personnage perso: aEnlever){
            personnages.remove(perso);
        }

        for (int i=0;i<tours.size();i++)
            tours.get(i).action();

        for (int i=0;i<projectiles.size();i++)
            projectiles.get(i).action();

        for (int i=0;i<personnages.size();i++){
            if (personnages.get(i).estMort())
                personnages.remove(personnages.get(i));
        }
    }

}
