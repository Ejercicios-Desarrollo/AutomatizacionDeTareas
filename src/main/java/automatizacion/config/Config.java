package automatizacion.config;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    public static final String RUTA_EXPORTACION = "D:\\";
    private Properties config;

    public Config(String filepath){
        this.config = new Properties();
        try{
            config.load(new FileInputStream(filepath));
        }
        catch (Exception ex) {
            throw new NoExisteArchivo();
        }
    }

    public String get(String key){
        return this.config.getProperty(key);
    }
}
