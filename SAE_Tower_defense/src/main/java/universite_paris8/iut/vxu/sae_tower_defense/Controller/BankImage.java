package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.vxu.sae_tower_defense.modele.*;

import java.util.HashMap;
import java.util.Map;

public class BankImage {
    private static HashMap<Class<? extends Entite>,String> bankImg =  new HashMap<>(Map.of(
            Tour.class,"/image/tour/archer/archer.gif",
            GobelinVert.class, "/image/perso/gobelin_vert/gobelin.gif",
            GobelinRouge.class, "/image/perso/gobelin_vert/gobelin.gif",
            GobelinNoir.class, "/image/perso/gobelin_vert/gobelin.gif",
            Pretre.class, "/image/perso/gobelin_vert/gobelin.gif",
            Projectile.class, "/image/projectile/flêche.png"));



    public static ImageView getImgView(Class<? extends Entite> c,int taille) {
        ImageView img = new ImageView(new Image(BankImage.class.getResourceAsStream(bankImg.get(c))));
        img.setFitWidth(taille);
        img.setPreserveRatio(true);
        return img;
    }
}
