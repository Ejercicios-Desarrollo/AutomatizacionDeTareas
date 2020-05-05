package automatizacion.acciones.whatsapp;

import automatizacion.config.Config;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class AdapterTwilio implements AdapterWhatsAppSender {
    @Override
    public void enviarWhastapp(WhatsApp whatsApp) {
        Config configTwilio= new Config("src/main/resources/Twilio.properties");
        Twilio.init(configTwilio.get("ACCOUNT_SID"), configTwilio.get("AUTH_TOKEN"));

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + whatsApp.getDestinatario()),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                whatsApp.getTexto())
                .create();
    }
}
