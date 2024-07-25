package com.moksh;

import com.moksh.annotations.Todo;
import com.moksh.hibernate.HibernateUtil;
import com.moksh.tui.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Todo todo = new Todo("Subscribe");
        Todo todo1 = new Todo("Unsubscribe");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(todo);
            session.save(todo1);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Todo> todos = session.createQuery("from Todo", Todo.class).list();

            todos.forEach(t -> System.out.println(t.getName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        System.out.println("Hello world!");
        sleep(500);
        Util.clearTerminal();
    }
}