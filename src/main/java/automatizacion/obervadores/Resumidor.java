package automatizacion.obervadores;

import automatizacion.Tarea;
import automatizacion.config.Config;
import automatizacion.exportadores.ExportadorPDF;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Resumidor implements IObserver {

    private String nombreDelArchivo;
    private String extension = ".pdf";
    private ExportadorPDF exportadorPDF;
    private List<String> datos;

    public Resumidor(ExportadorPDF exportadorPDF){
        this.exportadorPDF = exportadorPDF;
        datos = new ArrayList<>();
    }

    public List<String> getDatos() {
        return datos;
    }

    private String rutaCompletaDelArchivo() {
        return Config.RUTA_EXPORTACION + this.nombreDelArchivo + this.extension;
    }

    public void setNombreDelArchivo(String nombreDelArchivo) {
        this.nombreDelArchivo = nombreDelArchivo;
    }

    @Override
    public void serNotificadoPor(IObservable observable) {
        Tarea tarea = (Tarea) observable;

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = dateFormat.format(new Date());

        switch (tarea.getEstadoTarea()){
            case EN_EJECUCION:
                datos.add("Nombre: " + tarea.getNombre());
                setNombreDelArchivo(tarea.getNombre());
                datos.add("Descripcion: " + tarea.getDescripcion());
                datos.add("Hora de ejecucion: " + hora);
                Integer cantidadAcciones = tarea.getAcciones().size();
                datos.add("Cantidad de acciones ejecutadas: " + cantidadAcciones);
                break;
            case FINALIZADA:
                datos.add("Hora de finalizacion: " + hora);
                break;
        }
    }

    public void exportar(){
        System.out.println(Arrays.toString(datos.toArray()));
        this.exportadorPDF.exportar(datos, rutaCompletaDelArchivo());
    }

}
