package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Projectile extends Entite {

    private static int compteur = 0;

    private double dx;
    private double dy;
    private int portee;
    private double xInitial;
    private double yInitial;
    private int degat;

    public Projectile(int degat, double x, double y, double dx, double dy, int portee, Environnement map, int vitesse) {
        super("pr"+compteur,x,y,vitesse,map);
        compteur++;

        this.degat = degat;
        this.dx = dx;
        this.dy = dy;
        this.portee=portee;
        xInitial=x;
        yInitial=y;
    }

    public int getDegat() {
        return degat;
    }

    public void avancer(){
        super.setX(super.getX()+dx*super.getVitesse());
        super.setY(super.getY()+dy*super.getVitesse());
    }

    public boolean horsPortee(){
        return (int) (Math.abs(super.getX()-(xInitial)))>portee || (int) (Math.abs(super.getY()-(yInitial)))>portee;
    }

    public void projectileTouche(){
        int j=0;
        boolean arret=false;
        while (j<super.getEnv().getPersonnages().size()&&!arret){
            if (super.getEnv().getPersonnages().get(j).estTouché(super.getX(), super.getY())){
                super.getEnv().getPersonnages().get(j).subirDegat(degat);
                super.getEnv().getProjectiles().remove(this);
                arret=true;
            }
            else if (horsPortee()){
                super.getEnv().getProjectiles().remove(this);
                arret=true;
            }
            j++;
        }
    }

    @Override
    public void action(int temps) {
        avancer();
        projectileTouche();
    }
}
