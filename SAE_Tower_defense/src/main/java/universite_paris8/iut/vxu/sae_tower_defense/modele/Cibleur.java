package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;
import java.util.List;

public interface Cibleur {

    int getPortee();
    double getX();
    double getY();
    Environnement getEnv();

    default List<Personnage> cibler(){

        List<Personnage> ennemisCiblables = new ArrayList<>();

        for (int i = 0; i < getEnv().getPersonnages().size(); i++){
            if (((getX() - getPortee()) < getEnv().getPersonnages().get(i).getX())
                    && ((getX() + getPortee()) > getEnv().getPersonnages().get(i).getX())
                    && ((getY() - getPortee()) < getEnv().getPersonnages().get(i).getY())
                    && ((getY() + getPortee()) > getEnv().getPersonnages().get(i).getY())){
                ennemisCiblables.add(getEnv().getPersonnages().get(i));
            }
        }

        return ennemisCiblables;
    }
}
