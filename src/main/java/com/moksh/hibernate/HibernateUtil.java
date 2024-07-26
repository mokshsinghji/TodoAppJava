package com.moksh.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            var config = new Configuration().configure();


            String userHome = System.getProperty("user.home");
            char separator = File.separatorChar;

            var fileName = userHome + separator + "TodoAppJava" + separator + "db.sqlite";

            Files.createDirectories(Paths.get(new File(fileName).getAbsoluteFile().getParent()));

            config.setProperty(
                    "hibernate.connection.url",
                    "jdbc:sqlite:" + fileName
            );

            return config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}