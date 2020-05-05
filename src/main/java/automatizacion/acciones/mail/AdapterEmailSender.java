package automatizacion.acciones.mail;

import automatizacion.config.Config;

public interface AdapterEmailSender {
    public void enviarMail(Config configSMTP, Mail mail);
}
