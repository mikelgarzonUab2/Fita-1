package baseNoStates;

import baseNoStates.areas.Space;

import java.util.ArrayList;

public abstract class Area { // Classe abtract que permet desenvolupar les classes Area i Space
    protected String id;
    protected Area pare;

    public Area(String nom, Area a) {
        id = nom;
        pare = a;
    }

    public String getId() {
        return id;
    }

    public abstract Area findAreaById(String id);

    public abstract ArrayList<Space> getSpaces();

    public abstract ArrayList<Door> getDoorsGivingAccess();

    public void afegirFill(Area a) {}
}
