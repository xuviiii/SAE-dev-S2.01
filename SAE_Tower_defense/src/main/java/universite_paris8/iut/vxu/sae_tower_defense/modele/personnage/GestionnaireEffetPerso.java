package universite_paris8.iut.vxu.sae_tower_defense.modele.personnage;

public class GestionnaireEffetPerso {

    private boolean cuirasses;
    private boolean camoufles;
    private Personnage personnage;

    public void enleverCuirasses() {
        this.cuirasses = false;
        personnage.setTaille((int)(personnage.getTaille()/1.3));
    }

    public void rendreCuirases(){
        int alea = (int)(Math.random()*100)+1;
        if(alea <= 20 && personnage.getEnv().getVague().getNumVague() > 9){
            cuirasses = true;
            personnage.setTaille ((int)(personnage.getTaille()*1.3));
        }
        else {
            cuirasses = false;
        }
    }

    public void rendreCamoufles(){
        int alea = (int)(Math.random()*100)+1;
        if(alea <= 20 && personnage.getEnv().getVague().getNumVague() > 9)
            camoufles = true;
        else
            camoufles = false;
    }

    public void setCamoufles(boolean camoufles) {
        this.camoufles = camoufles;
    }

    public Boolean isCamoufles() {
        return camoufles;
    }

    public Boolean isCuirasses() {
        return cuirasses;
    }
}
