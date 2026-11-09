package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet;

import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class EffetBrulure implements EffetProjectile {

    private int temp;

    public EffetBrulure(int temp) {
        this.temp = temp;
    }

    @Override
    public void effet(Personnage personnage) {
        personnage.ajoutTempEnflamer(temp);
    }
}
