package automatizacion.obervadores;

import automatizacion.Tarea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TracerTarea extends Tracer {
    public TracerTarea(String nombreDelArchivo) {
        super(nombreDelArchivo);
    }

    @Override
    public void serNotificadoPor(IObservable observable) {
        Tarea tarea = (Tarea) observable;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = dateFormat.format(new Date());
        String base = hora + " [" + tarea.getNombre() + "]: ";

        switch (tarea.getEstadoTarea()){
            case EN_EJECUCION:
                this.agregarRegistro(base + "La tarea ha comenzado." + System.lineSeparator());
                tarea.getAcciones()
                        .forEach(a -> this.agregarRegistro(base + "La tarea va a ejecutar la accion de'" + a.getNombre() + "'." + System.lineSeparator()));
                break;
            case FINALIZADA:
                this.agregarRegistro(base + "La tarea ha finalizado." + System.lineSeparator());
                break;
        }
    }
}
