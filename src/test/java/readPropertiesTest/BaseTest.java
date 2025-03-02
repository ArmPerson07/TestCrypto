package readPropertiesTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    /*Инициализация selenide с настройками*/
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }
    //Выполнение метода перед каждым запуском тестов
    @BeforeEach
    public void init() {
        setUp();
    }

    //Выполнение метода после каждого закрытия тестов
    @AfterEach
    public void tearDown() {
    }
}
