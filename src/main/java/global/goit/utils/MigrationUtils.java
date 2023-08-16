package global.goit.utils;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

public class MigrationUtils {

    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = HibernateUtils.class
                .getClassLoader()
                .getResourceAsStream("hibernate.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkMigration() {
        String url = properties.getProperty(URL);
        String name = properties.getProperty(USER);
        String password = properties.getProperty(PASS);

        Flyway flyway = Flyway
                .configure()
                .dataSource(url, name, password)
                .load();

        flyway.migrate();
    }
}
