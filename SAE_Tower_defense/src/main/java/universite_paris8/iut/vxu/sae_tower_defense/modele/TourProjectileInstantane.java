package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class TourProjectileInstantane extends TourProjectile {

    private int tailleProjectileMax;

    public TourProjectileInstantane(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, map, prix, vitesse, dégât, portee);
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
