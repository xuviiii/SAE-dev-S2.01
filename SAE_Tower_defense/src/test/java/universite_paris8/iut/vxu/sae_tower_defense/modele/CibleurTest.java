package universite_paris8.iut.vxu.sae_tower_defense.modele;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur.Cibleur;
import universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur.CibleurPerso;
import universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur.CibleurTourProjectile;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.GobelinVert;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.Camp;
import universite_paris8.iut.vxu.sae_tower_defense.modele.tour.tourHorsChemin.tourProjectile.tourProjectileLance.TourDeFleche;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CibleurTest {

    private void ajouterNvPersoEnv(Environnement env, double x, double y){
        Personnage ennemie = new GobelinVert(env);
        ennemie.setX(x);
        ennemie.setY(y);
        env.ajouterPersonnage(ennemie);
    }


    @Test
    void ciblerEnnemie() {
        Environnement env = Environnement.getEnvironnement();
        Cibleur cibleur = new CibleurPerso(10, new TourDeFleche(20,20,env));
        ArrayList attendue= new ArrayList<>();
        assertEquals(attendue, cibleur.cibler(), "cas pas d'ennemie" );
        ajouterNvPersoEnv(env, 35, 35);
        assertEquals(attendue, cibleur.cibler(), "ennemie hors-porter" );
        for(int i=0; i<3; i++){
            ajouterNvPersoEnv(env, 27+i, 20);
            attendue.add(env.getPersonnages().get(env.getPersonnages().size()-1));
        }
        assertEquals(attendue, cibleur.cibler(), "cas normal" );
        env.ajouterTour(new TourDeFleche(25,25,env));
        assertEquals(attendue, cibleur.cibler(), "alier a porter" );
    }

    @Test
    void ciblerAllie() {
        Environnement env = Environnement.getEnvironnement();
        Cibleur cibleur = new CibleurTourProjectile(10, new Camp(20,20,env));
        ArrayList attendue= new ArrayList<>();
        assertEquals(attendue, cibleur.cibler(), "cas pas d'alier" );
        env.ajouterTour(new TourDeFleche(35,35,env));
        assertEquals(attendue, cibleur.cibler(), "alier hors-porter" );
        for(int i=0; i<3; i++){
            env.ajouterTour(new TourDeFleche(27+i,20,env));
            attendue.add(env.getTours().get(env.getTours().size()-1));
        }
        assertEquals(attendue, cibleur.cibler(), "cas normal" );
        ajouterNvPersoEnv(env, 25, 25);;
        assertEquals(attendue, cibleur.cibler(), "ennemie a porter" );
    }
}