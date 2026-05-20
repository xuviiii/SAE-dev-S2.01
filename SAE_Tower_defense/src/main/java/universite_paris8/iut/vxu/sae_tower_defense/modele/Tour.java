package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Tour {
    private String id;
    private DoubleProperty x;
    private DoubleProperty y;
    private int portée;
    private int dégât;

    public Tour(String id, double x, double y, int portée, int dégât) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.id = id;
        this.portée = portée;
        this.dégât = dégât;
    }

    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public Personnage ennemiACible(ArrayList<Personnage> ennemis){
        ArrayList<Personnage> ennemisCiblables = new ArrayList<>();
        Personnage ennemiACible;
        for (int i=0;i<ennemis.size();i++){
            if (x.get()-portée*10<ennemis.get(i).getX()&&x.get()+portée*10>ennemis.get(i).getX()){
                ennemisCiblables.add(ennemis.get(i));
            }
        }
        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (int i=1;i<ennemisCiblables.size();i++){
                if ((ennemiACible.getX()+ennemiACible.getY())<(ennemisCiblables.get(i).getX()+ennemisCiblables.get(i).getY()))
                    ennemiACible = ennemisCiblables.get(i);
            }
            return ennemiACible;
        }
    }

    public void attaquer(Personnage ennemi){
        ennemi.setPv(ennemi.getPv()-dégât);
    }
}
