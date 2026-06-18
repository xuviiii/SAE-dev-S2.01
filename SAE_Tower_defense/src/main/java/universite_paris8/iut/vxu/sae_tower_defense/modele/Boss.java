package universite_paris8.iut.vxu.sae_tower_defense.modele;

public abstract class Boss extends Personnage{
    public Boss(int pv, double vitesse, int degat, int taille, Environnement env, Deplacement deplacement, int gains) {
        super(pv, vitesse, degat, taille, env, deplacement, gains);
        setCamoufles(false);
        enleverCuirasses();
    }
}
