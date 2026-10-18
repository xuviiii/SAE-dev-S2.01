package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public class InvocationMultiple extends Invocation {

    private int tempsInvocation;
    private int compteur;

    public InvocationMultiple(int tempsInvocation) {
        this.tempsInvocation = tempsInvocation;
        this.compteur = 0;
    }

    @Override
    public void action(Personnage invocateur) {
        compteur++;
        if (compteur >= tempsInvocation) {
            compteur = 0;
            faireApparaitre(invocateur, new GobelinNoir(invocateur.getEnv()));
        }
    }
}