package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public abstract class Invocation {

    // Appelée à chaque action de l'invocateur
    public abstract void action(Personnage invocateur);

    // Code autrefois dupliqué : place l'invoqué sur la case de l'invocateur
    public void faireApparaitre(Personnage invocateur, Personnage invoque) {
        int indice = invocateur.getGestionnaireDeplacementPersonnage().getIndiceTerrain();
        invoque.getGestionnaireDeplacementPersonnage().setIndiceDepart(invocateur.getGestionnaireDeplacementPersonnage().getIndiceDepart());
        invoque.getGestionnaireDeplacementPersonnage().setIndiceTerrain(indice);
        invoque.setX(Terrain.getTerrain().positionXdepuisIndice(indice));
        invoque.setY(Terrain.getTerrain().positionYdepuisIndice(indice));
        Environnement.getEnvironnement().ajouterPersonnage(invoque);
    }
}