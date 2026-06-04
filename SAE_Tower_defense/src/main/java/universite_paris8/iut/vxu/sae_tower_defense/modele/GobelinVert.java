package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class GobelinVert extends Personnage {

    public GobelinVert(double x, double y, int indiceTerrain, Environnement env){
        super(10, x, y, 0.5, 10, 32, indiceTerrain, env);
    }

    @Override
    public int tileSuivante(){
        return getEnv().getParcours().tileSuivante(getIndiceTerrain());
    }

//    @Override
//    public void action(){
//        super.action();
//        //...
//    }
}
