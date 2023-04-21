package org.example.constant;

public class SessionFactoryConstant {
    public static String JdbcUrl = "jdbc:mysql://localhost:3306/librabry_ms";
    public static String Username = "root";
    public static String Password = "78951";
    public static String DriverClassName = "com.mysql.cj.jdbc.Driver";
    public static String hbm2ddl = "update";
    public static String show_sql = "true";
    public static String format_sql = "true";
    public static String Dialect = "org.hibernate.dialect.MySQL8Dialect";
    public static String maximumPoolSize = "20";
    public static String connectionTimeout = "10000";
    public static String idleTimeout = "20000";
}
