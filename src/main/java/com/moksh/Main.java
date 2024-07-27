package com.moksh;

import com.moksh.actions.ActionType;
import com.moksh.actions.Actions;
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

public class Main {
    public static void main(String[] args) throws Exception {
        while (true) {
            ActionType actionType = Actions.showActionMenu();

            switch (actionType) {
                case SHOW_TODOS -> Actions.showTodos();
                case CREATE_TODO -> Actions.createTodo();
                case CHANGE_TODO_NAME -> Actions.editTodoName();
                case TOGGLE_TODO_COMPLETED -> Actions.toggleTodoCompleted();
                case QUIT -> System.exit(0);
            }
        }
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