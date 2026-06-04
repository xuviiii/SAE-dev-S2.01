package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Marais extends Tour {

    private int ralentissement;

    public Marais(double x, double y, int taille, Environnement map, int prix, int vitesse, int dégât, int ralentissement) {
        super(x, y, map, prix, vitesse, dégât);
        this.ralentissement = ralentissement;
    }

    public ArrayList<Personnage> ennemiDessus(){
        ArrayList<Personnage> ennemis = new ArrayList<>();

        System.out.println(super.getEnv().getPersonnages());

        for (int i = 0; i < super.getEnv().getPersonnages().size(); i++){
            if (super.getX() < super.getEnv().getPersonnages().get(i).getX()
                    && super.getX() + super.getTaille() > super.getEnv().getPersonnages().get(i).getX()
                    && super.getY() < super.getEnv().getPersonnages().get(i).getY()
                    && super.getY() + super.getTaille() > super.getEnv().getPersonnages().get(i).getY()){
                ennemis.add(super.getEnv().getPersonnages().get(i));
            }
        }
        return ennemis;
    }

    @Override
    public void ameliorer() {

    }

    @Override
    public void action() {
        ArrayList<Personnage> ennemis = ennemiDessus();
        System.out.println(ennemis);
        for (Personnage ennemi : ennemis){
            ennemi.subirDegat(super.getDégât());
            System.out.println(ennemi);
        }
    }


}
