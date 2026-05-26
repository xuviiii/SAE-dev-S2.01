package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Environnement {

    private static int indiceCible = 99;

    private ObservableList<Integer> terrain; // 10 * 10 ?
    private List<Personnage> ennemies;

    public Environnement(){
        this.terrain = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {

            if((i % 10) == (i / 10) || (i % 9) == 0){
                terrain.add(1);
            } else {
                terrain.add(0);
            }
        }

       ennemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
           ennemies.add(new Personnage(20, -1, -1, 0, 0, 20, 99, -1));
       }

    }







    public List<Personnage> getEnnemies() {
        return ennemies;
    }

    public void test(){

        for (int i = 0; i < 100; i++) {
            System.out.print(terrain.get(i) + " ");
            if((i+ 1) % 10 == 0){
                System.out.println();
            }

        }
    }

    public static void main(String[] args) {
        Environnement env = new Environnement();
        env.test();

//        System.out.println(env.adjacents(13));
//        System.out.println(env.adjacents(55));
//        System.out.println(env.adjacents(90));

        System.out.println("---------");

        System.out.println(env.ennemies.get(0).getIndiceTerrain());
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);
//        env.ennemies.get(0).seDeplace(env);

    }
}
