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
        personnages = FXCollections.observableArrayList();
        tours = FXCollections.observableArrayList();
        longueurMap = 18;
        tailleTile = 60;
        argent = new SimpleIntegerProperty();

    }

    public void argentDeBase(){
        argent.set(999999999);
    }

    public void ajouterArgent(int ajout) {
        if(argent.get() + ajout > 0) {
            argent.set(argent.get() + ajout);
        }
    }

    public void enleverArgent(int enlever) {
        if(argent.get() - enlever > 0) {
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

    public void ajouterPersonnage(Personnage p){
        personnages.add(p);
    }

    public void ajouterTour(Tour t){
        tours.add(t);
    }

    public void faireUnTour(){
        System.out.println("Un tour ---------------------------------------------------------\n");

        int indDepart = genererIndiceDepartAlea();

        ajouterPersonnage(new Personnage(10,
                (indDepart % longueurMap) * tailleTile,
                (indDepart / longueurMap) * tailleTile,
                4,
                10,
                20,
                indDepart));

        for (int i = 0; i < personnages.size(); i++){
            personnages.get(i).action(this);
        }
        for (int i = 0; i < tours.size(); i++){
            tours.get(i).action();
        }
    }

    private static int genererIndiceDepartAlea(){
        int i = (int) (Math.random() * indicesDepart.length);
        return indicesDepart[i];
    }

    private Set<Integer> adjacents(int indice){

        if (indice < 0 || indice > map.size() - 1){
            throw new IllegalArgumentException();
        }

        var adjacents = new HashSet<Integer>();

        if(indice > longueurMap - 1) {
            if (map.get(indice).equals(map.get(indice - longueurMap))) {
                adjacents.add(indice - longueurMap);
            }
            // adjacent nord/ouest
//            if (indice % longueurMap != 0 && map.get(indice).equals(map.get(indice - longueurMap - 1))) {
//                adjacents.add(indice - longueurMap - 1);
//            }
            // adjacent nord/est
//            if ((indice + 1) % longueurMap != 0 && map.get(indice).equals(map.get(indice - longueurMap + 1))) {
//                adjacents.add(indice - longueurMap + 1);
//            }
        }
        if(indice < map.size() - longueurMap){ // !
            if(map.get(indice).equals(map.get(indice + longueurMap))){
                adjacents.add(indice + longueurMap);
            }
            // adjacent sud/ouest
//            if(indice % longueurMap != 0 && map.get(indice).equals(map.get(indice + longueurMap - 1))){
//                adjacents.add(indice + 9);
//            }
            // adjacent sud/est
//            if((indice + 1) % longueurMap != 0 && map.get(indice).equals(map.get(indice + longueurMap + 1))){
//                adjacents.add(indice + longueurMap + 1);
//            }
        }
        if(indice % longueurMap != 0 && map.get(indice).equals(map.get(indice - 1))){
            adjacents.add(indice - 1);
        }
        if((indice + 1) % longueurMap != 0 && map.get(indice).equals(map.get(indice + 1))){
            adjacents.add(indice + 1);
        }
        return adjacents;
    }

    private java.util.Map<Integer, Integer> parcoursBFS(int source){

        List<Integer> parcours = new ArrayList<>();

        LinkedList<Integer> fifo = new LinkedList<>();
        fifo.add(source);

        java.util.Map<Integer, Integer> predecesseurs = new HashMap<>();
        predecesseurs.put(source, null);

        while(!fifo.isEmpty()){

            Integer courant = fifo.poll();
            parcours.add(courant);

            for(Integer adjacent : adjacents(courant)){

                if(!parcours.contains(adjacent)){
                    parcours.add(adjacent);
                    predecesseurs.put(adjacent, courant);
                    fifo.add(adjacent);
                }

            }
        }

        return predecesseurs;
    }

    private List<Integer> cheminVersCible(int source){
        var predecesseurs = parcoursBFS(source);
        List<Integer> chemin = new ArrayList<>();
        Integer courant = indiceCible;
        while(courant != null){
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    public int tileSuivante(int source){
        List<Integer> chemin = cheminVersCible(source);
        return (chemin.size() == 1) ? chemin.get(0) : chemin.get(1);
    }
}
