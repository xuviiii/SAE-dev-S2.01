package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public class BFS {

    Environnement env;

    public BFS(Environnement env){
        this.env = env;
    }

    private Set<Integer> adjacents(int indice){

        if (indice < 0 || indice > env.getTerrain().getMap().size() - 1){
            throw new IllegalArgumentException();
        }

        var adjacents = new HashSet<Integer>();

        if(indice > env.getTerrain().getLongueurMap() - 1) {
            if (env.getTerrain().getMap().get(indice).equals(env.getTerrain().getMap().get(indice - env.getTerrain().getLongueurMap()))) {
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
        if(indice < env.getTerrain().getMap().size() - env.getTerrain().getLongueurMap()){ // !
            if(env.getTerrain().getMap().get(indice).equals(env.getTerrain().getMap().get(indice + env.getTerrain().getLongueurMap()))){
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
        if(indice % env.getTerrain().getLongueurMap() != 0 && env.getTerrain().getMap().get(indice).equals(env.getTerrain().getMap().get(indice - 1))){
            adjacents.add(indice - 1);
        }
        if((indice + 1) % env.getTerrain().getLongueurMap() != 0 && env.getTerrain().getMap().get(indice).equals(env.getTerrain().getMap().get(indice + 1))){
            adjacents.add(indice + 1);
        }
        return adjacents;
    }

    private java.util.Map<Integer, Integer> parcours(int source){

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

    public List<Integer> cheminVersCible(int source){
        var predecesseurs = parcours(source);
        List<Integer> chemin = new ArrayList<>();
        Integer courant = Terrain.getIndiceCible();
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
