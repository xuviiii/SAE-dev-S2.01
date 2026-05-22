package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Achat {
    private Map map;

    public Achat(Map map) {
        this.map = map;
    }

    public boolean horsChemin(double x,double y, int taille){
        if(map.getMap().get((int)(((x-(x%map.getTailleTile()))/ map.getTailleTile())+((y-y%map.getTailleTile())/ map.getTailleTile())*map.getLongueurMap())) == 1
            || map.getMap().get((int)((((x+taille)-((x+taille)%map.getTailleTile()))/ map.getTailleTile())+(((y+taille)-(y+taille)%map.getTailleTile())/ map.getTailleTile())*map.getLongueurMap())) == 1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean placerTour(String id, double x, double y){
        Tour nvTour;

        //vérifie si il y deja une tour a ces cordonnée
        for (Tour t:map.getTours()){
            if(x> (t.getX()-t.getTaille()-1) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()-t.getTaille()-1) && y<(t.getY()+t.getTaille()+1)){
                return false;
            }
        }
        if (id.equals("flêche")){
            nvTour = new Tour("a", x, y, 100,10,32);
            if (horsChemin(x,y,nvTour.getTaille()) && map.getArgent() >= 100) {
                map.ajouterTour(nvTour);
                map.enleverArgent(100);
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }
}
