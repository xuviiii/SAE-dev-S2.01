package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class Tour extends Entite{
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

    public void resetCompteurAction(){
        compteurAction = 0;
    }

    public abstract void ameliorer();
}
