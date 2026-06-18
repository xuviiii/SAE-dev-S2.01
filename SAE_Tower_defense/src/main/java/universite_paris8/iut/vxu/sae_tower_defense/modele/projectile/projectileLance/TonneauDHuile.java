package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ZoneDeFlamme;

public class TonneauDHuile extends ProjectileLance {

    private double xCible;
    private double yCible;
    private double multiplicateurDurer;

    public TonneauDHuile(double x, double y, Environnement env, int degat, double dx, double dy, int porteeTour, double xCible, double yCible, double multiplicateurDurer) {
        super(x, y, 3, env, 10, degat, dx, dy, porteeTour);
        this.xCible = xCible;
        this.yCible = yCible;
        this.multiplicateurDurer =multiplicateurDurer;
    }

    @Override
    public void attaquer(Personnage personnage) {
        personnage.subirDegat(super.getDegat());
        super.getEnv().getProjectiles().remove(this);
        super.getEnv().getProjectiles().add(new ZoneDeFlamme(personnage.getX(), personnage.getY(), super.getEnv(),getDegat(), multiplicateurDurer));
    }

    @Override
    public void action() {
        avancer();
        if (xCible+32>getX()&&xCible-32<getX()&&yCible+32>getY()&&yCible-32<getY()){
            super.getEnv().getProjectiles().remove(this);
            super.getEnv().getProjectiles().add(new ZoneDeFlamme(xCible, yCible, super.getEnv(),getDegat(), multiplicateurDurer));
        }
    }
}
