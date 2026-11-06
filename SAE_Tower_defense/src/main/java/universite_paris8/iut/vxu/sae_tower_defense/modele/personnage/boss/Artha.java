package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.boss;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Dijkstra;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation.Invocation;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation.InvocationMultiple;

public class Artha extends Boss {
    private Invocation invocation;

    public Artha(Environnement env) {
        super(500, 0.5, 20, 64, env, new Dijkstra(env), 1000);
        invocation = new InvocationMultiple(200);
    }

    @Override
    public void action() {
        super.action();
        invocation.action(this);
    }
}
