package automatizacion;

import automatizacion.acciones.*;
import automatizacion.acciones.mail.AdapterSimpleJavaMail;
import automatizacion.acciones.mail.EnviarMail;
import automatizacion.acciones.mail.Mail;
import automatizacion.acciones.whatsapp.AdapterTwilio;
import automatizacion.acciones.whatsapp.EnviarWhatsApp;
import automatizacion.acciones.whatsapp.WhatsApp;
import automatizacion.ejecuciones.EjecucionParalela;
import automatizacion.exportadores.ExportadorPDF;
import automatizacion.obervadores.Resumidor;
import automatizacion.obervadores.TracerAccion;
import automatizacion.obervadores.TracerTarea;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        AbrirAplicacion abrirChrome = new AbrirAplicacion("Abrir Chrome", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        Mail mail = new Mail("tomasmerencio@gmail.com", "Automatizacion de Tareas", "Hola!");
        AdapterSimpleJavaMail adapterSimpleJavaMail = new AdapterSimpleJavaMail();
        EnviarMail enviarMail = new EnviarMail("Enviarme un mail", mail, adapterSimpleJavaMail);

        WhatsApp whatsApp = new WhatsApp("+5491130194744", "Hola!");
        AdapterTwilio adapterTwilio = new AdapterTwilio();
        EnviarWhatsApp enviarWhatsApp = new EnviarWhatsApp("Enviarme Hola!", whatsApp, adapterTwilio);

        TracerAccion tracerAccion = new TracerAccion("log-acciones");
        TracerTarea tracerTarea = new TracerTarea("log-tareas");

        ExportadorPDF exportadorPDF = new ExportadorPDF();
        Resumidor resumidor = new Resumidor(exportadorPDF);

        abrirChrome.agregarObserver(tracerAccion);
        enviarMail.agregarObserver(tracerAccion);
        enviarWhatsApp.agregarObserver(tracerAccion);

        Tarea tarea1 = new Tarea("Tarea 1", "Abrir Google Chrome, Enviarme un Mail y Enviarme un WhatsApp", new EjecucionParalela());

        tarea1.agregarAccion(abrirChrome, enviarMail, enviarWhatsApp);

        tarea1.agregarObserver(tracerTarea, resumidor);

        EjecutadorDeTareas ejecutadorDeTareas = new EjecutadorDeTareas();
        ejecutadorDeTareas.setIntervaloDeEjecucion(60*1000);

        ejecutadorDeTareas.agregarTarea(tarea1);

        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(ejecutadorDeTareas, 0, ejecutadorDeTareas.getIntervaloDeEjecucion());

        try {
            Thread.sleep(180000);
        }
        catch (InterruptedException e){

        }

        resumidor.exportar();

        timer.cancel();

        System.out.println("Termino la ejecucion del hilo principal");
    }
}
