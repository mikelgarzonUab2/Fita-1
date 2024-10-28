package baseNoStates.areas;

import baseNoStates.Area;
import baseNoStates.Door;

import java.util.ArrayList;

public class Space extends Area { // Herencia de classe àrea que conté totes les portes que té accés

    private ArrayList<Door> doors;

    public Space(String id, Area a) {
        super(id, a);
        if (pare != null)
            a.afegirFill(this); // Identifica l'existencia d'aquest espai al seu pare
        doors = new ArrayList<>();
    }

    public void setDoors(ArrayList<Door> ds) {
        doors = ds;
    }

    @Override
    public Area findAreaById(String nom) {
        if (nom.equals(id)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Space> getSpaces() { // Retrona a si mateix però en format llista
        ArrayList<Space> espai = new ArrayList<>();
        espai.add(this);
        return espai;
    }

    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        return doors; // Retorne llista de portes que té l'àrea
    }

}
