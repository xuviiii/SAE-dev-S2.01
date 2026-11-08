package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourSurChemin;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Deplacement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.time.temporal.Temporal;
import java.util.List;

public class Mur extends TourSurChemin {
  
    private int pv;

    public Mur(double x, double y) {
        super(x, y, 1000, 59, 400, 0, -1);
        pv = 100;
    }

    @Override
    public void agir() {
        int indice;
        List<Integer> adjacents;

        indice = (int)(((getX()-(getX()% Terrain.getTerrain().getTailleTile()))/Terrain.getTerrain().getTailleTile())+((getY()-(getY()%Terrain.getTerrain().getTailleTile()))/Terrain.getTerrain().getTailleTile()*Terrain.getTerrain().getLongueurMap()));
        adjacents = Deplacement.adjacents(indice);

        for (Personnage personnage : Environnement.getEnvironnement().getPersonnages()){
            if (adjacents.contains(personnage.getGestionnaireDeplacementPersonnage().getIndiceTerrain()))
                pv -= personnage.attaqueMur();
        }
        if (pv<=0)
            Environnement.getEnvironnement().getTours().remove(this);
    }

    @Override
    public int prixAmelioration() {
        if (getNiveau() == 0)
            return 200;
        return 0;
    }

    @Override
    public boolean estAuNiveauMax() {
        return getNiveau() > 0;
    }

    @Override
    public void ameliorer() {
        if (getNiveau() == 0)
            pv = 250;
        gainNiveau();
    }

    @Override
    public String toString() {
        return "Mur [ " + super.toString() + " ]" ;
    }
}
