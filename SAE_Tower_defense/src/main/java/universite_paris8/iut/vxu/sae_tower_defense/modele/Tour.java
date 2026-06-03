package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Tour extends Entite{
    private static int compteur = 0;
    private int portée;
    private int dégât;
    private int taille;
    private int prix;

    public Tour(double x, double y, int portée, int dégât, int taille, Environnement map, int prix, int vitesse) {
        super("t"+compteur,x,y,vitesse,map);
        compteur++;
        this.portée = portée;
        this.dégât = dégât;
        this.taille = taille;
        this.prix = prix;
    }

    public int getPortée() {
        return portée;
    }

    public int getDégât() {
        return dégât;
    }

    public int getTaille() {
        return taille;
    }

    public int getPrix() {
        return prix;
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public void ameliorer(){
        dégât+=1;
    }

    public Personnage ennemiACible(){

        ArrayList<Personnage> ennemisCiblables = new ArrayList<>();
        Personnage ennemiACible;
        Parcours parcours = getEnv().getParcours();

        for (int i = 0; i < super.getEnv().getPersonnages().size(); i++){
            if (super.getX() - portée < super.getEnv().getPersonnages().get(i).getX()
                    && super.getX() + portée > super.getEnv().getPersonnages().get(i).getX()
                    && super.getY() - portée < super.getEnv().getPersonnages().get(i).getY()
                    && super.getY() + portée > super.getEnv().getPersonnages().get(i).getY()){
                ennemisCiblables.add(super.getEnv().getPersonnages().get(i));
            }
        }
        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (Personnage personnage : ennemisCiblables)
                //TODO
                // associer à chaque perso sa bonne methode de déplacement
                if (parcours.cheminVersCible(personnage.getIndiceTerrain()).size()< parcours.cheminVersCible(ennemiACible.getIndiceTerrain()).size())
                    ennemiACible = personnage;
            /*for (int i=1;i<ennemisCiblables.size();i++){
                if ((ennemiACible.getX()+ennemiACible.getY())<(ennemisCiblables.get(i).getX()+ennemisCiblables.get(i).getY()))
                    ennemiACible = ennemisCiblables.get(i);
            }*/
            return ennemiACible;
        }
    }

    public void creerProjectile(Personnage ennemi){
        double dx,dy,h;
        h = Math.hypot(ennemi.getX()-super.getX(),ennemi.getY()-super.getY());
        dx = (ennemi.getX()+ (double) ennemi.getTaille() /2-super.getX())/h;
        dy = (ennemi.getY()+ (double) ennemi.getTaille() /2-super.getY())/h;
        super.getEnv().getProjectiles().add(new Projectile(dégât,getX(),getY(),dx,dy,portée,super.getEnv(),10));
    }

    public void attaquer(Personnage ennemi){
        if (ennemi!=null) creerProjectile(ennemi);
    }

    public void action(){
        attaquer(ennemiACible());
    }
}
