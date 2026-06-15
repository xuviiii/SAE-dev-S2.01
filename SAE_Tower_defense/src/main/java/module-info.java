module universite_paris8.iut.vxu.sae_tower_defense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens universite_paris8.iut.vxu.sae_tower_defense to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.Controller;
    opens universite_paris8.iut.vxu.sae_tower_defense.Controller to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.tour;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.tour to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.projectile;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.projectile to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileLance to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.projectile.projectileInstantane to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileInstantane to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin to javafx.fxml;
}