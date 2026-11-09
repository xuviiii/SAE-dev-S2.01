package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation;

import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Fabrique.FabriquePerso;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.GobelinVert;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public class InvocationApresMort extends Invocation {

    private int nbPersonnages;
    private int distanceInit;

    public InvocationApresMort(FabriquePerso fabrique, int nbPersonnages, int distanceInit) {
        super(fabrique);
        this.nbPersonnages = nbPersonnages;
        this.distanceInit = distanceInit;
    }

    @Override
    public void action(Personnage invocateur) {
        int distanceCible = invocateur.getGestionnaireDeplacementPersonnage().getDeplacement().parcours(invocateur.getGestionnaireDeplacementPersonnage().getIndiceTerrain()).size();
        int rand = (int) (Math.random() * 6) - 2;   // entre -2 et 3, comme avant
        if (distanceCible + rand < distanceInit / 2) {
            invoquer(invocateur);
            invocateur.meurt();
        }
    }

    private void invoquer(Personnage invocateur) {
        int cible;
        Personnage personnage;
        for (int i = 0; i < nbPersonnages; i++) {
            if (i < nbPersonnages / 2)
                cible = invocateur.getGestionnaireDeplacementPersonnage().getIndiceDepart();
            else
                cible = invocateur.getEnv().getTerrain().getIndiceCible();
            personnage = faireApparaitre(invocateur);
            for (int j = 0; j < 8 * (i + 1); j++) {
                personnage.getGestionnaireDeplacementPersonnage().seDeplace(cible);
            }
        }
    }
}