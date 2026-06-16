package universite_paris8.iut.vxu.sae_tower_defense.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class vie_listener implements ChangeListener<Number> {

    private GameLoop loop;
    private Pane terrainEntite;

    public vie_listener(GameLoop loop, Pane terrainEntite) {
        this.loop = loop;
        this.terrainEntite = terrainEntite;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number old, Number nv) {
        //change la view si les pv sont a 0
        if(nv.intValue() <= 0){
            try {
                Fin();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void Fin() throws Exception{
            loop.mettrePause();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/vxu/sae_tower_defense/Demarrage.fxml"));
            Stage stage = (Stage) terrainEntite.getScene().getWindow();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setMaximized(false);

    }
}
