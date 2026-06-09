package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.List;

public class Tour extends Entite {
    private static int compteur = 0;
    private int portee;
    private int dégât;
    private int taille;
    private int prix;
    private Cibleur cibleur;

    public Tour(double x, double y, int portee, int dégât, int taille, Environnement map, int prix, int vitesse) {
        super("t"+compteur,x,y,vitesse,map);
        compteur++;
        this.portee = portee;
        this.dégât = dégât;
        this.taille = taille;
        this.prix = prix;
        this.cibleur = new Cibleur(this.portee, this);
    }

    public int getPortee() {
        return portee;
    }

    public int getDégât() {
        return dégât;
    }

    public int getTaille() {
        return taille;
    }

    public int getPrix() {
        return prix;
    }

    public void ameliorer(){
        dégât+=1;
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
                        .size())
                    ennemiACible = personnage;
            /*for (int i=1;i<ennemisCiblables.size();i++){
                if ((ennemiACible.getX()+ennemiACible.getY())<(ennemisCiblables.get(i).getX()+ennemisCiblables.get(i).getY()))
                    ennemiACible = ennemisCiblables.get(i);
            }*/
            return ennemiACible;
        }
    }

    public void creerProjectile(Personnage ennemi){
        double dx,dy,h;
        h = Math.hypot(ennemi.getX()-super.getX(),ennemi.getY()-super.getY());
        dx = (ennemi.getX()+ (double) ennemi.getTaille() /2-super.getX())/h;
        dy = (ennemi.getY()+ (double) ennemi.getTaille() /2-super.getY())/h;
        super.getEnv().getProjectiles().add(new Projectile(dégât,getX(),getY(),dx,dy,portee,super.getEnv(),10));
    }

    public void attaquer(Personnage ennemi){
        if (ennemi!=null) creerProjectile(ennemi);
    }

    @Override
    public void action(int temps){
        if(temps % 50 == 0){
            attaquer(ennemiACible());
        }
    }
}
