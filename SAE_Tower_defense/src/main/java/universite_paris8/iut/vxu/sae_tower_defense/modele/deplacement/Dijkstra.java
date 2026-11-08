package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;

import java.util.*;

public class Dijkstra extends Algorithme {

    public Dijkstra() {
        super();
    }

    public Map<Integer, Integer> predecesseurs(int source) {



        PriorityQueue<IndiceCout> fifo = new PriorityQueue<>();
        Map<Integer, Integer> predecesseurs = new HashMap<>();
        Map<Integer, Integer> couts = new HashMap<>();

        predecesseurs.put(source, null);
        couts.put(source, 0);
        fifo.add(new IndiceCout(source, 0));

        while (!fifo.isEmpty()) {

            Integer courant = fifo.poll().getIndice();

            for (Integer adjacent : Deplacement.adjacents( courant)) {

                int cout = couts.get(courant) + Terrain.getTerrain().getMap().get(adjacent);

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
