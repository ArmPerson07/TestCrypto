package readPropertiesTest;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertiesTest extends BaseTest {
    @Test
    public void readProperties() {

        // Load the configuration using OWNER
        AppConfig config = ConfigFactory.create(AppConfig.class);//Метод create принимает в качестве аргумента тип класса
                                                                 // — в данном случае AppConfig.class — интерфейс, определяющий свойства конфигурации.
        // Access the property
        String urlFromProperty = config.url();
        System.out.println(urlFromProperty);
    }
    @Test
    public void readPropertiesTest() throws IOException {
        // Загрузите конфигурацию с помощью OWNER
        AppConfig config = ConfigFactory.create(AppConfig.class);

        // Доступ к объекту properties
        String urlFromProperty = config.url();
        System.out.println(urlFromProperty);
    }
}
// Определите интерфейс конфигурации с помощью OWNER
interface AppConfig extends Config {
    @Key("url")
    String url();
}