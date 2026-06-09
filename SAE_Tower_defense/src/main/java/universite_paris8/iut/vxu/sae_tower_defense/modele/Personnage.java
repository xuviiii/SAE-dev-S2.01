package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Personnage extends Entite {

    private static int compteur = 0;
    private int pv;
    private int degat;
    private int indiceTerrain;
    private int malusVitesse;

    public Personnage(int pv, double x, double y, int vitesse,  int degat,
                      int taille, int indiceTerrain, Environnement map){
        super("p"+compteur,x,y,vitesse,map,taille);
        compteur++;

        this.pv = pv;
        this.degat = degat;
        this.indiceTerrain = indiceTerrain;
        malusVitesse = 1;
    }

    public int getPv() {
        return pv;
    }

    public int getDegat() {
        return degat;
    }

    public void subirDegat(int degat){
        pv-=degat;
    }

    public boolean estMort(){return pv<=0;}

    public boolean estTouché(Projectile projectile){
        double yProjectileH,yProjectileB,xProjectileG,xProjectileD;
        xProjectileG = projectile.getX();
        xProjectileD = projectile.getX()+projectile.getTaille();
        yProjectileH = projectile.getY();
        yProjectileB = projectile.getY()+projectile.getTaille();
        return super.getX()<xProjectileD && super.getX()+getTaille()>xProjectileG && super.getY()<yProjectileB && super.getY()+getTaille()>yProjectileH;
    }

    private void seDeplace(){

        int suivant = getEnv().getParcours().tileSuivanteMoindreCout(indiceTerrain);

        double suivant_X = getEnv().getTerrain().toX(suivant);
        double suivant_Y = getEnv().getTerrain().toY(suivant);

        double dist_x = Math.abs(super.getX() - suivant_X);
        double dist_y = Math.abs(super.getY() - suivant_Y);

        if(super.getX() > suivant_X){
            super.setX(super.getX() - (Math.min(super.getVitesse(), dist_x)));
        }

        if(super.getX() < suivant_X){
            super.setX(super.getX() + (Math.min(super.getVitesse(), dist_x)));
        }

        if(super.getY() > suivant_Y){
            super.setY(super.getY() - (Math.min(super.getVitesse(), dist_y)));
        }

        if(super.getY() < suivant_Y){
            super.setY(super.getY() + (Math.min(super.getVitesse(), dist_y)));
        }

        if(super.getX() == suivant_X && super.getY() == suivant_Y){
            indiceTerrain = suivant;
        }

    }

    public int getIndiceTerrain(){
        return indiceTerrain;
    }

    public void setIndiceTerrain(int indiceTerrain) {
        this.indiceTerrain = indiceTerrain;
    }

    @Override
    public void action() {
        seDeplace();
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "pv=" + pv +
                ", x=" + super.getX() +
                ", y=" + super.getY() +
                ", degat=" + degat +
                '}';
    }
}
