package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.invocation;

import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

public abstract class Invocation {

    // Appelée à chaque action de l'invocateur
    public abstract void action(Personnage invocateur);

    // Code autrefois dupliqué : place l'invoqué sur la case de l'invocateur
    public void faireApparaitre(Personnage invocateur, Personnage invoque) {
        int indice = invocateur.getIndiceTerrain();
        invoque.setIndiceDepart(invocateur.getIndiceDepart());
        invoque.setIndiceTerrain(indice);
        invoque.setX(invocateur.getEnv().getTerrain().toX(indice));
        invoque.setY(invocateur.getEnv().getTerrain().toY(indice));
        invocateur.getEnv().ajouterPersonnage(invoque);
    }
}