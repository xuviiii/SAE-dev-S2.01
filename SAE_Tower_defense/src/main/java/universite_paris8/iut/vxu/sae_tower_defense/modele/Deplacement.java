package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public abstract class Deplacement {

    Environnement env;

    public Deplacement(Environnement env){
        this.env = env;
    }

    public abstract List<Integer> cheminVersCible(int source);

    public Set<Integer> adjacents(int indice){

        if (indice < 0 || indice > env.getTerrain().getMap().size() - 1){
            throw new IllegalArgumentException();
        }

        var adjacents = new HashSet<Integer>();

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

    public List<Integer> cheminVersCible(Map<Integer, Integer> predecesseurs) {
        List<Integer> chemin = new ArrayList<>();
        Integer courant = env.getTerrain().getIndiceCible();
        while(courant != null){
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    public int tileSuivante(int source) {
        List<Integer> chemin = cheminVersCible(source);
        return (chemin.size() == 1) ? chemin.get(0) : chemin.get(1);
    }
}
