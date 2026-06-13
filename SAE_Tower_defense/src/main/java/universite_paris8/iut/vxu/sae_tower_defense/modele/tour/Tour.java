package universite_paris8.iut.vxu.sae_tower_defense.modele.tour;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;

public abstract class Tour extends Entite {
    private static int compteur = 0;
    private int prix;
    private int dégât;
    private int niveau;
    private int compteurAction;

    public Tour(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât) {
        super("t"+compteur,x,y,vitesse,map,taille);
        compteur++;
        this.prix = prix;
        this.dégât = dégât;
        niveau = 0;
        compteurAction = 0;
    }

    public void augmenterDegat(int ajout){
        dégât += ajout;
    }

    public void gainNiveau(){
        niveau++;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getPrix() {
        return prix;
    }

    public int getDégât() {
        return dégât;
    }

    public int getNiveau() {
        return niveau;
    }

    @Override
    public void action() {
        if (compteurAction>1000){
            agir();
            compteurAction = 0;
        }
        compteurAction += (int) getVitesse();
    }

    public abstract void agir();

    public abstract void ameliorer();

    @Override
    public String toString() {
        return "niveau:" + niveau ;
    }
}
