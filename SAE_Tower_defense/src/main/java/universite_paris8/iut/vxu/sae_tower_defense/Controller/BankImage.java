package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.vxu.sae_tower_defense.modele.*;

import java.util.HashMap;
import java.util.Map;

public class BankImage {
    private static HashMap<Class<? extends Entite>,String> bankImg =  new HashMap<>(Map.ofEntries(
            Map.entry(GobelinVert.class, "/image/perso/gobelin_vert/gobelin.gif"),
            Map.entry(Pretre.class, "/image/perso/gobelin_vert/gobelin.gif"),

            Map.entry(TourDeFleche.class, "/image/tour/archer/archer.gif"),
            Map.entry(Catapulte.class, "/image/tour/archer/archer.gif"),
            Map.entry(TourDHuile.class, "/image/tour/archer/archer.gif"),
            Map.entry(TourDeMage.class, "/image/tour/archer/archer.gif"),
            Map.entry(TourDeSauron.class, "/image/tour/archer/archer.gif"),

            Map.entry(Fleche.class, "/image/projectile/flêche.png"),
            Map.entry(Rocher.class, "/image/projectile/flêche.png"),
            Map.entry(TonneauDHuile.class, "/image/projectile/flêche.png"),
            Map.entry(ZoneDeFlamme.class, "/image/projectile/flêche.png"),
            Map.entry(ProjectileInstantane.class, "/image/projectile/flêche.png")
            ));



    public static ImageView getImgView(Class<? extends Entite> c,int taille) {
        ImageView img = new ImageView(new Image(BankImage.class.getResourceAsStream(bankImg.get(c))));
        img.setFitWidth(taille);
        img.setPreserveRatio(true);
        return img;
    }
}
