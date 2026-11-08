package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;

import java.util.*;
import java.util.function.Predicate;

public abstract class Deplacement {


    private int indicePrecedent;
    private int indiceSuivant;
    private int tmp;

    public Deplacement(){

        indicePrecedent = -1;
        indiceSuivant = -1;
        tmp = -1;
    }



    public int getIndicePrecedent() {
        return indicePrecedent;
    }

    public int getIndiceSuivant() {
        return indiceSuivant;
    }

    public void setIndicePrecedent(int indicePrecedent) {
        this.indicePrecedent = indicePrecedent;
    }

    public void setIndiceSuivant(int indiceSuivant) {
        this.indiceSuivant = indiceSuivant;
    }

    public abstract List<Integer> parcours(int source);

    public abstract List<Integer> parcours(int source, int cible);

    public static List<Integer> indicesCasesContigues( Integer indice, Predicate<Integer> predicate){

        if (indice < 0 || indice > Terrain.getTerrain().getMap().size() - 1){
            throw new IllegalArgumentException();
        }

        List<Integer> contigues = new ArrayList<>();

        if(indice > Terrain.getTerrain().getLongueurMap() - 1) {

            if (predicate.test(
                    Terrain.getTerrain().getMap().get(indice - Terrain.getTerrain().getLongueurMap())
            )) {
                contigues.add(indice - Terrain.getTerrain().getLongueurMap());
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

        if(indice < Terrain.getTerrain().getMap().size() - Terrain.getTerrain().getLongueurMap()){

            if(predicate.test(
                    Terrain.getTerrain().getMap().get(indice + Terrain.getTerrain().getLongueurMap())
            )){
                contigues.add(indice + Terrain.getTerrain().getLongueurMap());
            }
            // adjacent sud/ouest
//            if(indice % longueurMap != 0 && map.get(indice).equals(map.get(indice + longueurMap - 1))){
//                adjacents.add(indice + longueurMap - 1);
//            }
            // adjacent sud/est
//            if((indice + 1) % longueurMap != 0 && map.get(indice).equals(map.get(indice + longueurMap + 1))){
//                adjacents.add(indice + longueurMap + 1);
//            }
        }
        if(indice % Terrain.getTerrain().getLongueurMap() != 0
                && predicate.test(Terrain.getTerrain().getMap().get(indice - 1))){
            contigues.add(indice - 1);
        }
        if((indice + 1) % Terrain.getTerrain().getLongueurMap() != 0
                && predicate.test(Terrain.getTerrain().getMap().get(indice + 1))){
            contigues.add(indice + 1);
        }
        return contigues;
    }

    public static List<Integer> adjacents(int indice){
        return indicesCasesContigues( indice, i -> i > 0);
    }

    public List<Integer> cheminVersCible(Map<Integer, Integer> predecesseurs,  int cible) {
        List<Integer> chemin = new ArrayList<>();
        Integer courant = cible;
        while(courant != null){
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }

        Collections.reverse(chemin);

        return chemin;
    }

    public int tileSuivante(int source, int cible) {
        List<Integer> chemin = parcours(source, cible);

        int tileSuivante = (chemin.size() == 1) ? chemin.get(0) : chemin.get(1);

        if(source == indiceSuivant){
            indicePrecedent = tmp;
        }

        indiceSuivant = tileSuivante;
        tmp = source;

        return tileSuivante;
    }
    
}
