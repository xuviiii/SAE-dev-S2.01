package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public abstract class TourProjectile extends TourHorsChemin{

    private int boost;

    public TourProjectile(double x, double y, Environnement map, int prix, int vitesse, int dégât, int portee) {
        super(x, y, map, prix, vitesse, dégât, portee);
        boost = 0;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public Personnage ennemiACible(){

        ArrayList<Personnage> ennemisCiblables = new ArrayList<>();
        Personnage ennemiACible;
        Parcours parcours = getEnv().getParcours();

        for (int i = 0; i < super.getEnv().getPersonnages().size(); i++){
            if (super.getX() - super.getPortee() < super.getEnv().getPersonnages().get(i).getX()
                    && super.getX() + super.getPortee() > super.getEnv().getPersonnages().get(i).getX()
                    && super.getY() - super.getPortee() < super.getEnv().getPersonnages().get(i).getY()
                    && super.getY() + super.getPortee() > super.getEnv().getPersonnages().get(i).getY()){
                ennemisCiblables.add(super.getEnv().getPersonnages().get(i));
            }
        }
        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (Personnage personnage : ennemisCiblables)
                //TODO
                // associer à chaque perso sa bonne methode de déplacement
                if (parcours.cheminVersCible(personnage.getIndiceTerrain()).size()< parcours.cheminVersCible(ennemiACible.getIndiceTerrain()).size())
                    ennemiACible = personnage;
            return ennemiACible;
        }

    }

    public void creerProjectile(double x,double y) {
        double dx,dy,h;
        h = Math.hypot(x-super.getX(),y-super.getY());
        dx = (x-super.getX())/h;
        dy = (y-super.getY())/h;
        super.getEnv().getProjectiles().add(projectileALancer(x,y,dx,dy));
    }

    public abstract Projectile projectileALancer(double x,double y, double dx,double dy);
}
