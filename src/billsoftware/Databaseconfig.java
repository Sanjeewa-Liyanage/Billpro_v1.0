package billsoftware;

import java.io.FileInputStream;
import java.util.Properties;

public class Databaseconfig {
    private Properties properties;

    public Databaseconfig() throws Exception{
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() throws Exception{
        try (FileInputStream stream = new FileInputStream("config/db.properties")) {
        properties.load(stream);
        } catch (Exception e){
            throw e;
        }
    }

    public String getUrl() {
        System.out.println(properties.getProperty("url"));
        return properties.getProperty("url");
    }

    public String getUsername() {
        System.out.println(properties.getProperty("username"));
        return properties.getProperty("username");
    }

    public String getPassword() {
        System.out.println(properties.getProperty("password"));
        return properties.getProperty("password");
    }
}
