package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Cibleur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.TourHorsChemin;

import java.util.List;

public abstract class TourProjectile extends TourHorsChemin {

    private double boostAttaque;
    private double boostVitesse;
    private Cibleur cibleur;
    private Boolean peutciblerCamoufle;

    public TourProjectile(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int portee, boolean peutciblerCamoufle) {
        super(x, y, vitesse, map, taille, prix, dégât, portee);
        boostAttaque = 1;
        boostVitesse = 1;
        this.cibleur = new Cibleur(getPortee(), this);
        this.peutciblerCamoufle = peutciblerCamoufle;
    }

    public void setPeutciblerCamoufle(Boolean peutciblerCamoufle) {
        this.peutciblerCamoufle = peutciblerCamoufle;
    }

    public double getBoostAttaque() {
        return boostAttaque;
    }

    public double getBoostVitesse() {
        return boostVitesse;
    }

    public void setBoostAttaque(double boostAttaque) {
        this.boostAttaque = boostAttaque;
    }

    public void setBoostVitesse(double boostVitesse) {
        this.boostVitesse = boostVitesse;
    }

    public Personnage ennemiACible(){

        List<Personnage> ennemisCiblables;
        Personnage ennemiACible;

        ennemisCiblables = cibleur.ciblerEnnemie();

        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (Personnage personnage : ennemisCiblables)
                if (personnage
                        .getDeplacement()
                        .parcours(personnage.getIndiceTerrain(), getEnv().getTerrain().getIndiceCible())
                        .size()
                        < ennemiACible
                        .getDeplacement()
                        .parcours(personnage.getIndiceTerrain(), getEnv().getTerrain().getIndiceCible())
                        .size()) {
                    if (!personnage.isCamoufles() || peutciblerCamoufle) {
                        ennemiACible = personnage;
                    }
                }
            /*for (int i=1;i<ennemisCiblables.size();i++){
                if ((ennemiACible.getX()+ennemiACible.getY())<(ennemisCiblables.get(i).getX()+ennemisCiblables.get(i).getY()))
                    ennemiACible = ennemisCiblables.get(i);
            }*/
            if(!ennemiACible.isCamoufles() || peutciblerCamoufle) {
                return ennemiACible;
            }
            else {
                return null;
            }
        }
    }


    public abstract void creerProjectile(double x,double y);
}
