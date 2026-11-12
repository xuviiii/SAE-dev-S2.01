package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Fabrique;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.GobelinNoir;

public class FabriqueGobelinNoir implements FabriquePerso{

    @Override
    public Personnage créerPerso() {
        return new GobelinNoir();
    }
}
