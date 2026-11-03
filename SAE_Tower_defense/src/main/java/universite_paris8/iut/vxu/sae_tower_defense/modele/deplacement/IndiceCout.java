package universite_paris8.iut.vxu.sae_tower_defense.modele.deplacement;

public class IndiceCout implements Comparable<IndiceCout> {

    private int indice;
    private int cout;

    public IndiceCout(int indice, int cout) {
        this.indice = indice;
        this.cout = cout;
    }

    @Override
    public int compareTo(IndiceCout indiceCout) {
        return this.cout - indiceCout.cout;
    }

    public int getIndice() {
        return indice;
    }

    public int getCout() {
        return cout;
    }
}

