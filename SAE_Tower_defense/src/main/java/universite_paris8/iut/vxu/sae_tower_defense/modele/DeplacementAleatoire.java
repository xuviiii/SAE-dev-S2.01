package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeplacementAleatoire extends Deplacement{

    public DeplacementAleatoire(Environnement env){
        super(env);
    }
    @Override
    public List<Integer> parcours(int source) {
        return parcours(source, getEnv().getTerrain().getIndiceCible());
    }

    @Override
    public List<Integer> parcours(int source, int cible) {

        List<Integer> chemin = new ArrayList<>();

        if(getIndiceSuivant() == -1  || source == getIndiceSuivant()){
            chemin = deplacementAlea(source);
        } else {
            chemin.add(source);
            chemin.add(getIndiceSuivant());
        }

        return chemin;
    }

    private List<Integer> deplacementAlea(int source){

        List<Integer> chemin = new ArrayList<>();
        List<Integer> indicesCroissement = new ArrayList<>();
        chemin.add(source);

        List<Integer> adjacents = Deplacement.adjacents(getEnv(), source);

        if(adjacents.size() == 1 && getEnv().getTerrain().estIndiceDepart(source)){
            chemin.add(adjacents.get(0));
            setIndicePrecedent(source);
            setIndiceSuivant(adjacents.get(0));
        } else if(adjacents.size() > 1
                && Deplacement.indicesCasesContigues(getEnv(), source, i -> i == -1).isEmpty()){
            for (Integer adjacent: adjacents) {
                if(adjacent != getIndicePrecedent()){
                    indicesCroissement.add(adjacent);
                }
            }

            Collections.shuffle(indicesCroissement);
            setIndicePrecedent(source);
            setIndiceSuivant(indicesCroissement.get(0));
            chemin.add(getIndiceSuivant());
        }

        return chemin;
    }

}
