package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public class Parcours {

    Environnement env;

    public Parcours(Environnement env){
        this.env = env;
    }

    private Set<Integer> adjacents(int indice){

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

    private Map<Integer, Integer> parcours(int source) {

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

    private Map<Integer, Integer> parcoursMoindreCout(int source) {

        class IndiceCout implements Comparable<IndiceCout> {

            private int indice;
            private int cout;

            public IndiceCout(int indice, int cout){
                this.indice = indice;
                this.cout = cout;
            }

            @Override
            public int compareTo(IndiceCout indiceCout){
                return this.cout - indiceCout.cout;
            }
        }

        PriorityQueue<IndiceCout> fifo = new PriorityQueue<>();
        Map<Integer, Integer> predecesseurs = new HashMap<>();
        Map<Integer, Integer> couts = new HashMap<>();

        predecesseurs.put(source, null);
        couts.put(source, 0);
        fifo.add(new IndiceCout(source, 0));

        while(!fifo.isEmpty()){

            Integer courant = fifo.poll().indice;

            for(Integer adjacent : adjacents(courant)){

                int cout = couts.get(courant) + env.getTerrain().getMap().get(adjacent);

                if((predecesseurs.get(adjacent) == null && adjacent != source) || cout < couts.get(adjacent)){

                    if(predecesseurs.get(adjacent) != null){
                        fifo.removeIf((IndiceCout indiceCout) -> indiceCout.indice == adjacent); // ?
                    }

                    predecesseurs.put(adjacent, courant);
                    couts.put(adjacent, cout);
                    fifo.add(new IndiceCout(adjacent, cout));
                }
            }
        }

        return predecesseurs;
    }



    private List<Integer> cheminVersCible(int source, Map<Integer, Integer> predecesseurs) {
        List<Integer> chemin = new ArrayList<>();
        Integer courant = Terrain.getIndiceCible();
        while(courant != null){
            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    public List<Integer> cheminVersCible(int source) {
        Map<Integer, Integer> predecesseurs = parcours(source);
        return cheminVersCible(source, predecesseurs);
    }




    public int tileSuivante(int source) {
        List<Integer> chemin = cheminVersCible(source);
        return (chemin.size() == 1) ? chemin.get(0) : chemin.get(1);
    }

    public int tileSuivanteMoindreCout(int source) {
        Map<Integer, Integer> predecesseurs = parcoursMoindreCout(source);
        List<Integer> chemin = cheminVersCible(source, predecesseurs);
        return (chemin.size() == 1) ? chemin.get(0) : chemin.get(1);
    }
}
