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
           ennemies.add(new Personnage(20, -1, -1, 0, 0, 20));
       }

    }

    public Set<Integer> adjacents(int indice){

        // condition / tests indices
        var adjacents = new HashSet<Integer>();

        if(indice > 9) {

            if (terrain.get(indice).equals(terrain.get(indice - 10))) {
                adjacents.add(indice - 10);
            }

            // adjacent nord/ouest
            if ( indice % 10 != 0 && terrain.get(indice).equals(terrain.get(indice - 11))) {
                adjacents.add(indice - 11);
            }

            // adjacent nord/est
            if ((indice + 1) % 10 != 0 && terrain.get(indice).equals(terrain.get(indice - 9))) {
                adjacents.add(indice - 9);
            }

        }

        if(indice < 90){

            if( terrain.get(indice).equals(terrain.get(indice + 10))){
                adjacents.add(indice + 10);
            }

            // adjacent sud/ouest
            if(indice % 10 != 0 && terrain.get(indice).equals(terrain.get(indice + 9))){
                adjacents.add(indice - 9);
            }

            // adjacent sud/est
            if((indice + 1) % 10 != 0 && terrain.get(indice).equals(terrain.get(indice + 11))){
                adjacents.add(indice + 11);
            }

        }

        if(indice % 10 != 0 && terrain.get(indice).equals(terrain.get(indice - 1))){
            adjacents.add(indice - 1);
        }

        if((indice + 1) % 10 != 0 && terrain.get(indice).equals(terrain.get(indice + 1))){
            adjacents.add(indice + 1);
        }

        return adjacents;
    }

    public Map<Integer, Integer> BFS(int source){

        List<Integer> parcours = new ArrayList<>();

        LinkedList<Integer> fifo = new LinkedList<>();
        fifo.add(source);

        Map<Integer, Integer> predecesseurs = new HashMap<>();
        predecesseurs.put(source, null);

        while(!fifo.isEmpty()){

            Integer courant = fifo.poll();
            parcours.add(courant);

            for(Integer adjacent : adjacents(courant)){

                if(!parcours.contains(adjacent)){
                    parcours.add(adjacent);
                    predecesseurs.put(adjacent, courant);
                    fifo.add(adjacent);
                }

            }
        }

        return predecesseurs;
    }

    public List<Integer> cheminVersCible(int source){

        var predecesseurs = BFS(source);

        List<Integer> chemin = new ArrayList<>();

        Integer courant = indiceCible;

        while(courant != null){

            chemin.add(courant);
            courant = predecesseurs.get(courant);
        }

        Collections.reverse(chemin);

        return chemin;
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

        System.out.println(env.adjacents(13));
        System.out.println(env.adjacents(55));
        System.out.println(env.adjacents(90));

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
