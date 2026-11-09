package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Fabrique;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.GobelinVert;

public class FabriqueGobelinVert implements FabriquePerso{

    @Override
    public Personnage créerPerso(Environnement environnement) {
        return new GobelinVert(environnement);
    }
}
