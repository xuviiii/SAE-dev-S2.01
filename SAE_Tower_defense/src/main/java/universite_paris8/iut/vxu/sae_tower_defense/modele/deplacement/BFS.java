package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

import java.util.*;

public class BFS extends Algorithme {

    public BFS(Environnement env){
        super(env);
    }

    public Map<Integer, Integer> predecesseurs(int source){

        List<Integer> parcours = new ArrayList<>();

        LinkedList<Integer> fifo = new LinkedList<>();
        fifo.add(source);

        java.util.Map<Integer, Integer> predecesseurs = new HashMap<>();
        predecesseurs.put(source, null);

        while(!fifo.isEmpty()){

            Integer courant = fifo.poll();
            parcours.add(courant);

            for(Integer adjacent : Deplacement.adjacents(getEnv(), courant)){

                if(!parcours.contains(adjacent)){
                    parcours.add(adjacent);
                    predecesseurs.put(adjacent, courant);
                    fifo.add(adjacent);
                }

            }
        }

        return predecesseurs;
    }
}
