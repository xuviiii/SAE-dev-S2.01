package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Fabrique.FabriquePerso;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public abstract class Invocation {

    FabriquePerso fabrique;

    public Invocation(FabriquePerso fabrique) {
        this.fabrique = fabrique;
    }

    public abstract void action(Personnage invocateur);

    public Personnage faireApparaitre(Personnage invocateur) {
        Personnage invoque = fabrique.créerPerso();
        int indice = invocateur.getGestionnaireDeplacementPersonnage().getIndiceTerrain();
        invoque.getGestionnaireDeplacementPersonnage().setIndiceDepart(invocateur.getGestionnaireDeplacementPersonnage().getIndiceDepart());
        invoque.getGestionnaireDeplacementPersonnage().setIndiceTerrain(indice);
        invoque.setX(Terrain.getTerrain().positionXdepuisIndice(indice));
        invoque.setY(Terrain.getTerrain().positionYdepuisIndice(indice));
        Environnement.getEnvironnement().ajouterPersonnage(invoque);
        return invoque;
    }
}