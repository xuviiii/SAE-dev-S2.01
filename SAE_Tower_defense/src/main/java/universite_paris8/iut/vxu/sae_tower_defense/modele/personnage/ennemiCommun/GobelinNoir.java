package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.BFS;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class GobelinNoir extends Personnage {
    public GobelinNoir(){
        super(20, 1, 15, 32, new BFS(), 5);
    }
}