package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.List;

public class Pretre extends Personnage {

    private int portee;
    private Cibleur cibleur;
    private int pvSoin;

    public Pretre(double x, double y, int indiceTerrain, Environnement env, int portee, int pvSoin){
        super(15, x, y, 0.5, 10, 40, indiceTerrain, env, new Dijkstra(env));
        this.portee = portee;
        this.cibleur = new Cibleur(this.portee, this);
        this.pvSoin = pvSoin;
    }

    public int getPortee() {
        return portee;
    }

    @Override
    public void action(int temps){
        super.action(temps);
        if (temps % 200 == 0){
            soigner();
        }
    }

    private void soigner(){

        List<Personnage> personnages = cibleur.cibler();
        for (Personnage p : personnages){
            p.setPv(p.getPv() + pvSoin);
        }
    }




}
