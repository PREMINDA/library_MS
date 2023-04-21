package org.example.database;
import org.example.constant.SessionFactoryConstant;
import org.example.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataBase {

    private static SessionFactory sessionFactory = null;
    private static Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", SessionFactoryConstant.DriverClassName);
        configuration.setProperty("hibernate.connection.url", SessionFactoryConstant.JdbcUrl);
        configuration.setProperty("hibernate.connection.username", SessionFactoryConstant.Username);
        configuration.setProperty("hibernate.connection.password", SessionFactoryConstant.Password);
        configuration.setProperty("hibernate.dialect", SessionFactoryConstant.Dialect);
        configuration.setProperty("hibernate.show_sql", SessionFactoryConstant.show_sql);
        configuration.setProperty("hibernate.format_sql", SessionFactoryConstant.format_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", SessionFactoryConstant.hbm2ddl);
        configuration.setProperty("hibernate.connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");
        configuration.setProperty("hibernate.hikari.maximumPoolSize", SessionFactoryConstant.maximumPoolSize);
        configuration.setProperty("hibernate.hikari.connectionTimeout",SessionFactoryConstant.connectionTimeout);
        configuration.setProperty("hibernate.hikari.idleTimeout", SessionFactoryConstant.idleTimeout);
        configuration.addAnnotatedClass(Book.class);
        return configuration;
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = getConfiguration().buildSessionFactory();
        }
        return sessionFactory;
    }

}
