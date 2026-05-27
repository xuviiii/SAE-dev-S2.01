package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Personnage;

public class ObsPersoPvAZero implements ChangeListener<Number> {
    private Environnement map;
    private Personnage personnage;

    public ObsPersoPvAZero(Environnement map, Personnage personnage) {
        this.map = map;
        this.personnage = personnage;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        if (personnage.estMort())
            map.getPersonnages().remove(personnage);
    }
}
