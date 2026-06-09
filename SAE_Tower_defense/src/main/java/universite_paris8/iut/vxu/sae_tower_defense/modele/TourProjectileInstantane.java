package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class TourProjectileInstantane extends TourProjectile {

    //TourHorsChemin(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee)

    //TourHorsChemin(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int portee)

    public TourProjectileInstantane(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int portee) {
        super(x, y, vitesse, map, taille, prix, dégât, portee);
    }

    @Override
    public void action() {
        Personnage cible;
        if (getCompteurAction()%10==0){
            cible = ennemiACible();
            if (cible != null)
                creerProjectile(cible.getX(), cible.getY());
        }
        compteurActionPlus();
    }
}
