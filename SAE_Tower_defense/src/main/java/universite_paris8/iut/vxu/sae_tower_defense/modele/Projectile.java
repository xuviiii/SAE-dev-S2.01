package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class Projectile extends Entite {

    private static int compteur = 0;

    private double dx;
    private double dy;
    private int portee;
    private double xInitial;
    private double yInitial;
    private int degat;

    public Projectile(double x, double y, int vitesse, Environnement env, int taille, double dx, double dy, int portee, int degat) {
        super("pr"+compteur,x,y,vitesse,env,taille);
        compteur++;
        this.dx = dx;
        this.dy = dy;
        this.portee = portee;
        xInitial = x;
        yInitial = y;
        this.degat = degat;
    }

    public int getDegat() {
        return degat;
    }

    public void avancer(){
        super.setX(super.getX()+dx*super.getVitesse());
        super.setY(super.getY()+dy*super.getVitesse());
    }

    public boolean horsPortee(){
        return (int) (Math.abs(super.getX()-(xInitial)))>portee || (int) (Math.abs(super.getY()-(yInitial)))>portee || getX()<0 || getX()>getEnv().getTerrain().getLongueurMap()*getEnv().getTerrain().getTailleTile() || getY()<0 || getY()>getEnv().getTerrain().getHauteurMap()*getEnv().getTerrain().getTailleTile();
    }

    public Personnage projectileTouche(){
        for (Personnage personnage : super.getEnv().getPersonnages())
            if (personnage.estTouché(super.getX(), super.getY()))
                return personnage;
        return null;
    }

    public abstract void attaquer(Personnage personnage);

    @Override
    public void action() {
        avancer();
        Personnage personnageTouche = projectileTouche();
        if (personnageTouche != null)
            attaquer(personnageTouche);
        else if (horsPortee())
            super.getEnv().getProjectiles().remove(this);
    }
}
