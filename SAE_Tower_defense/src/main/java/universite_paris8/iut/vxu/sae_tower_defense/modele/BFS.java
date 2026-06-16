package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.*;

public class BFS extends Deplacement{

    DeplacementAleatoire deplacementAlea;

    public BFS(Environnement env){
        super(env);
        deplacementAlea = new DeplacementAleatoire(env);
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

    @Override
    public List<Integer> parcours(int source){

        List<Integer> parcours;
        Map<Integer, Integer> predecesseurs = bfs(source);

        parcours =  cheminVersCible(predecesseurs, source);
        if(!cheminVersCibleExiste(parcours, source)){
            parcours = deplacementAlea.parcours(source);
        }

        return parcours;
    }

    @Override
    public List<Integer> parcours(int source, int cible){

        List<Integer> parcours;
        Map<Integer, Integer> predecesseurs = bfs(source);

        parcours =  cheminVersCible(predecesseurs, source, cible);
        if(!cheminVersCibleExiste(parcours, source)){
            parcours = deplacementAlea.parcours(source);
        }

        return parcours;
    }
}
