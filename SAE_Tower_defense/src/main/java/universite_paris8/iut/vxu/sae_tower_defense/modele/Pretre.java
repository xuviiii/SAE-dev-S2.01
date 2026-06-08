package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.List;

public class Pretre extends Personnage implements Cibleur {

    private int portee;
    private int pvSoin;

    public Pretre(Environnement env){
        super(15 ,0.5, 10, 32, env);
        this.portee = 100;
        this.pvSoin = 15;
    }

    public int getPortee() {
        return portee;
    }

    @Override
    public List<Integer> cheminVersCible(){
        return getEnv().getParcours().cheminVersCibleMoindreCout(getIndiceTerrain());
    }

    @Override
    public void action(int temps){
        super.action(temps);
        if (temps % 200 == 0){
            soigner();
        }
    }

    private void soigner(){

        List<Personnage> personnages = cibler();
        for (Personnage p : personnages){
            p.setPv(p.getPv() + pvSoin);
        }
    }




}
