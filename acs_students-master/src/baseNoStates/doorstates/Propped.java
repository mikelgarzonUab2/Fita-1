package baseNoStates.doorstates;

import baseNoStates.Door;

//Estdo de la puerta en dónde debería bloquearse pero se mantiene abierta por algún bloqueo
public class Propped extends DoorStates{

    public Propped(Door d) {
        super(d);
        this.name = States.PROPPED;
    }

    @Override
    public void open() {
        System.out.println("La porta " + door.getId() + " ja està oberta");

    }

    @Override
    public void close() {
            System.out.println("Tancant la porta " + door.getId() + " ...");
            door.setClose(true);
            door.setState(new Locked(door)); //Estableix el nou esta a bloquejat
    }

    @Override
    public void lock() {
        System.out.println("Acció denegada -> porta " + door.getId() + " oberta");
    }

    @Override
    public void unlock() {
        System.out.println("La porta " + door.getId() + " ja està desbolquejada");
    }

    @Override
    public void unlocked_shortly() {
        System.out.println("La porta " + door.getId() + " ja està desbolquejada");
    }

}