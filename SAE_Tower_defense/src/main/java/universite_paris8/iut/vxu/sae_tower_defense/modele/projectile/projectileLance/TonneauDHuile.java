package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ZoneDeFlamme;

public class TonneauDHuile extends ProjectileLance {

    private double xCible;
    private double yCible;
    private double multiplicateurDurer;

    public TonneauDHuile(double x, double y, int degat, double dx, double dy, int porteeTour, double xCible, double yCible, double multiplicateurDurer) {
        super(x, y, 3, 10, degat, dx, dy, porteeTour);
        this.xCible = xCible;
        this.yCible = yCible;
        this.multiplicateurDurer =multiplicateurDurer;
    }

    @Override
    public void enPlus(Personnage personnage) {
        Environnement.getEnvironnement().getProjectiles().remove(this);
        Environnement.getEnvironnement().getProjectiles().add(new ZoneDeFlamme(personnage.getX(), personnage.getY(), getDegat(), multiplicateurDurer));
    }

    @Override
    public void action() {
        avancer();
        if (xCible+32>getX()&&xCible-32<getX()&&yCible+32>getY()&&yCible-32<getY()){
            Environnement.getEnvironnement().getProjectiles().remove(this);
            Environnement.getEnvironnement().getProjectiles().add(new ZoneDeFlamme(xCible, yCible, getDegat(), multiplicateurDurer));
        }
    }
}
