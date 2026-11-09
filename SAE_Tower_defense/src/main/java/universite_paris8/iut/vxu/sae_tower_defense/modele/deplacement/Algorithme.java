package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;

import java.util.List;
import java.util.Map;

public abstract class Algorithme extends Deplacement {

    private final DeplacementAleatoire deplacementAlea;

    public Algorithme() {
        super();
        deplacementAlea = new DeplacementAleatoire( () -> this.getIndicePrecedent());
    }

    public abstract Map<Integer, Integer> predecesseurs(int source);

    @Override
    public List<Integer> parcours(int source){
        return parcours(source, Terrain.getTerrain().getIndiceCible());
    }

    @Override
    public List<Integer> parcours(int source, int cible) {
        List<Integer> parcours = cheminVersCible(predecesseurs(source), cible);
        if (!parcours.contains(source))
            parcours = deplacementAlea.parcours(source);
        return parcours;
    }

}
