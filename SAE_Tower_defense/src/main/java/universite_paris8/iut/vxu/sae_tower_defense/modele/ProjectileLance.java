package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public abstract class ProjectileLance extends Projectile {

    private double dx;
    private double dy;
    private int porteeTour;
    private double xInitial;
    private double yInitial;

    public ProjectileLance(double x, double y, int vitesse, Environnement env, int taille, int degat, double dx, double dy, int porteeTour) {
        super(x, y, vitesse, env, taille, degat);

        this.dx = dx;
        this.dy = dy;
        this.porteeTour = porteeTour;
        xInitial = x;
        yInitial = y;
    }

    public void avancer(){
        super.setX(super.getX()+dx*super.getVitesse());
        super.setY(super.getY()+dy*super.getVitesse());
    }

    public boolean horsPortee(){
        return (int) (Math.abs(super.getX()-(xInitial)))> porteeTour || (int) (Math.abs(super.getY()-(yInitial)))> porteeTour || getX()<0 || getX()>getEnv().getTerrain().getLongueurMap()*getEnv().getTerrain().getTailleTile() || getY()<0 || getY()>getEnv().getTerrain().getHauteurMap()*getEnv().getTerrain().getTailleTile();
    }

    public abstract void attaquer(Personnage personnage);

    @Override
    public void action() {
        avancer();
        ArrayList<Personnage> personnageTouches = projectileTouche();
        for (Personnage personnage : personnageTouches)
            attaquer(personnage);
        if (horsPortee())
            super.getEnv().getProjectiles().remove(this);
    }

}
