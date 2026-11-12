package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Fabrique.FabriquePerso;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.GobelinNoir;

public class InvocationMultiple extends Invocation {

    private int tempsInvocation;
    private int compteur;

    public InvocationMultiple(FabriquePerso fabrique, int tempsInvocation) {
        super(fabrique);
        this.tempsInvocation = tempsInvocation;
        this.compteur = 0;
    }

    @Override
    public void action(Personnage invocateur) {
        compteur++;
        if (compteur >= tempsInvocation) {
            compteur = 0;
            faireApparaitre(invocateur);
        }
    }
}