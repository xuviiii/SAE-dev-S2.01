package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;
import java.util.List;

public abstract class TourProjectile extends TourHorsChemin{

    private int boost;
    private Cibleur cibleur;
    private Boolean peutciblerCamoufle;

    public TourProjectile(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int portee, boolean peutciblerCamoufle) {
        super(x, y, vitesse, map, taille, prix, dégât, portee);
        boost = 0;
        this.cibleur = new Cibleur(getPortee(), this);
        this.peutciblerCamoufle = peutciblerCamoufle;
    }

    public void setPeutciblerCamoufle(Boolean peutciblerCamoufle) {
        this.peutciblerCamoufle = peutciblerCamoufle;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public Personnage ennemiACible(){

        List<Personnage> ennemisCiblables;
        Personnage ennemiACible;

        ennemisCiblables = cibleur.cibler();

        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (Personnage personnage : ennemisCiblables)
                if (personnage
                        .getDeplacement()
                        .cheminVersCible(personnage.getIndiceTerrain())
                        .size()
                        < ennemiACible
                        .getDeplacement()
                        .cheminVersCible(personnage.getIndiceTerrain())
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
