package universite_paris8.iut.vxu.sae_tower_defense;


public class Map {
    private int[][] map;

    public Map(){
        map = new int[5][5];
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if (j==2){
                    map[i][j] = 1;
                }
                else {
                    map[i][j] = 0;
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }
}
