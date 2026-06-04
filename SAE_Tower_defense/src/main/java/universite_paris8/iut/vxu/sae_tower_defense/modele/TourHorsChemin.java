package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public abstract class TourHorsChemin extends Tour{

    private int portée;

    public TourHorsChemin(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, map, prix, vitesse, dégât);
        this.portée = portee;
    }

    public int getPortee() {
        return portée;
    }

}

