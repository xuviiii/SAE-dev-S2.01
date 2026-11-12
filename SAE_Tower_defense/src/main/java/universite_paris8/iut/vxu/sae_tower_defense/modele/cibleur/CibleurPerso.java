package universite_paris8.iut.vxu.sae_tower_defense.modele.cibleur;

import universite_paris8.iut.vxu.sae_tower_defense.modele.Entite;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.personnage.Personnage;

import java.util.ArrayList;

public class CibleurPerso extends  Cibleur{


    public CibleurPerso(int portee, Entite entite) {
        super(portee, entite);
    }


    @Override
    public void ajouterAuCibleur(ArrayList ennemisCiblables, Entite entite) { //modifié pour avoir une condition et ne pas dupliquer le add
        ennemisCiblables.add(entite);
    }

    public ArrayList<Entite> getListAInspecter(){
        return new ArrayList<>(Environnement.getEnvironnement().getPersonnages()) ;
    }
}
