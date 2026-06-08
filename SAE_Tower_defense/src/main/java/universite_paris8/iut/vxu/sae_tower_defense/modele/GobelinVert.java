package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.List;

public class GobelinVert extends Personnage {

    public GobelinVert(double x, double y, int indiceTerrain, Environnement env){
        super(10, x, y, 1, 10, 32, indiceTerrain, env, new BFS(env));
    }

//    @Override
//    public void action(){
//        super.action();
//        //...
//    }
}
