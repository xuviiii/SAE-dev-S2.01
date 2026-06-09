package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class Tour extends Entite{
    private static int compteur = 0;
    private int prix;
    private int dégât;
    private int niveau;
    private int compteurAction;

    public Tour(double x, double y, Environnement map, int prix, int vitesse, int dégât) {
        super("t"+compteur,x,y,vitesse,map,32);
        compteur++;
        this.prix = prix;
        this.dégât = dégât;
        niveau = 0;
        compteurAction = 0;
    }

    public int getPrix() {
        return prix;
    }

    public int getDégât() {
        return dégât;
    }

    public int getCompteurAction() {
        return compteurAction;
    }

    public void compteurActionPlus(){
        compteurAction++;
    }

    public abstract void ameliorer();
}
