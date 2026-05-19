module universite_paris8.iut.vxu.sae_tower_defense {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.vxu.sae_tower_defense to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense;
    exports universite_paris8.iut.vxu.sae_tower_defense.modele;
    opens universite_paris8.iut.vxu.sae_tower_defense.modele to javafx.fxml;
    exports universite_paris8.iut.vxu.sae_tower_defense.Controller;
    opens universite_paris8.iut.vxu.sae_tower_defense.Controller to javafx.fxml;
}