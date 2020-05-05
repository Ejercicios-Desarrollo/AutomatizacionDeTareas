package automatizacion.ejecuciones;

import automatizacion.acciones.Accion;
import automatizacion.Tarea;

public class EjecucionParalela implements EstrategiaDeEjecucion {
    @Override
    public void ejecutar(Tarea tarea) {
        tarea.getAcciones().parallelStream().forEach(Accion::ejecutar);
    }
}
