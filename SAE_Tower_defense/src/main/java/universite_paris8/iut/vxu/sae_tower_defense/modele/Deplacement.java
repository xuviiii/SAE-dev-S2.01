package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public abstract class Deplacement {

    private Environnement env;

    public Deplacement(Environnement env){
        this.env = env;
    }

    public Environnement getEnv() {
        return env;
    }

    public abstract List<Integer> parcours(int source);

    public abstract List<Integer> parcours(int source, int cible);

    public List<Integer> adjacents(int indice){

        if (indice < 0 || indice > env.getTerrain().getMap().size() - 1){
            throw new IllegalArgumentException();
        }

        List<Integer> adjacents = new ArrayList<>();

        if(indice > env.getTerrain().getLongueurMap() - 1) {

            if (env.getTerrain().getMap().get(indice - env.getTerrain().getLongueurMap()) != 0) {
                adjacents.add(indice - env.getTerrain().getLongueurMap());
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

        if(indice < env.getTerrain().getMap().size() - env.getTerrain().getLongueurMap()){// !

            if(env.getTerrain().getMap().get(indice + env.getTerrain().getLongueurMap()) != 0){
                adjacents.add(indice + env.getTerrain().getLongueurMap());
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
        if(indice % env.getTerrain().getLongueurMap() != 0 && env.getTerrain().getMap().get(indice - 1) != 0){
            adjacents.add(indice - 1);
        }
        if((indice + 1) % env.getTerrain().getLongueurMap() != 0 && env.getTerrain().getMap().get(indice + 1) != 0){
            adjacents.add(indice + 1);
        }
        return adjacents;
    }

    public List<Integer> cheminVersCible(Map<Integer, Integer> predecesseurs, int source,  int cible) {
        List<Integer> chemin = new ArrayList<>();
        Integer courant = cible;
        while(courant != null){
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }

        Collections.reverse(chemin);

        return chemin;
    }

    public List<Integer> cheminVersCible(Map<Integer, Integer> predecesseurs, int source) {
        return cheminVersCible(predecesseurs, source, env.getTerrain().getIndiceCible());
    }

    public int tileSuivante(int source, int cible) {
        List<Integer> chemin = parcours(source, cible);
        return (chemin.size() == 1) ? chemin.get(0) : chemin.get(1);
    }

    public boolean cheminVersCibleExiste(int source, int cible){
        return parcours(source, cible).contains(source);
    }
}
