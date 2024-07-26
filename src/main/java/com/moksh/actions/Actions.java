package com.moksh.actions;

import com.moksh.annotations.Todo;
import com.moksh.db.Db;
import com.moksh.tui.MenuItem;
import com.moksh.tui.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Actions {
    private static final List<MenuItem<ActionType>> actions =
            Arrays.asList(
                    new MenuItem<>("Show Todos", ActionType.SHOW_TODOS),
                    new MenuItem<>("Quit", ActionType.QUIT)
            );

    public static void showTodos() throws Exception {
        var todos = Db.getAllTodos();

        Util.clearTerminal();

        System.out.println("Todos:");

        if (todos.isEmpty()) {
            System.out.println("No todos to show.");
            return;
        }


        for (Todo todo : todos) {
            System.out.printf("[%s] %s\n", todo.isCompleted() ? "x" : " ", todo.getName());
        }
    }

    public static ActionType showActionMenu() {


        Util.showMenu(actions);

        return ActionType.SHOW_TODOS;
    }

}
