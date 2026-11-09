package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.BFS;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class ChevaucheurDeSanglier extends Personnage {

    public ChevaucheurDeSanglier() {
        super(50, 1.5, 20, 32, new BFS(), 10);
    }

    @Override
    public void action() {
        getGestionnaireDeplacementPersonnage().setMalusVitesse(1);
        super.action();
    }
}
