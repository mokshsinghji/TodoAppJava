package com.moksh.db;

import com.moksh.annotations.Todo;
import com.moksh.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class Db {
    public static List<Todo> getAllTodos() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Todo", Todo.class).list();
        } catch (Exception e) {
            System.out.println("Unable to get all todos");
            throw e;
        }
    }
}
