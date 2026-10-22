package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Dijkstra;

public class Artha extends Boss {
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
            perso.getGestionnaireDeplacementPersonnage().setIndiceDepart(getGestionnaireDeplacementPersonnage().getIndiceDepart());
            perso.getGestionnaireDeplacementPersonnage().setIndiceTerrain(getGestionnaireDeplacementPersonnage().getIndiceTerrain());
            perso.setX(getEnv().getTerrain().toX(getGestionnaireDeplacementPersonnage().getIndiceTerrain()));
            perso.setY(getEnv().getTerrain().toY(getGestionnaireDeplacementPersonnage().getIndiceTerrain()));
            getEnv().ajouterPersonnage(perso);
        }
    }
}
