package baseNoStates.time;

import baseNoStates.doorstates.Observable;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;


//Clase que se encarga de generar un thread que cuente 10 segundos
public class Clock {
    private LocalDateTime date;
    private Timer timer;
    private int period; // seconds
    public Clock(int period) {
        this.period = period;
        timer = new Timer();
    }

    //Esta función inicia el reloj y espera a un total de 10 segundos antes de que el observable
    // notifique a sus observadores
    public void start(Observable observervable) {

        TimerTask repeatedTask = new TimerTask() {
            public void run() {

                //Espera a que cierto periodo se haya completado
                observervable.notifyObservers();
            }
        };
        timer.scheduleAtFixedRate(repeatedTask, 0, 1000 * period);
    }

    //Finaliza el reloj
    public void stop() { timer.cancel(); }
    public int getPeriod() { return period; }
    public LocalDateTime getDate() { return date; }
}