package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public class BFS extends Deplacement{

    public BFS(Environnement env){
        super(env);
    }

    public Map<Integer, Integer> bfs(int source){

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

    @Override
    public List<Integer> cheminVersCible(int source){
        Map<Integer, Integer> predecesseurs = bfs(source);
        return cheminVersCible(predecesseurs);
    }

    @Override
    public List<Integer> cheminVersCible(int source, int cible){
        Map<Integer, Integer> predecesseurs = bfs(source);
        return cheminVersCible(predecesseurs, cible);
    }
}
