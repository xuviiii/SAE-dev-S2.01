package universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.Tour;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.TourProjectile;

import java.util.ArrayList;

public abstract class Cibleur {

    private int portee;
    private Entite entite;

    public Cibleur(int portee, Entite entite) {
        this.portee = portee;
        this.entite = entite;
    }


    public ArrayList cibler(){

        ArrayList ennemisCiblables = new ArrayList<>();

        for (Entite personnage : getListAInspecter()) {// modifier le get --------------------------------
            if (estAPorter(personnage))
                ajouterAuCibleur(ennemisCiblables,personnage);
        }
        return ennemisCiblables;
    }

    public abstract void ajouterAuCibleur(ArrayList ennemisCiblables, Entite entite);

    public abstract ArrayList<Entite> getListAInspecter();


    public Boolean estAPorter(Entite entiteCible){
        return Math.hypot(entite.getX()-entiteCible.getX(),entite.getY()-entiteCible.getY())<portee;
    }
}
