package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.boss.Artha;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.*;

import java.util.ArrayList;

public class Vague {
    private ArrayList<Personnage> caserne;
    private IntegerProperty numVague;
    private Environnement env;

    public Vague(Environnement env) {
        caserne = new ArrayList<>();
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
        // crée une nouvelle vague
        if (tousEnnemisMorts()) {
            creerVague();
        }
        // envoie petit a petit les ennemie sur le terrain
        else if (ennemisDansLaCaserne()){
            envoyerVague();
        }
    }

    private boolean tousEnnemisMorts(){
        return env.getPersonnages().isEmpty() && caserne.isEmpty();
    }

    private boolean ennemisDansLaCaserne(){
        return !caserne.isEmpty();
    }

    private void creerVague(){
        Personnage en;
        numVague.set(numVague.get()+1);
        if (numVague.get()%20 == 0){
            caserne.add(new Artha(env));
        }
        for (int i = 0; i < (10 * numVague.get()); i++) {
            en = creerEnnemi();
            caserne.add(en);
        }
    }

    private Personnage creerEnnemi() {
        int alea = (int)(Math.random() * 100);
        int numero = numVague.get();
        if (alea < 25)
            return new GobelinRouge(env);
        if (alea < 50)
            return new GobelinNoir(env);
        if (alea < 55 && numero > 4)
            return new Pretre(env);
        if (alea < 60 && numero > 8)
            return new ChevalDeTroie(env, 5);
        if (alea < 70 && numero > 11)
            return new ChevaucheurDeSanglier(env);
        return new GobelinVert(env);
    }

    public void envoyerVague(){
        Personnage en;
        en = caserne.remove(0);
        en.setIndiceTerrain(env.getTerrain().genererIndiceDepartAlea());
        en.setIndiceDepart(en.getIndiceTerrain());
        en.setX(env.getTerrain().positionXdepuisIndice(en.getIndiceTerrain()));
        en.setY(env.getTerrain().positionYdepuisIndice(en.getIndiceTerrain()));
        env.ajouterPersonnage(en);
    }
}


