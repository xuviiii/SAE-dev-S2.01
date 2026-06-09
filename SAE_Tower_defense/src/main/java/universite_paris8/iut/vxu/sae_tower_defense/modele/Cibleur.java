package universite_paris8.iut.vxu.sae_tower_defense.modele;

import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

public class Cibleur {

    private int portee;
    private Entite entite;

    public Cibleur(int portee, Entite entite) {
        this.portee = portee;
        this.entite = entite;
    }

    public List<Personnage> cibler(){

        List<Personnage> ennemisCiblables = new ArrayList<>();
        ObservableList<Personnage> personnages = entite.getEnv().getPersonnages();

        for (int i = 0; i < personnages.size(); i++){
            if (((entite.getX() - portee) < personnages.get(i).getX())
                    && ((entite.getX() + portee) > personnages.get(i).getX())
                    && ((entite.getY() - portee) < personnages.get(i).getY())
                    && ((entite.getY() + portee) > personnages.get(i).getY())) {

                ennemisCiblables.add(personnages.get(i));
            }
        }

        return ennemisCiblables;
    }
}
