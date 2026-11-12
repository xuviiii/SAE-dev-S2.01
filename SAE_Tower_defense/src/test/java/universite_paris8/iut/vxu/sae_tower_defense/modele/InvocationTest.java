package universite_paris8.iut.vxu.sae_tower_defense.modele;

import org.junit.jupiter.api.Test;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.ennemiCommun.ChevalDeTroie;

public class InvocationTest {


    @Test
    void TestInvoc(){
        Personnage BertrantLeCheval = new ChevalDeTroie(Environnement.getEnvironnement(),5);

        //BertrantLeCheval.



    }
}
