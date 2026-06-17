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

        // crée une nouvelle vague
        if (env.getPersonnages().isEmpty() && aRelacher.isEmpty()) {
            numVague.set(numVague.get()+1);
            if (numVague.get()%20 == 0){
                aRelacher.add(new Artha(env));
            }
            for (int i = 0; i < (10 * numVague.get()); i++) {
                alea = (int)(Math.random() * 100);
                if (alea<25){
                   en = new GobelinRouge(env);
                }
                else if(alea<50){
                    en = new GobelinNoir(env);
                }
                else if (alea < 55 && numVague.get() > 4) {
                    en = new Pretre(env);
                } else if (alea < 60 && numVague.get()>8) {
                    en = new ChevalDeTroie(env, 5);
                } else if (alea < 70) {
                    en = new ChevaucheurDeSanglier(env);
                } else {
                    en = new GobelinVert(env);
                }
                aRelacher.add(en);
            }
        }
        // envoie petit a petit les ennemie sur le terrain
        else if (!aRelacher.isEmpty()){
                en = aRelacher.remove(0);
                en.setIndiceTerrain(env.getTerrain().genererIndiceDepartAlea());
                en.setIndiceDepart(en.getIndiceTerrain());
                en.setX(env.getTerrain().toX(en.getIndiceTerrain()));
                en.setY(env.getTerrain().toY(en.getIndiceTerrain()));
                env.ajouterPersonnage(en);
        }
    }
}


