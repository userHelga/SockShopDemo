package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalProperties {

    private static GlobalProperties instance;
    private static Properties properties;

    private GlobalProperties() {
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {

            properties = new Properties();
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static GlobalProperties getInstance() {
        if (instance == null) {
            synchronized (GlobalProperties.class) {
                if (instance == null) {
                    instance = new GlobalProperties();
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
