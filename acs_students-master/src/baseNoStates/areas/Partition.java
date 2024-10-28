package baseNoStates.areas;

import baseNoStates.Area;
import baseNoStates.Door;

import java.util.ArrayList;

public class Partition extends Area { // Herencia de Area que és un composite

    public ArrayList<Area> fills;

    public Partition(String nom, Area a) {
        super(nom, a);
        if (pare != null)
            a.afegirFill(this); // Determina al seu pare l'existencia del seu fill si es pensa com una arbre
        fills = new ArrayList<>();
    }

    @Override
    public Area findAreaById(String AreaId) {
        if (AreaId.equals(id)) {
            return this;
        } else {
            for (Area fill : fills) {
                Area resultat = fill.findAreaById(AreaId); // Cerca de l'existencia de la partició als fills
                if (resultat != null) {
                    return resultat;
                }
            }
        }
        return null;
    }

    @Override
    public void afegirFill(Area a) {
        fills.add(a);
    }

    @Override
    public ArrayList<Space> getSpaces() { // De tots els fills que té agrupa tots els espais possibles
        ArrayList<Space> resultat = new ArrayList<>();
        for (Area a : fills) {
            ArrayList<Space> espais = a.getSpaces();
            resultat.addAll(espais);
        }
        return resultat;
    }

    @Override
    public ArrayList<Door> getDoorsGivingAccess() { // Agafa totes les portes que tens accès
        ArrayList<Door> resultat = new ArrayList<>();
        for (Space s : getSpaces()) {
            resultat.addAll(s.getDoorsGivingAccess());
        }
        return resultat;
    }
}
