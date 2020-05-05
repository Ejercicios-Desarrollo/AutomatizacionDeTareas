package automatizacion.obervadores;

import automatizacion.config.Config;

import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Tracer implements IObserver {
    private String nombreDelArchivo;
    private String extension = ".txt";
    private List<String> registros;

    public Tracer(String nombreDelArchivo){
        this.nombreDelArchivo = nombreDelArchivo;
        this.registros = new ArrayList<>();
    }

    public List<String> getRegistros() {
        return registros;
    }

    private String rutaCompletaDelArchivo(String fecha){
        return Config.RUTA_EXPORTACION + this.nombreDelArchivo + "-" + fecha + this.extension;
    }

    protected void agregarRegistro(String registro) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = dateFormat.format(new Date());

        Path path = Paths.get(rutaCompletaDelArchivo(fecha));
        try {
            Files.write(path, registro.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            this.registros.add(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
