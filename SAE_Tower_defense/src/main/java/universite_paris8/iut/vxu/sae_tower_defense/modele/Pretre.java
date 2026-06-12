package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.List;

public class Pretre extends Personnage {

    private int portee;
    private Cibleur cibleur;
    private int pvSoin;

    public Pretre(Environnement env){
        super(15 ,0.5, 10, 32, env,new Dijkstra(env));
        this.portee = 100;
        this.cibleur = new Cibleur(this.portee, this);
        this.pvSoin = 15;
    }

    public int getPortee() {
        return portee;
    }

    @Override
    public void action(){
        super.action();
        if (getCompteurAction() % 200 == 0){
            soigner();
        }
    }

    private void soigner(){

        List<Personnage> personnages = cibleur.ciblerEnnemie();
        for (Personnage p : personnages){
            p.setPv(p.getPv() + pvSoin);
        }
    }




}
