package baseNoStates.doorstates;

//Clase base que observa a observables
public abstract class Observer {
    public int seconds;
    public Observer(int seconds) {
        this.seconds = seconds;
    }
    public abstract void update();
}