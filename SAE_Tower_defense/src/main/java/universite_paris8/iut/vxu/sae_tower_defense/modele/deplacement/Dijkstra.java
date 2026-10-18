package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

import java.util.*;

public class Dijkstra extends Algorithme {

    public Dijkstra(Environnement env) {
        super(env);
    }

    public Map<Integer, Integer> predecesseurs(int source) {

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

            for (Integer adjacent : Deplacement.adjacents(getEnv(), courant)) {

                int cout = couts.get(courant) + getEnv().getTerrain().getMap().get(adjacent);

                if ((predecesseurs.get(adjacent) == null && adjacent != source) || cout < couts.get(adjacent)) {

                    predecesseurs.put(adjacent, courant);
                    couts.put(adjacent, cout);
                    fifo.add(new IndiceCout(adjacent, cout));
                }
            }
        }

        return predecesseurs;
    }

}
