package com.moksh;

import com.moksh.annotations.Todo;
import com.moksh.db.Db;
import com.moksh.hibernate.HibernateUtil;
import com.moksh.tui.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
//        createBoilerplateTodo();

        var todos = Db.getAllTodos();

        System.out.println(todos);
        sleep(500);
        Util.clearTerminal();
    }

    private static void createBoilerplateTodo() {
        Todo todo = new Todo("Subscribe");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(todo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus() != TransactionStatus.COMMITTED) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}