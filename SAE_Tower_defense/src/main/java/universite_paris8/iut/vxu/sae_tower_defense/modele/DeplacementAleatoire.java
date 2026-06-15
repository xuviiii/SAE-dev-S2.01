package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeplacementAleatoire extends Deplacement{

    private int indicePrecedent;
    private int indiceSuivant;

    public DeplacementAleatoire(Environnement env){
        super(env);
        indicePrecedent = -1;
        indiceSuivant = -1;
    }
    @Override
    public List<Integer> parcours(int source) {
        return parcours(source, getEnv().getTerrain().getIndiceCible());
    }

    @Override
    public List<Integer> parcours(int source, int cible) {

        List<Integer> chemin = new ArrayList<>();

        if(indiceSuivant == -1  || source == indiceSuivant){
            chemin = deplacementAlea(source);
        } else {
            // chemin.clear();
            chemin.add(source);
            chemin.add(indiceSuivant);
        }

        return chemin;
    }

    private List<Integer> deplacementAlea(int source){

        List<Integer> chemin = new ArrayList<>();
        List<Integer> indicesCroissement = new ArrayList<>();
        chemin.add(source);

        List<Integer> adjacents = adjacents(source);

        if(adjacents.size() == 1 && getEnv().getTerrain().estIndiceDepart(source)){
            chemin.add(adjacents.get(0));
            indicePrecedent = source;
            indiceSuivant = adjacents.get(0);
        } else if(adjacents.size() > 1){
            for (Integer adjacent: adjacents) {
                if(adjacent != indicePrecedent){
                    indicesCroissement.add(adjacent);
                }
            }

            Collections.shuffle(indicesCroissement);
            indicePrecedent = source;
            indiceSuivant = indicesCroissement.get(0);
            chemin.add(indiceSuivant);
        }

        return chemin;
    }

}
