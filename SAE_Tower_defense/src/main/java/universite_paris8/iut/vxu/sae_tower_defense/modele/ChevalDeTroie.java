package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class ChevalDeTroie extends Personnage {

    private int nbPersonnages;
    private int distanceInit;

    public ChevalDeTroie(Environnement env, int nbPersonnages){
        super(30, 0.5, 10, 48, env, new Dijkstra(env), 5);
        this.nbPersonnages = nbPersonnages;
        distanceInit = getDeplacement().parcours(getIndiceTerrain()).size();
    }

    @Override
    public void action(){
        super.action();
        if(seDecharge()){
            attaque();
        }
    }

    private boolean seDecharge(){

        int distanceCible = getDeplacement().parcours(getIndiceTerrain()).size();
        int rand = (int) (Math.random() * 6) + 1;
        rand = rand - 3;
        return (distanceCible + rand) < (distanceInit / 2);
    }

    private void attaque(){

        int cible;

        for (int i = 0; i < nbPersonnages; i++) {

            cible = (i < nbPersonnages / 2)
                    ? getIndiceDepart()
                    : getEnv().getTerrain().getIndiceCible();

            // System.out.println("Indice cheval: " + getIndiceTerrain() + ", indice cibe: " + cible);

//            GobelinVert gobelin = new GobelinVert(getEnv().getTerrain().toX(getIndiceTerrain()),
//                    getEnv().getTerrain().toY(getIndiceTerrain()),
//                    getIndiceTerrain(),
//                    getEnv());

            GobelinVert gobelin = new GobelinVert(getEnv());
            gobelin.setIndiceTerrain(getIndiceTerrain());
            gobelin.setX(getEnv().getTerrain().toX(getIndiceTerrain()));
            gobelin.setY(getEnv().getTerrain().toY(getIndiceTerrain()));

            for (int j = 0; j < 8 * (i + 1); j++) {
                gobelin.seDeplace(cible);
            }
            getEnv().ajouterPersonnage(gobelin);
            // System.out.println("NEW GOBELIN ! x: " + gobelin.getX() + ", y: " + gobelin.getY());
        }

        meurt();
        // System.out.println("------");

    }


}
