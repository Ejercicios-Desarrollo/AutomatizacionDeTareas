package automatizacion;

import automatizacion.acciones.Accion;
import automatizacion.ejecuciones.EstrategiaDeEjecucion;
import automatizacion.obervadores.IObservable;
import automatizacion.obervadores.IObserver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Tarea implements IObservable {
    private String nombre;
    private String descripcion;
    private EstadoTarea estadoTarea;
    private List<Accion> acciones;
    private List<IObserver> observers;
    private EstrategiaDeEjecucion estrategiaDeEjecucion;

    public Tarea(String nombre, String descripcion, EstrategiaDeEjecucion estrategiaDeEjecucion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estrategiaDeEjecucion = estrategiaDeEjecucion;
        this.acciones = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.estadoTarea = EstadoTarea.INICIADA;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void agregarAccion(Accion ... acciones){
        Collections.addAll(this.acciones, acciones);
    }

    public void ejecutar(){
        cambiarEstadoYNotificar(EstadoTarea.EN_EJECUCION);
        this.estrategiaDeEjecucion.ejecutar(this);
        cambiarEstadoYNotificar(EstadoTarea.FINALIZADA);
        System.out.println("Estoy ejecutando");
    }

    private void cambiarEstadoYNotificar(EstadoTarea estadoTarea){
        this.estadoTarea = estadoTarea;
        notificar();
    }

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
