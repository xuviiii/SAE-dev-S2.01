package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun;

import universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur.Cibleur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur.CibleurPerso;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Dijkstra;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.List;

public class Pretre extends Personnage {

    private int portee;
    private Cibleur cibleur;
    private int pvSoin;
    private int compteurAbilite;

    public Pretre(Environnement env){
        super(15 ,0.5, 10, 32, env,new Dijkstra(env),10);
        this.portee = 100;
        this.cibleur = new CibleurPerso(this.portee, this);
        this.pvSoin = 15;
        compteurAbilite = 0;
    }

    @Override
    public void action(){
        super.action();
        compteurAbilite++;
        if (compteurAbilite % 200 == 0)
            soigner();
    }

    private void soigner(){
        List<Personnage> personnages = cibleur.cibler();
        for (Personnage p : personnages)
            p.setPv(p.getPv() + pvSoin);
    }
}