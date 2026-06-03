package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Personnage extends Entite {

    private static int compteur = 0;
    private int pv;
    private int degat;
    private int indiceTerrain;
    private int taille;
    private Environnement map;

    public Personnage(int pv, int x, int y, int vitesse,  int degat,
                      int taille, int indiceTerrain, Environnement map){
        super("p"+compteur,x,y,vitesse);
        compteur++;

        this.pv = pv;
        this.degat = degat;
        this.taille = taille;
        this.indiceTerrain = indiceTerrain;
        this.map = map;
    }

    public int getPv() {
        return pv;
    }

    public int getDegat() {
        return degat;
    }

    public int getTaille() {
        return taille;
    }

    public void subirDegat(int degat){
        pv-=degat;
    }

    public boolean estMort(){return pv<=0;}

    public boolean estTouché(double x,double y){
        return super.getX()-1<=x && super.getX()+taille+1>=x && super.getY()-1<=y && super.getY()+taille+1>=y;
    }

    private void seDeplace(){

        BFS bfs = new BFS(map);
        int suivant = bfs.tileSuivante(indiceTerrain);

        int suivant_X = (suivant % map.getTerrain().getLongueurMap()) * map.getTerrain().getTailleTile();
        int suivant_Y = (suivant / map.getTerrain().getLongueurMap()) * map.getTerrain().getTailleTile();

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
