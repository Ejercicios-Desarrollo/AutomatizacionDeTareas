package automatizacion.acciones.whatsapp;

public class WhatsApp {
    private String destinatario;
    private String texto;

    public WhatsApp(String destinatario, String texto){
        this.destinatario = destinatario;
        this.texto = texto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getTexto() {
        return texto;
    }
}
