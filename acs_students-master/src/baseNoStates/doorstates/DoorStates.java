package baseNoStates.doorstates;

import baseNoStates.Door;
import baseNoStates.time.Temporizador;

public abstract class DoorStates extends Observer {

    protected Door door;
    protected String name; // Fa referencia a l'estat de la porta
    protected Temporizador temporizador = new Temporizador();


    public DoorStates(Door d) {
        super(0);
        door = d; // Es referencia a quina porta està establert aquest estat
    }

    public String getNom() {
        return name;
    }

    // Totes aquestes funcions reamlment no estarn programades ja que no s'ha
    public abstract void open();
    public abstract void close();
    public abstract void lock();
    public abstract void unlock();
    public abstract void unlocked_shortly();

    //Función del Observer que "updates" el estado de la clase en función de un observable, esta función la ejecutará
    // el observado para notificar de cierta ocurrencia

    public void update() {
        //Dado el periodo de 10 segundos

        if (door.isClosed()) { //Bloqueamos en caso de que esté cerrado
            System.out.println("Bloquejant la porta " + door.getId() + " ...");
            door.setState(new Locked(door));
        } else { //Propped en caso de que la puerta este abierta (algo bloquea el cierre)
            System.out.println("Acció denegada -> porta " + door.getId() + " propped");
            door.setState(new Propped(door));
        }
        //Eliminamos de la cola de suscripción nuestra puerta
        temporizador.detach(this);

    }
}