package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Effet.EffetProjectile;

public class Rocher extends ProjectileTranspercant{

    private EffetProjectile effet;

    public Rocher(double x, double y, int degat, double dx, double dy, int porteeTour, EffetProjectile effet) {
        super(x, y, 7, 40, degat, dx, dy, porteeTour);
        this.effet = effet;
    }

    @Override
    public void enPlus(Personnage personnage) {
        super.enPlus(personnage);
        if (effet != null) {
            personnage.ajoutTempEnflamer(5);
        }
    }
}
