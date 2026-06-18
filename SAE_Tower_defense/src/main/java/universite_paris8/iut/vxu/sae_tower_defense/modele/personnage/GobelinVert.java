package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.BFS;

public class GobelinVert extends Personnage {

    public GobelinVert( Environnement env){
        super(10, 1, 10, 32, env, new BFS(env), 5);
    }
}
