package com.moksh;

import com.moksh.annotations.Todo;
import com.moksh.db.Db;
import com.moksh.hibernate.HibernateUtil;
import com.moksh.tui.MenuItem;
import com.moksh.tui.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
//        createBoilerplateTodo();
//        createBoilerplateTodo();

        var todos = Db.getAllTodos();
        var menuItems = makeMenuItemList(todos);

//        Util.showMenu(menuItems);

        sleep(500);
        Util.clearTerminal();
    }

    private static List<MenuItem<Todo>> makeMenuItemList(List<Todo> todos) {
        List<MenuItem<Todo>> menuItems = new ArrayList<>();

        for (Todo todo : todos) {
            menuItems.add(new MenuItem<>(todo.getName(), todo));
        }

        return menuItems;
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