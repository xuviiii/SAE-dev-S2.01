package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class Rocher extends ProjectileTranspercant{

    private boolean enflamme;

    public Rocher(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour, boolean enflamme) {
        super(x, y, 7, env, 40, degat, dx, dy, porteeTour);
        this.enflamme = enflamme;
    }

    @Override
    public void enPlus(Personnage personnage) {
        super.enPlus(personnage);
        if (enflamme) {
            personnage.ajoutTempEnflamer(5);
        }
    }



}
