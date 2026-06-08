package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Vague {
    private ArrayList<Personnage> aRelacher;
    private int numVague;
    private Environnement env;

    public Vague(Environnement env) {
        aRelacher = new ArrayList<>();
        numVague = 0;
        this.env = env;
    }

    public void libererVague(){
        int alea;
        Personnage en;
        if (env.getPersonnages().isEmpty() && aRelacher.isEmpty()) {
            numVague++;
            for (int i = 0; i < (10 * numVague); i++) {
                alea = (int)(Math.random() * 100);
                if (alea < 30 && numVague > 4) {
                    en = new Pretre(env);
                } else {
                    en = new GobelinVert(env);
                }
                aRelacher.add(en);
            }
        }
        else if (!aRelacher.isEmpty()){
                en = aRelacher.remove(0);
                en.setIndiceTerrain(env.getTerrain().genererIndiceDepartAlea());
                en.setX(env.getTerrain().toX(en.getIndiceTerrain()));
                en.setY(env.getTerrain().toY(en.getIndiceTerrain()));
                env.ajouterPersonnage(en);
        }
    }
}


