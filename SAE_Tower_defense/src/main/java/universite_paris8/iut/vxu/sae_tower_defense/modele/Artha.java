package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class Artha extends Boss{
    private int tempsInvocation;

    public Artha(Environnement env) {
        super(500, 0.5, 20, 64, env, new Dijkstra(env), 1000);
        tempsInvocation = 0;
    }

    @Override
    public void action() {
        super.action();
        tempsInvocation++;
        if(tempsInvocation == 200){
            System.out.println("Invoquer");
            tempsInvocation =0;
            Personnage perso = new GobelinNoir(getEnv());
            perso.setIndiceDepart(getIndiceDepart());
            perso.setIndiceTerrain(getIndiceTerrain());
            perso.setX(getEnv().getTerrain().toX(getIndiceTerrain()));
            perso.setY(getEnv().getTerrain().toY(getIndiceTerrain()));
            getEnv().ajouterPersonnage(perso);
        }
    }
}
