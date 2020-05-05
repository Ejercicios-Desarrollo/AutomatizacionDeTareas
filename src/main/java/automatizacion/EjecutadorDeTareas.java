package automatizacion;

import java.util.*;

public class EjecutadorDeTareas extends TimerTask {
    private List<Tarea> tareas;
    private Integer intervaloDeEjecucion;

    public EjecutadorDeTareas(){
        this.tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea ... tareas){
        Collections.addAll(this.tareas, tareas);
    }

    public Integer getIntervaloDeEjecucion() {
        return intervaloDeEjecucion;
    }

    public void setIntervaloDeEjecucion(Integer intervaloDeEjecucion) {
        this.intervaloDeEjecucion = intervaloDeEjecucion;
    }

    @Override
    public void run() {
        this.tareas.forEach(Tarea::ejecutar);
    }
}
