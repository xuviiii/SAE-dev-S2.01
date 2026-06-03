package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Projectile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Tour;

import java.util.HashMap;

public class bankImage {
    private static HashMap<Class<Tour>,String> imgTour;
    private static HashMap<Class<Personnage>,String> imgPerso;
    private static HashMap<Class<Projectile>,String> imgProj;

    public bankImage() {
        imgTour = new HashMap<>();
        imgTour.put(Tour.class,"/image/tour/archer/archer.gif");
    }

    public static String getTourImg(Class c) {
        return imgTour.get(c);
    }
}
