package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Achat {
    private Environnement map;

    public Achat(Environnement map) {
        this.map = map;
    }


    public Tour selctionerTour(String id, double x, double y) {
        Tour nvTour;


        if (id.equals("flêche")) {
            nvTour = new Tour(x+16, y+16, 100, 10, 32, map, 100,10);

        } else {
            return null;
        }
        return nvTour;
    }

    public boolean peutEtrePoser(double x,double y, int taille){
        return ((!surAutreTour(x,y)) && horsChemin(x,y,taille));
    }

    public boolean surAutreTour(double x,double y){
        for (Tour t:map.getTours()){
            if(x> (t.getX()-t.getTaille()-1) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()-t.getTaille()-1) && y<(t.getY()+t.getTaille()+1)){
                return true;
            }
        }
        return false;
    }

    public boolean horsChemin(double x,double y, int taille){
        if(map.getTerrain().getMap().get((int)(((x-(x%map.getTerrain().getTailleTile()))/ map.getTerrain().getTailleTile())+((y-y%map.getTerrain().getTailleTile())/ map.getTerrain().getTailleTile())*map.getTerrain().getLongueurMap())) == 1
                || map.getTerrain().getMap().get((int)((((x+taille)-((x+taille)%map.getTerrain().getTailleTile()))/ map.getTerrain().getTailleTile())+(((y+taille)-(y+taille)%map.getTerrain().getTailleTile())/ map.getTerrain().getTailleTile())*map.getTerrain().getLongueurMap())) == 1){
            return false;
        }
        else {
            return true;
        }
    }
}
