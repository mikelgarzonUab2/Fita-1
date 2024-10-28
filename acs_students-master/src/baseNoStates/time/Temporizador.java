package baseNoStates.time;

import baseNoStates.doorstates.Observable;
import baseNoStates.doorstates.Observer;

import java.util.ArrayList;


// Esta es la clase que es Obserbable, esta se encargará de notificaar a sus observadores
// cuándo cierto cambio sea producido, en nuestro caso un periodo de tiempo de 10 segundos

public class Temporizador implements Observable {
    static final Clock clock = new Clock(1);
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    static final int period = 10;

    //Inicialización del Observable junto a su observador y arrancamos el reloj
    //Le pasamos el apuntador a nosotros para que, dado el periodo, se llamará a la función notifyObservers()
    public Temporizador(){
        clock.start(this);
    }

    //Añadimos a la cola de observadores un nuevo observador
    @Override
    public void attach(Observer observer) {
       this.observers.add(observer);
    }

    //Eliminamos de la cola de observadores un observador
    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    //Notify tiene un periodo de ejcución de 1 segundo, lo que actualiza el contador del observador hasta que este
    //Consiga un periodo de 10 segundo en donde se hará un Update que consiste en_:
    //Modificación del estado de la puerta
    //Detach de la cola de suscripción de observadores
    @Override
    public void notifyObservers() {
        if (!observers.isEmpty()) {
            for (Observer observer : observers) {
                observer.seconds++;
                if (observer.seconds == period) {
                    observer.update();
                }
            }
        }
    }
}