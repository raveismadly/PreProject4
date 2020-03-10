package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        if (connection == null) {
            connection = getMYSQLConnection();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }
    private static Properties propertiesHibernate = PropertiesReader.getProperties("hibernate.properties");
    private static String Dialect =propertiesHibernate.getProperty("hibernate.dialect");
    private static String DriverClass =propertiesHibernate.getProperty("hibernate.connection.driver_class");
    private static String Url =propertiesHibernate.getProperty("hibernate.connection.url");
    private static String User =propertiesHibernate.getProperty("hibernate.connection.username");
    private static String Password=propertiesHibernate.getProperty("hibernate.connection.password");
    private static String SQL=propertiesHibernate.getProperty("hibernate.show_sql");
    private static String DDL=propertiesHibernate.getProperty("hibernate.hbm2ddl.auto");

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect",Dialect);
        configuration.setProperty("hibernate.connection.driver_class",DriverClass);
        configuration.setProperty("hibernate.connection.url",Url);
        configuration.setProperty("hibernate.connection.username",User);
        configuration.setProperty("hibernate.connection.password",Password);
        configuration.setProperty("hibernate.show_sql",SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto",DDL);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Properties propertiesConnect = PropertiesReader.getProperties("database.properties");
    private static String UrlConnect = propertiesConnect.getProperty("JdbcUrl");
    private static String UserConnect = propertiesConnect.getProperty("username");
    private static String PasswordConnect = propertiesConnect.getProperty("password");

    private static Connection getMYSQLConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(UrlConnect, UserConnect, PasswordConnect);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
