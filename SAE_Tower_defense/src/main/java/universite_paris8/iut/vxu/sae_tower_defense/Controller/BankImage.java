package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.vxu.sae_tower_defense.modele.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.AttaqueSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.Explosion;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane.ZoneDeFlamme;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance.Fleche;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance.Rocher;
import universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance.TonneauDHuile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeMage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane.TourDeSauron;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Marais;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.Camp;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.Catapulte;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDHuile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDeFleche;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin.Mur;

import java.util.HashMap;
import java.util.Map;

public class BankImage {
    //stock tout les sprite des entiter
    private static HashMap<Class<? extends Entite>,String> bankImg =  new HashMap<>(Map.ofEntries(
            Map.entry(GobelinVert.class, "/image/perso/gobelin_vert/gobelin.gif"),
            Map.entry(Pretre.class, "/image/perso/pretre/pretre.gif"),
            Map.entry(GobelinRouge.class, "/image/perso/gobelin_vert/gobelin.gif"),
            Map.entry(GobelinNoir.class, "/image/perso/gobelin_vert/gobelin.gif"),
            Map.entry(ChevalDeTroie.class, "/image/perso/cheval_de_troie/chevalDeTroie.gif"),
            Map.entry(Artha.class, "/image/perso/artha/Artha.gif"),
            Map.entry(ChevaucheurDeSanglier.class, "/image/perso/artha/Artha.gif"),

            Map.entry(TourDeFleche.class, "/image/tour/archer/archer.gif"),
            Map.entry(Catapulte.class, "/image/tour/catapulte/catapulte.gif"),
            Map.entry(TourDHuile.class, "/image/tour/tour_de_feu/tour_de_feu.gif"),
            Map.entry(TourDeMage.class, "/image/tour/mage/mage.png"),
            Map.entry(TourDeSauron.class, "/image/tour/tour_de_sauron/tour_de_sauron.gif"),
            Map.entry(Camp.class, "/image/tour/camp/camp.png"),
            Map.entry(Marais.class, "/image/tour/marais/marais.png"),
            Map.entry(Mur.class, "/image/tour/mur/bob.png"),

            Map.entry(Fleche.class, "/image/projectile/flêche.png"),
            Map.entry(Rocher.class, "/image/projectile/grosCaillou.png"),
            Map.entry(TonneauDHuile.class, "/image/projectile/huile.png"),
            Map.entry(ZoneDeFlamme.class, "/image/projectile/venezAcheter.png"),
            Map.entry(Explosion.class, "/image/projectile/explosion.png"),
            Map.entry(AttaqueSauron.class, "/image/projectile/pasContent.png")
            ));



    public static ImageView getImgView(Class<? extends Entite> c,int taille) {
        ImageView img = new ImageView(new Image(BankImage.class.getResourceAsStream(bankImg.get(c))));
        img.setFitHeight(taille);
        img.setPreserveRatio(true);
        return img;
    }
}
