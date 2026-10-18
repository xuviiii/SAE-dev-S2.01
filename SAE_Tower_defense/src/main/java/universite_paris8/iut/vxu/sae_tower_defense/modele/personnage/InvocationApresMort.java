package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

public class InvocationApresMort extends Invocation {

    private int nbPersonnages;
    private int distanceInit;

    public InvocationApresMort(int nbPersonnages, int distanceInit) {
        this.nbPersonnages = nbPersonnages;
        this.distanceInit = distanceInit;
    }

    @Override
    public void action(Personnage invocateur) {
        int distanceCible = invocateur.getDeplacement().parcours(invocateur.getIndiceTerrain()).size();
        int rand = (int) (Math.random() * 6) - 2;   // entre -2 et 3, comme avant
        if (distanceCible + rand < distanceInit / 2) {
            invoquer(invocateur);
            invocateur.meurt();
        }
    }

    private void invoquer(Personnage invocateur) {
        int cible;
        for (int i = 0; i < nbPersonnages; i++) {
            cible = (i < nbPersonnages / 2)
                    ? invocateur.getIndiceDepart()
                    : invocateur.getEnv().getTerrain().getIndiceCible();

            GobelinVert gobelin = new GobelinVert(invocateur.getEnv());
            faireApparaitre(invocateur, gobelin);
            for (int j = 0; j < 8 * (i + 1); j++) {
                gobelin.seDeplace(cible);
            }
        }
    }
}