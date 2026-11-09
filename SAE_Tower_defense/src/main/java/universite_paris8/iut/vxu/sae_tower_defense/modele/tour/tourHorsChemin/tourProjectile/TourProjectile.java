package universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile;

import universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur.Cibleur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.TourHorsChemin;

import java.util.List;

public abstract class TourProjectile extends TourHorsChemin {

    private double boostAttaque;
    private double boostVitesse;
    private Cibleur cibleur;
    private Boolean peutciblerCamoufle;

    public TourProjectile(double x, double y, int vitesse, int taille, int prix, int dégât, int portee, boolean peutciblerCamoufle) {
        super(x, y, vitesse, taille, prix, dégât, portee);
        boostAttaque = 1;
        boostVitesse = 1;
        this.cibleur = new CibleurPerso(getPortee(), this);
        this.peutciblerCamoufle = peutciblerCamoufle;
    }

    @Override
    public double vitesseAction() {
        return getVitesse() * boostVitesse;
    }

    public int vraiDegat(){
        return (int) (getDégât() * boostAttaque);
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

    private boolean plusProcheDeLaFin(Personnage personnage, Personnage ennemiACible){
        return parcoursSize(personnage) < parcoursSize(ennemiACible);
    }

    private int parcoursSize(Personnage personnage){
        return personnage
                .getGestionnaireDeplacementPersonnage().getDeplacement()
                .parcours(personnage.getGestionnaireDeplacementPersonnage().getIndiceTerrain(), Terrain.getTerrain().getIndiceCible())
                .size();
    }

    public Personnage ennemiACible(){

        List<Personnage> ennemisCiblables;
        Personnage ennemiACible;

        ennemisCiblables = cibleur.cibler();

        if (ennemisCiblables.isEmpty())
            return null;

        ennemiACible = ennemisCiblables.get(0);
        for (Personnage personnage : ennemisCiblables)
            if (!peutCibler(ennemiACible) || (plusProcheDeLaFin(personnage, ennemiACible) && peutCibler(personnage)))
                ennemiACible = personnage;
        if (ennemiACible.getGestionnaireEffetPerso().isCamoufles() && !peutciblerCamoufle)
            ennemiACible = null;
        return ennemiACible;
    }

    public boolean peutCibler(Personnage personnage){
        return !personnage.getGestionnaireEffetPerso().isCamoufles() || peutciblerCamoufle;
    }

    public abstract void creerProjectile(double x,double y);
}
