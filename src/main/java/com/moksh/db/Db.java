package com.moksh.db;

import com.moksh.annotations.Todo;
import com.moksh.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public static void createTodo(String name) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            var newTodo = new Todo(name);
            session.persist(newTodo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void editTodoName(Todo todo, String newName) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.evict(todo);
            todo.setName(newName);
            session.merge(todo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
