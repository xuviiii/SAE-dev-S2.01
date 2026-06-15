package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public class Dijkstra extends Deplacement {

    public Dijkstra(Environnement env) {
        super(env);
    }

    private Map<Integer, Integer> dijkstra(int source) {

        class IndiceCout implements Comparable<IndiceCout> {

            private int indice;
            private int cout;

            public IndiceCout(int indice, int cout) {
                this.indice = indice;
                this.cout = cout;
            }

            @Override
            public int compareTo(IndiceCout indiceCout) {
                return this.cout - indiceCout.cout;
            }
        }

        PriorityQueue<IndiceCout> fifo = new PriorityQueue<>();
        Map<Integer, Integer> predecesseurs = new HashMap<>();
        Map<Integer, Integer> couts = new HashMap<>();

        predecesseurs.put(source, null);
        couts.put(source, 0);
        fifo.add(new IndiceCout(source, 0));

        while (!fifo.isEmpty()) {

            Integer courant = fifo.poll().indice;

            for (Integer adjacent : adjacents(courant)) {

                int cout = couts.get(courant) + getEnv().getTerrain().getMap().get(adjacent);

                if ((predecesseurs.get(adjacent) == null && adjacent != source) || cout < couts.get(adjacent)) {

                    if (predecesseurs.get(adjacent) != null) {
                        fifo.removeIf((IndiceCout indiceCout) -> indiceCout.indice == adjacent);
                    }

                    predecesseurs.put(adjacent, courant);
                    couts.put(adjacent, cout);
                    fifo.add(new IndiceCout(adjacent, cout));
                }
            }
        }

        return predecesseurs;
    }

    @Override
    public List<Integer> parcours(int source){
        Map<Integer, Integer> predecesseurs = dijkstra(source);
        return cheminVersCible(predecesseurs, source);
    }

    @Override
    public List<Integer> parcours(int source, int cible){
        Map<Integer, Integer> predecesseurs = dijkstra(source);
        return cheminVersCible(predecesseurs, source, cible);
    }

}
