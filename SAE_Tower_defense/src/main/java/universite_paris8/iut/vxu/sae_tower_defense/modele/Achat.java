package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Achat {
    private Map map;

    public Achat(Map map) {
        this.map = map;
    }

    public Boolean placerTour(String id, double x, double y){
        Tour nvTour;
        for (Tour t:map.getTours()){
            if(x> (t.getX()-t.getTaille()-1) &&  x<(t.getX()+t.getTaille()+1) && y> (t.getY()-t.getTaille()-1) && y<(t.getY()+t.getTaille()+1)){
                return false;
            }
        }
        if (id.equals("flêche")){
            nvTour = new Tour("a", x, y, 100,10,10);
            map.ajouterTour(nvTour);
        }
        else {
            return false;
        }
        return true;
    }
}
