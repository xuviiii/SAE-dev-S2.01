package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class ChevalDeTroie extends Personnage {

    private int nbPersonnages;
    private int distanceInit;

    public ChevalDeTroie(double x, double y, int indiceTerrain, Environnement env, int nbPersonnages){
        super(20, x, y, 0.5, 10, 32, indiceTerrain, env, new Dijkstra(env));
        this.nbPersonnages = nbPersonnages;
        distanceInit = getDeplacement().cheminVersCible(indiceTerrain).size();
    }

    @Override
    public void action(int temps){
        super.action(temps);
        if(seDecharge()){
            attaque();
        }
    }

    private boolean seDecharge(){

        int distanceCible = getDeplacement().cheminVersCible(getIndiceTerrain()).size();
        int rand = (int) (Math.random() * 6) + 1;
        rand = rand - 3;
        return (distanceCible + rand) < (distanceInit / 2);
    }

    private void attaque(){

        for (int i = 0; i < nbPersonnages / 2; i++) {

            int cible = getHistoriqueDeplacement().get(getHistoriqueDeplacement().size() - 2);
            // System.out.println("Indice cheval: " + getIndiceTerrain() + ", indice prev: " + cible);

            GobelinVert gobelin = new GobelinVert(getEnv().getTerrain().toX(getIndiceTerrain()),
                    getEnv().getTerrain().toY(getIndiceTerrain()),
                    getIndiceTerrain(),
                    getEnv());

            for (int j = 0; j < 8 * (i + 1); j++) {
                gobelin.seDeplace(cible);
            }
            getEnv().ajouterPersonnage(gobelin);
            // System.out.println("NEW GOBELIN ! x: " + gobelin.getX() + ", y: " + gobelin.getY());
        }

        for (int i = 0; i < (nbPersonnages - (nbPersonnages / 2)); i++) {

            GobelinVert gobelin = new GobelinVert(getEnv().getTerrain().toX(getIndiceTerrain()),
                    getEnv().getTerrain().toY(getIndiceTerrain()),
                    getIndiceTerrain(),
                    getEnv());

            for (int j = 0; j < 8 * (i + 1); j++) {
                gobelin.seDeplace(getDeplacement().tileSuivante(getIndiceTerrain(), getEnv().getTerrain().getIndiceCible()));
            }
            getEnv().ajouterPersonnage(gobelin);
            // System.out.println("NEW GOBELIN ! x: " + gobelin.getX() + ", y: " + gobelin.getY());

        }

        meurt();
        // System.out.println("------");

    }


}
