package universite_paris8.iut.vxu.sae_tower_defense.Controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Environnement;
import universite_paris8.iut.vxu.sae_tower_defense.modele.GestionVague;
import universite_paris8.iut.vxu.sae_tower_defense.modele.Terrain;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {




    private GameLoop loop;
    private DragAndDrop dragAndDrop;
    private VueTerrain vueTerrain;

    @FXML
    private TilePane tile;

    @FXML
    private Pane terrainEntite;

    @FXML
    private Pane magasin;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label labelArgent;

    @FXML
    private Label labelVie;

    @FXML
    private Label labelVague;

    @FXML
    private Label labelVitesse;


    @FXML
    private Button pause;

    @FXML
    private Button accelerer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Environnement.getEnvironnement();
        Terrain.getTerrain();
        dragAndDrop = new DragAndDrop( terrainEntite, borderPane);
        vueTerrain = new VueTerrain(tile);
        loop=new GameLoop();

        // donne au terrainEntiter la taille de la map
        terrainEntite.setMaxWidth(Terrain.getTerrain().getLongueurMap()*Terrain.getTerrain().getTailleTile());
        terrainEntite.setMaxHeight(Terrain.getTerrain().getHauteurMap()*Terrain.getTerrain().getTailleTile());

        Environnement.getEnvironnement().getTours().addListener(new ObsTour(terrainEntite));
        Environnement.getEnvironnement().getProjectiles().addListener(new ObsProjectile(terrainEntite));
        Environnement.getEnvironnement().getPersonnages().addListener(new ObsPerso(terrainEntite));

        //gère le drag and drop
        dragAndDrop.objetdrag(magasin);

        Environnement.getEnvironnement().argentProperty().addListener((ob,old,nv) -> labelArgent.setText(nv.toString()));
        Environnement.getEnvironnement().argentDeBase();

        GestionVague.getVague().numVagueProperty().addListener((ob, old, nv)-> labelVague.setText(nv.toString()));
        loop.vittesseProperty().addListener((ob,old,nv)-> labelVitesse.setText("x"+nv.toString()));

        loop.getGameLoop().statusProperty().addListener((ob,old,nv)-> {
            if (nv.name().equals("PAUSED")) {
                pause.setText("Reprendre");
            }
            else {
                pause.setText("Pause");
            }
        });

        Environnement.getEnvironnement().vieProperty().addListener(new vie_listener(loop, terrainEntite));

        Environnement.getEnvironnement().vieProperty().addListener((ob,old,nv) -> labelVie.setText(nv.toString()));
        Environnement.getEnvironnement().vieDeBase();

        accelerer.setOnAction(e -> loop.changerVitesse());
        pause.setOnAction(e -> loop.mettrePause());


        terrainEntite.setOnMouseClicked(new Menu(terrainEntite));

        loop.initLoop();
        loop.lancer();
    }


}
