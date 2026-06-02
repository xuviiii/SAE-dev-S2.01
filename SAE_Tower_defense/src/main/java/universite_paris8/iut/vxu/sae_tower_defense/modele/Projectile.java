package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {
    private static int compteur;
    private String id;
    private int degat;
    private DoubleProperty x;
    private DoubleProperty y;
    private double dx;
    private double dy;
    private int portee;
    private double xInitial;
    private double yInitial;
    private int vitesse;
    private Environnement map;

    public Projectile(int degat, double x, double y, double dx, double dy, int portee, int vitesse, Environnement map) {
        compteur++;
        id= "Pr"+compteur;
        this.degat = degat;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.dx = dx;
        this.dy = dy;
        this.portee=portee;
        xInitial=x;
        yInitial=y;
        this.vitesse = vitesse;
        this.map = map;
    }

    public String getId() {
        return id;
    }

    public int getDegat() {
        return degat;
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

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void avancer(){
        x.setValue(x.getValue()+dx*vitesse);
        y.setValue(y.getValue()+dy*vitesse);
    }

    public boolean horsPortee(){
        return (int) (Math.abs(x.getValue()-(xInitial)))>portee || (int) (Math.abs(y.getValue()-(yInitial)))>portee;
    }

    public void projectileTouche(){
        int j=0;
        boolean arret=false;
        while (j<map.getPersonnages().size()&&!arret){
            if (map.getPersonnages().get(j).estTouché(x.getValue(), y.getValue())){
                map.getPersonnages().get(j).subirDegat(degat);
                map.getProjectiles().remove(this);
                arret=true;
            }
            else if (horsPortee()){
                map.getProjectiles().remove(this);
                arret=true;
            }
            j++;
        }
    }

    public void action(){
        avancer();
        projectileTouche();
    }
}
