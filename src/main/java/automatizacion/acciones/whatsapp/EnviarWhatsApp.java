package automatizacion.acciones.whatsapp;

import automatizacion.acciones.Accion;
import automatizacion.config.Config;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class EnviarWhatsApp extends Accion {
    private WhatsApp whatsApp;
    private AdapterWhatsAppSender adapter;


    public EnviarWhatsApp(String nombre, WhatsApp whatsApp, AdapterWhatsAppSender adapterWhatsAppSender) {
        super(nombre);
        this.whatsApp = whatsApp;
        this.adapter = adapterWhatsAppSender;
    }

    @Override
    public void ejecutar() {
        cambiarPasoYNotificar("Enviar Mensaje: '" + whatsApp.getTexto() + "' a Destinatario: " + whatsApp.getDestinatario());
        this.adapter.enviarWhastapp(whatsApp);
    }
}
