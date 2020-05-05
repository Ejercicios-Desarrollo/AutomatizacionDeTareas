package automatizacion.acciones;

import automatizacion.EstadoTarea;
import automatizacion.obervadores.IObservable;
import automatizacion.obervadores.IObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Accion implements IObservable {
    private String nombre;
    private String pasoActual;
    private List<IObserver> observers;

    public Accion(String nombre){
        this.nombre = nombre;
        this.observers = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPasoActual() {
        return pasoActual;
    }

    protected void cambiarPasoYNotificar(String pasoActual){
        this.pasoActual = pasoActual;
        notificar();
    }

    public abstract void ejecutar();

    @Override
    public void notificar() {
        this.observers.forEach(o -> o.serNotificadoPor(this));
    }

    @Override
    public void agregarObserver(IObserver... observers) {
        Collections.addAll(this.observers, observers);
    }

    @Override
    public void eliminarObserver(IObserver observer) {
        this.observers.remove(observer);
    }
}
