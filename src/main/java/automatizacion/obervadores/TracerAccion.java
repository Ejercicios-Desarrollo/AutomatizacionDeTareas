package automatizacion.obervadores;

import automatizacion.acciones.Accion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TracerAccion extends Tracer {
    public TracerAccion(String nombreDelArchivo) {
        super(nombreDelArchivo);
    }

    @Override
    public void serNotificadoPor(IObservable observable) {
        Accion accion = (Accion) observable;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = dateFormat.format(new Date());
        String base = hora + " [" + accion.getNombre() + "]: " + accion.getPasoActual();
        this.agregarRegistro(base + System.lineSeparator());

    }
}
