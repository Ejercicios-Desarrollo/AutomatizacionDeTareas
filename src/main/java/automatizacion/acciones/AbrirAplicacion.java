package automatizacion.acciones;

import automatizacion.obervadores.IObserver;

import java.io.IOException;

public class AbrirAplicacion extends Accion {
    private String nombreAplicacion;

    public AbrirAplicacion(String nombre, String nombreAplicacion){
        super(nombre);
        this.nombreAplicacion = nombreAplicacion;
    }

    @Override
    public void ejecutar() {
        Runtime runtime = Runtime.getRuntime();     //getting Runtime object
        try
        {
            cambiarPasoYNotificar("Abrir Aplicacion: " + nombreAplicacion);
            runtime.exec(nombreAplicacion);        //opens new notepad instance
            //OR runtime.exec("notepad");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
