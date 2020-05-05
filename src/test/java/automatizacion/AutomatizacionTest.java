package automatizacion;

import automatizacion.acciones.AbrirAplicacion;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AutomatizacionTest {
    private AbrirAplicacion abrirChrome;
    private Mail mail;
    private AdapterSimpleJavaMail adapterSimpleJavaMail;
    private EnviarMail enviarMail;
    private WhatsApp whatsApp;
    private AdapterTwilio adapterTwilio;
    private EnviarWhatsApp enviarWhatsApp;
    private TracerAccion tracerAccion;
    private TracerTarea tracerTarea;
    private ExportadorPDF exportadorPDF;
    private Resumidor resumidor;
    private Tarea tarea1;

    @Before
    public void init(){
        abrirChrome = new AbrirAplicacion("Abrir Chrome", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        mail = new Mail("tomasmerencio@gmail.com", "Automatizacion de Tareas", "Hola!");
        adapterSimpleJavaMail = new AdapterSimpleJavaMail();
        enviarMail = new EnviarMail("Enviarme un mail", mail, adapterSimpleJavaMail);

        whatsApp = new WhatsApp("+5491130194744", "Hola!");
        adapterTwilio = new AdapterTwilio();
        enviarWhatsApp = new EnviarWhatsApp("Enviarme Hola!", whatsApp, adapterTwilio);

        tracerAccion = new TracerAccion("log-acciones");
        tracerTarea = new TracerTarea("log-tareas");

        exportadorPDF = new ExportadorPDF();
        resumidor = new Resumidor(exportadorPDF);

        abrirChrome.agregarObserver(tracerAccion);
        enviarMail.agregarObserver(tracerAccion);
        enviarWhatsApp.agregarObserver(tracerAccion);

        tarea1 = new Tarea("Tarea 1", "Abrir Google Chrome, Enviarme un Mail y Enviarme un WhatsApp", new EjecucionParalela());

        tarea1.agregarAccion(abrirChrome, enviarMail, enviarWhatsApp);

        tarea1.agregarObserver(tracerTarea, resumidor);
    }

    @Test
    public void TracersTienenRegistrosTest(){
        tarea1.ejecutar();
        resumidor.exportar();
        Assert.assertEquals(4, tracerAccion.getRegistros().size());
        Assert.assertEquals(5, tracerTarea.getRegistros().size());
        Assert.assertEquals(5, resumidor.getDatos().size());
    }
}
