package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Dijkstra;

public class ChevalDeTroie extends Personnage {

    private final Invocation invocation;

    public ChevalDeTroie(Environnement env, int nbPersonnages) {
        super(30, 0.5, 10, 48, env, new Dijkstra(env), 5);
        invocation = new InvocationApresMort(nbPersonnages,getDeplacement().parcours(getIndiceTerrain()).size());
    }

    @Override
    public void action() {
        super.action();
        invocation.action(this);
    }
}
