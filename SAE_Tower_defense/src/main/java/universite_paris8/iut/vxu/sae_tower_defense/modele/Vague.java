package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Vague {
    private ArrayList<Personnage> aRelacher;
    private IntegerProperty numVague;
    private Environnement env;

    public Vague(Environnement env) {
        aRelacher = new ArrayList<>();
        numVague = new SimpleIntegerProperty(0);
        this.env = env;
    }

    public int getNumVague() {
        return numVague.get();
    }

    public IntegerProperty numVagueProperty() {
        return numVague;
    }

    public void libererVague(){
        int alea;
        Personnage en;
        if (env.getPersonnages().isEmpty() && aRelacher.isEmpty()) {
            numVague.set(numVague.get()+1);
            for (int i = 0; i < (10 * numVague.get()); i++) {
                alea = (int)(Math.random() * 100)+1;
                if (alea<30){
                   en = new GobelinRouge(env);
                }
                else if(alea<60){
                    en = new GobelinNoir(env);
                }
                else if (alea < 70 && numVague.get() > 4) {
                    en = new Pretre(env);
                }
                else {
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


