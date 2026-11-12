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
    public void ajouterAuCibleur(ArrayList ennemisCiblables, Entite entite) {
        ennemisCiblables.add(entite);
    }

    public ArrayList<Entite> getListAInspecter(){
        return new ArrayList<>(Environnement.getEnvironnement().getPersonnages()) ;
    }
}
