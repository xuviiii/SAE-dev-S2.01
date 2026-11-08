package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet;

import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class EffetTeleportation implements EffetProjectile{

    @Override
    public void effet(Personnage personnage) {
        personnage.getGestionnaireDeplacementPersonnage().reculer(5);
    }
}
