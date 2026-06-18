package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.BFS;

public class GobelinNoir extends Personnage {
    public GobelinNoir( Environnement env){
        super(20, 1, 15, 32, env, new BFS(env), 5);
    }
}