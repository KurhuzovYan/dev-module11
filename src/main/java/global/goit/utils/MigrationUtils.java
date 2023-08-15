package global.goit.utils;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        String url = properties.getProperty("hibernate.connection.url");
        String name = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");

        Flyway flyway = Flyway
                .configure()
                .dataSource(url, name, password)
                .load();

        flyway.migrate();
    }
}
