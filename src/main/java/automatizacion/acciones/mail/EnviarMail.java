package automatizacion.acciones.mail;

import automatizacion.acciones.Accion;
import automatizacion.config.Config;

public class EnviarMail extends Accion {
    private Mail mail;
    private AdapterEmailSender adapter;

    public EnviarMail(String nombre, Mail mail, AdapterEmailSender adapter) {
        super(nombre);
        this.mail = mail;
        this.adapter = adapter;
    }

    @Override
    public void ejecutar() {
        cambiarPasoYNotificar("Configurar SMTP.");
        Config configSMTP = new Config("src/main/resources/SMTP.properties");

        cambiarPasoYNotificar("Enviar Mail.");
        this.adapter.enviarMail(configSMTP, mail);
    }
}
