package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Cibleur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement.Dijkstra;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.List;

public class Pretre extends Personnage {

    private int portee;
    private Cibleur cibleur;
    private int pvSoin;
    private int compteurAbilite;

    public Pretre(){
        super(15 ,0.5, 10, 32,new Dijkstra(),10);
        this.portee = 100;
        this.cibleur = new Cibleur(this.portee, this);
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
        List<Personnage> personnages = cibleur.ciblerPerso();
        for (Personnage p : personnages)
            p.setPv(p.getPv() + pvSoin);
    }
}