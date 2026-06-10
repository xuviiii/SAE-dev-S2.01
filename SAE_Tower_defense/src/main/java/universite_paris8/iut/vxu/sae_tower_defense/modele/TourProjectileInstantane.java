package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class TourProjectileInstantane extends TourProjectile {

    public TourProjectileInstantane(double x, double y, int vitesse, Environnement map, int taille, int prix, int dégât, int portee) {
        super(x, y, vitesse, map, taille, prix, dégât, portee);
    }

    @Override
    public void agir() {
        Personnage cible;
        cible = ennemiACible();
        if (cible != null)
            creerProjectile(cible.getX(), cible.getY());
    }
}
