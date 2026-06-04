package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public abstract class Tour extends Entite{
    private static int compteur = 0;
    private int taille;
    private int prix;
    private int dégât;
    private int niveau;

    public Tour(double x, double y, int taille, Environnement map, int prix, int vitesse, int dégât) {
        super("t"+compteur,x,y,vitesse,map);
        compteur++;
        this.taille = taille;
        this.prix = prix;
        this.dégât = dégât;
        niveau = 0;
    }

    public int getTaille() {
        return taille;
    }

    public int getPrix() {
        return prix;
    }

    public int getDégât() {
        return dégât;
    }

    public abstract void ameliorer();
}
