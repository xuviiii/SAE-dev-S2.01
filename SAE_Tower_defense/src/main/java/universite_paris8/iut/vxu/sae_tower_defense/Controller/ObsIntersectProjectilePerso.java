package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Map;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Projectile;

public class ObsIntersectProjectilePerso implements ChangeListener<Number> {
    private Map map;
    private Projectile projectile;

    public ObsIntersectProjectilePerso(Map map, Pane terrain, Projectile projectile) {
        this.map = map;
        this.projectile = projectile;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number aDouble, Number t1) {
        int i=0;
        boolean arret=false;
        while (i<map.getPersonnages().size()&&!arret){
            if (map.getPersonnages().get(i).estTouché(projectile.getX(), projectile.getY())){
                map.getPersonnages().get(i).subirDegat(projectile.getDegat());
                map.getProjectiles().remove(projectile);
                arret=true;
            }
            if (projectile.horsPortee()){
                map.getProjectiles().remove(projectile);
                arret=true;
            }
            i++;
        }
    }
}