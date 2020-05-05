package automatizacion.ejecuciones;

import automatizacion.acciones.Accion;
import automatizacion.Tarea;

public class EjecucionSecuencial implements EstrategiaDeEjecucion {
    @Override
    public void ejecutar(Tarea tarea) {
        tarea.getAcciones().forEach(Accion::ejecutar);  //mismo que a -> a.ejecutar() pero no recibe parametros
    }
}
