package baseNoStates.doorstates;

import baseNoStates.Door;

public class Locked extends DoorStates {

    public Locked(Door d) {
        super(d);
        this.name = States.LOCKED;
    }

    // Totes les accions s'han d'aplicar Override perquè DoorSate és abstract
    @Override
    public void open() {
        System.out.println("No es possible obrir la porta " + door.getId() + " -> està bloquejada");
    }

    public void close() {
        System.out.println("La porta " + door.getId() + " ja està tancada");
    }

    public void lock() {
        System.out.println("No es possible bloquejar la porta " + door.getId() + " -> ja està bloquejada");
    }

    public void unlock() {
        System.out.println("Desbloquejant la porta " + door.getId() + " ...");
        door.setState(new Unlock(door));
    }

    // Función que solo ejecuta el estado de Locked, UnlockedShortly desbloquea la
    // puerta y llama al temporizador para
    // que después de 10 segundos nos notifique de lo ocurrido.
    @Override
    public void unlocked_shortly() {
        System.out.println("Desbloquejant la porta temporalment" + door.getId() + " ...");
        UnlockedShortly US = new UnlockedShortly(door);
        door.setState(US);
        this.temporizador.attach(US);
    }

}