package universite_paris8.iut.vxu.sae_tower_defense.modele;

public class GobelinRouge extends Personnage{
    public GobelinRouge( Environnement env){
        super(10, 2, 10, 32, env, new BFS(env), 5);
    }

}
