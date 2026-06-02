package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Tour {
    private static int compteur;
    private String id;
    private DoubleProperty x;
    private DoubleProperty y;
    private int portée;
    private int dégât;
    private Environnement map;
    private int taille;
    private int prix;

    public Tour(double x, double y, int portée, int dégât, int taille, Environnement map, int prix) {
        compteur++;
        id= "T"+compteur;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.portée = portée;
        this.dégât = dégât;
        this.taille = taille;
        this.map = map;
        this.prix = prix;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty getYProperty() {
        return y;
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

    public void ameliorer(){
        dégât+=1;
    }

    public Personnage ennemiACible(){
        ArrayList<Personnage> ennemisCiblables = new ArrayList<>();
        Personnage ennemiACible;
        for (int i=0;i<map.getPersonnages().size();i++){
            if (x.getValue()-portée<map.getPersonnages().get(i).getX()&&x.getValue()+portée>map.getPersonnages().get(i).getX()&&y.getValue()-portée<map.getPersonnages().get(i).getY()&&y.getValue()+portée>map.getPersonnages().get(i).getY()){
                ennemisCiblables.add(map.getPersonnages().get(i));
            }
        }
        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (Personnage personnage : ennemisCiblables)
                if (map.cheminVersCible(personnage.getIndiceTerrain()).size()<map.cheminVersCible(ennemiACible.getIndiceTerrain()).size())
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
        h = Math.hypot(ennemi.getX()-x.getValue(),ennemi.getY()-y.getValue());
        dx = (ennemi.getX()+ (double) ennemi.getTaille() /2-x.getValue())/h;
        dy = (ennemi.getY()+ (double) ennemi.getTaille() /2-y.getValue())/h;
        map.getProjectiles().add(new Projectile(dégât,getX(),getY(),dx,dy,portée,10,map));
    }

    public void attaquer(Personnage ennemi){
        if (ennemi!=null) creerProjectile(ennemi);
    }

    public void action(){
        attaquer(ennemiACible());
    }
}
