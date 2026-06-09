package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class TonneauDHuile extends ProjectileLance {

    private double xCible;
    private double yCible;

    public TonneauDHuile(double x, double y, int vitesse, Environnement env, int taille, double dx, double dy, int portee, int degat, double xCible, double yCible) {
        super(x, y, vitesse, env, taille, degat, dx, dy, portee);
        this.xCible = xCible;
        this.yCible = yCible;
    }

    @Override
    public void attaquer(Personnage personnage) {
        personnage.subirDegat(super.getDegat());
        super.getEnv().getProjectiles().remove(this);
        super.getEnv().getProjectiles().add(new ZoneDeFlamme(personnage.getX(), personnage.getY(), super.getEnv()));
    }

    @Override
    public void action() {
        avancer();
        if (xCible+32>getX()&&xCible-32<getX()&&yCible+32>getY()&&yCible-32<getY()){
            super.getEnv().getProjectiles().remove(this);
            super.getEnv().getProjectiles().add(new ZoneDeFlamme(xCible, yCible, super.getEnv()));
        }
    }
}
