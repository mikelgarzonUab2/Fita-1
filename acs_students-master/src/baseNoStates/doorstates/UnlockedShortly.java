package baseNoStates.doorstates;

import baseNoStates.Door;

//Estado de la puerta en dónde se mantiene desbloqueada durante cierto periodo de tiempo
public class UnlockedShortly extends DoorStates{

    public UnlockedShortly(Door d) {
        super(d);
        this.name = States.UNLOCKED_SHORTLY;
    }

    @Override
    public void open() {
        if (door.isClosed()) {
            System.out.println("Obrint la porta " + door.getId() + " ...");
            door.setClose(false);
        } else {
            System.out.println("La porta " + door.getId() + " ja està oberta");
        }
    }

    public void close() {
        if (door.isClosed()) {
            System.out.println("La porta " + door.getId() + " ja està tancada");
        } else {
            System.out.println("Tancant la porta " + door.getId() + " ...");
            door.setClose(true);
        }
    }

    public void lock() {
        if (door.isClosed()) {
            System.out.println("Bloquejant la porta " + door.getId() + " ...");
            door.setState(new Locked(door)); //Estableix el nou estat a bloquejat
        } else {
            System.out.println("Acció denegada -> porta " + door.getId() + " oberta");
        }
    }

    public void unlock() {
        System.out.println("La porta " + door.getId() + " ja està desbolquejada temporalment");
    }

    @Override
    public void unlocked_shortly() {
            System.out.println("La porta " + door.getId() + " ja està desbolquejada temporalment");
    }

}