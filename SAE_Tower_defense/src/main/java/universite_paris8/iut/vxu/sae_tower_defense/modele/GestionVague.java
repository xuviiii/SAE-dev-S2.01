package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.ChevalDeTroie;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.boss.Artha;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.*;

import java.util.ArrayList;

public class GestionVague {
    private static GestionVague uniqueInstance = null;
    private ArrayList<Personnage> caserne;
    private IntegerProperty numVague;
    

    private GestionVague() {
        caserne = new ArrayList<>();
        numVague = new SimpleIntegerProperty(0);
    }

    public static GestionVague getVague(){
        if (uniqueInstance == null){
            uniqueInstance = new GestionVague();
        }
        return uniqueInstance;
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
        return Environnement.getEnvironnement().getPersonnages().isEmpty() && caserne.isEmpty();
    }

    private boolean ennemisDansLaCaserne(){
        return !caserne.isEmpty();
    }

    private void creerVague(){
        Personnage en;
        numVague.set(numVague.get()+1);
        if (numVague.get()%20 == 0){
            caserne.add(new Artha(Environnement.getEnvironnement()));
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
            return new GobelinRouge();
        if (alea < 50)
            return new GobelinNoir();
        if (alea < 55 && numero > 4)
            return new Pretre();
        if (alea < 60 && numero > 8)
            return new ChevalDeTroie(Environnement.getEnvironnement(), 5);
        if (alea < 70 && numero > 11)
            return new ChevaucheurDeSanglier();
        return new GobelinVert();
    }

    public void envoyerVague(){
        Personnage en;
        en = caserne.remove(0);
        en.getGestionnaireDeplacementPersonnage().setIndiceTerrain(Terrain.getTerrain().genererIndiceDepartAlea());
        en.getGestionnaireDeplacementPersonnage().setIndiceDepart(en.getGestionnaireDeplacementPersonnage().getIndiceTerrain());
        en.setX(Terrain.getTerrain().positionXdepuisIndice(en.getGestionnaireDeplacementPersonnage().getIndiceTerrain()));
        en.setY(Terrain.getTerrain().positionYdepuisIndice(en.getGestionnaireDeplacementPersonnage().getIndiceTerrain()));
        Environnement.getEnvironnement().ajouterPersonnage(en);
    }

    public static void finDePartie(){
        uniqueInstance = null;
    }
}


