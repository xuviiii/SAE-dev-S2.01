package universite_paris8.iut.vxu.sae_tower_defense.modele;

import java.util.ArrayList;

public class Tour {
    private String id;
    private int x;
    private int y;
    private int portée;
    private int dégât;

    public Tour(String id, int x, int y, int portée, int dégât) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.portée = portée;
        this.dégât = dégât;
    }

    public Personnage ennemiACible(ArrayList<Personnage> ennemis){
        ArrayList<Personnage> ennemisCiblables = new ArrayList<>();
        Personnage ennemiACible;
        for (int i=0;i<ennemis.size();i++){
            if (x-portée*10<ennemis.get(i).getX()&&x+portée*10>ennemis.get(i).getX()){
                ennemisCiblables.add(ennemis.get(i));
            }
        }
        if (ennemisCiblables.isEmpty())
            return null;
        else{
            ennemiACible = ennemisCiblables.get(0);
            for (int i=1;i<ennemisCiblables.size();i++){
                if ((ennemiACible.getX()+ennemiACible.getY())<(ennemisCiblables.get(i).getX()+ennemisCiblables.get(i).getY()))
                    ennemiACible = ennemisCiblables.get(i);
            }
            return ennemiACible;
        }
    }

    public void attaquer(Personnage ennemi){
        ennemi.setPv(ennemi.getPv()-dégât);
    }
}
