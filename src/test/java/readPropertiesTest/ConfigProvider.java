package readPropertiesTest;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

public interface ConfigProvider extends Config {

    // Определите свойства конфигурации
    @Key("url")
    String url();

    @Key("age")
    Integer age();

    @Key("usersParams.admin.login")
    String adminLogin();

    @Key("usersParams.demo.password")
    String demoPassword();

    @Key("usersParams.demo.isAdmin")
    Boolean isDemoAdmin();

    // Загрузите конфигурацию
    ConfigProvider CONFIG = ConfigFactory.create(ConfigProvider.class);
}

/*public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties())
                : ConfigFactory.load("application.conf");
    }

    String URL=readConfig().getString("url");
    Integer age=readConfig().getInt("age");
    String admin_login=readConfig().getString("usersParams.admin.login");
    String demo_password=readConfig().getString("usersParams.demo.password");
    Boolean is_demo_admin=readConfig().getBoolean("usersParams.demo.isAdmin");*/

