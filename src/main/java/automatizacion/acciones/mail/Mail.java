package automatizacion.acciones.mail;

public class Mail {
    private String destinatario;
    private String asunto;
    private String texto;

    public Mail(String destinatario, String asunto, String texto){
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.texto = texto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getTexto() {
        return texto;
    }
}
