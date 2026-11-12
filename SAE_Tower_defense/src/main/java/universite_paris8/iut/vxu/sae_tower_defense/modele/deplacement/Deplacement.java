package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

import javafx.collections.ObservableList;
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

        List<Integer> map = Terrain.getTerrain().getMap();
        int longueur = Terrain.getTerrain().getLongueurMap();

        if (indice < 0 || indice > map.size() - 1)
            throw new IllegalArgumentException();

        List<Integer> contigues = new ArrayList<>();

        boolean nord = indice > longueur - 1;
        boolean sud = indice < map.size() - longueur;
        boolean ouest = (indice % longueur != 0);
        boolean est = ((indice + 1) % longueur != 0);

        if(nord) ajouterContiguesCardinal(predicate,map,indice - longueur,contigues);
        if(sud) ajouterContiguesCardinal(predicate,map,indice + longueur,contigues);
        if(ouest) ajouterContiguesCardinal(predicate,map,indice-1,contigues);
        if(est) ajouterContiguesCardinal(predicate,map,indice+1,contigues);

        /*
        if(nord && ouest && map.get(indice).equals(map.get(indice - longueur - 1)))
            contigues.add(indice - longueur - 1);
        if(nord && est && map.get(indice).equals(map.get(indice - longueur + 1)))
            contigues.add(indice - longueur + 1);
        if(sud && est && indice % longueur != 0 && map.get(indice).equals(map.get(indice + longueur - 1)))
            contigues.add(indice + longueur - 1);
        if(sud && est && (indice + 1) % longueur != 0 && map.get(indice).equals(map.get(indice + longueur + 1)))
            contigues.add(indice + longueur + 1);
        */

        return contigues;
    }

    public static void ajouterContiguesCardinal (Predicate<Integer> predicate,List<Integer> map,int indice,List<Integer> contigues){
        if (predicate.test(map.get(indice))) {
            contigues.add(indice);
        }
    }

    public static List<Integer> adjacents(int indice){
        return indicesCasesContigues(indice, i -> i > 0);
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
