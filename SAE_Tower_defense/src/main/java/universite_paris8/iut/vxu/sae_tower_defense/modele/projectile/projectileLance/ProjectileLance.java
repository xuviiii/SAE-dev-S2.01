package universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.Projectile;

public abstract class ProjectileLance extends Projectile {

    private double dx;
    private double dy;
    private int porteeTour;
    private double xInitial;
    private double yInitial;

    public ProjectileLance(double x, double y, int vitesse, int taille, int degat, double dx, double dy, int porteeTour) {
        super(x, y, vitesse, taille, degat);

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
        return (int) (Math.abs(super.getX()-(xInitial)))> porteeTour || (int) (Math.abs(super.getY()-(yInitial)))> porteeTour || getX()<0 || getX()> Terrain.getTerrain().getLongueurMap()* Terrain.getTerrain().getTailleTile() || getY()<0 || getY()> Terrain.getTerrain().getHauteurMap()* Terrain.getTerrain().getTailleTile();
    }

    public double getAngle(){return Math.toDegrees(Math.atan2(dy, dx));}

    @Override
    public void action() {
        avancer();
        super.action();
    }

    @Override
    public void enPlus(Personnage personnage) {
        if (horsPortee())
            Environnement.getEnvironnement().getProjectiles().remove(this);
    }

}
