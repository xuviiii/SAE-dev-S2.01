package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Achat {
    private Map map;

    public Achat(Map map) {
        this.map = map;
    }

    public Tour placerTour(String id, double x, double y){
        Tour nvTour;
        System.out.println(id);
        if (id.equals("flêche")){
            System.out.println(x+" "+y);
            nvTour = new Tour("a", x, y, 100,10);
            System.out.println("i");
        }
        else {
            nvTour = null;
        }
        return nvTour;
    }
}
