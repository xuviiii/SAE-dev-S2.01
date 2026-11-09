package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.boss;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Deplacement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public abstract class Boss extends Personnage {
    public Boss(int pv, double vitesse, int degat, int taille, Deplacement deplacement, int gains) {
        super(pv, vitesse, degat, taille, deplacement, gains);
        getGestionnaireEffetPerso().setCamoufles(false);
        getGestionnaireEffetPerso().enleverCuirasses();
    }
}
