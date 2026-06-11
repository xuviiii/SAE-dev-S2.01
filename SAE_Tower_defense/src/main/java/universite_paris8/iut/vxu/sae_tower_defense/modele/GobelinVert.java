package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.List;

public class GobelinVert extends Personnage {

    public GobelinVert( Environnement env){
        super(10, 1, 10, 32, env, new BFS(env));
    }
    

//    @Override
//    public void action(){
//        super.action();
//        //...
//    }
}
