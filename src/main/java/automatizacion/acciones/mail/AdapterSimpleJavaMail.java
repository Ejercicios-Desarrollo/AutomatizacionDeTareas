package automatizacion.acciones.mail;

import automatizacion.config.Config;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class AdapterSimpleJavaMail implements AdapterEmailSender {
    @Override
    public void enviarMail(Config configSMTP, Mail mail) {
        Email email = EmailBuilder.startingBlank()
                .from(configSMTP.get("username"))
                .to(mail.getDestinatario())
                .withSubject(mail.getAsunto())
                .withPlainText(mail.getTexto())
                .buildEmail();

        MailerBuilder
                .withSMTPServer(configSMTP.get("server"), Integer.parseInt(configSMTP.get("port")),
                        configSMTP.get("username"), configSMTP.get("password"))
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer()
                .sendMail(email);
    }
}
