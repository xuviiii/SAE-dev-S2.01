package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class TonneauDHuile extends Projectile {

    private double xCible;
    private double yCible;

    public TonneauDHuile(double x, double y, int vitesse, Environnement env, int taille, double dx, double dy, int portee, int degat, double xCible, double yCible) {
        super(x, y, vitesse, env, taille, dx, dy, portee, degat);
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
        Personnage personnageTouche = projectileTouche();
        if (personnageTouche != null)
            attaquer(personnageTouche);
        else if (xCible+10>getX()&&xCible-10<getX()&&yCible+10>getY()&&yCible-10<getY()){
            super.getEnv().getProjectiles().remove(this);
            super.getEnv().getProjectiles().add(new ZoneDeFlamme(xCible, yCible, super.getEnv()));
        }
    }
}
