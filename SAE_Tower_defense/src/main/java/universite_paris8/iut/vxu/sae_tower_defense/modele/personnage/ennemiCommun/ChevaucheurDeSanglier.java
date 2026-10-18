package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.BFS;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class ChevaucheurDeSanglier extends Personnage {

    public ChevaucheurDeSanglier(Environnement env) {
        super(50, 1.5, 20, 32, env, new BFS(env), 10);
    }

    @Override
    public void action() {
        setMalusVitesse(1);
        super.action();
    }
}
