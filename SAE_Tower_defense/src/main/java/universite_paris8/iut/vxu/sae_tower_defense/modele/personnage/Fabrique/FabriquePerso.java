package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Fabrique;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public interface FabriquePerso {

    public Personnage créerPerso(Environnement environnement);

}
