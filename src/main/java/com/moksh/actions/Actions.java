package com.moksh.actions;

import com.moksh.annotations.Todo;
import com.moksh.db.Db;
import com.moksh.tui.MenuItem;
import com.moksh.tui.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Actions {
    private static final List<MenuItem<ActionType>> actions =
            Arrays.asList(
                    new MenuItem<>("Show Todos", ActionType.SHOW_TODOS),
                    new MenuItem<>("Create New Todo", ActionType.CREATE_TODO),
                    new MenuItem<>("Change Todo Name", ActionType.CHANGE_TODO_NAME),
                    new MenuItem<>("Toggle Todo Completed", ActionType.TOGGLE_TODO_COMPLETED),
                    new MenuItem<>("Quit", ActionType.QUIT)
            );

    public static void showTodos() throws Exception {
        var todos = Db.getAllTodos();

        Util.clearTerminal();

        System.out.println("Todos:");

        if (todos.isEmpty()) {
            System.out.println("No todos to show.");
            System.console().readLine("Press enter to go back to main menu");
            return;
        }


        for (Todo todo : todos) {
            System.out.printf("[%s] %s\n", todo.isCompleted() ? "x" : " ", todo.getName());
        }

        System.out.println();

        System.console().readLine("Press enter to go back to main menu");
    }

    public static void createTodo() throws Exception {
        Util.clearTerminal();

        System.out.println("Create a todo:");

        System.out.println("What is the name of the todo:");
        var todoName = System.console().readLine();

        Db.createTodo(todoName);

        System.out.println("Todo created.");
        System.out.println();

        System.console().readLine("Press enter to go back to main menu");
    }

    public static void editTodoName() throws Exception {
        Util.clearTerminal();

        System.out.println("Edit the name of a todo:");

        var todos = Db.getAllTodos();

        if (todos.isEmpty()) {
            System.out.println("You do not have any todos to edit");
            System.console().readLine("Press enter to go back to main menu");
            return;
        }

        List<MenuItem<Todo>> menuItems = new ArrayList<>();

        for (Todo todo : todos) {
            menuItems.add(new MenuItem<>(String.format("[%s] %s", todo.isCompleted() ? "x": " ", todo.getName()), todo));
        }

        var selectedTodo = Util.showMenu(menuItems);

        Util.clearTerminal();

        assert selectedTodo != null;
        System.out.println("Selected Todo " + selectedTodo.name());

        System.out.println("What is the new name of the todo:");
        var todoName = System.console().readLine();

        Db.editTodoName(selectedTodo.item(), todoName);

        System.out.println("Todo name changed");
        System.out.println();
        System.console().readLine("Press enter to go back to main menu");
    }

    public static void toggleTodoCompleted() throws Exception {
        Util.clearTerminal();

        System.out.println("Edit the name of a todo:");

        var todos = Db.getAllTodos();

        if (todos.isEmpty()) {
            System.out.println("You do not have any todos to edit");
            System.console().readLine("Press enter to go back to main menu");
            return;
        }

        List<MenuItem<Todo>> menuItems = new ArrayList<>();

        for (Todo todo : todos) {
            menuItems.add(new MenuItem<>(String.format("[%s] %s", todo.isCompleted() ? "x": " ", todo.getName()), todo));
        }

        var selectedTodo = Util.showMenu(menuItems);

        Util.clearTerminal();

        assert selectedTodo != null;
        System.out.println("Selected Todo " + selectedTodo.name());

        Db.toggleTodoCompleted(selectedTodo.item());

        System.out.println("Todo completed status toggled");
        System.out.println();
        System.console().readLine("Press enter to go back to main menu");
    }

    public static ActionType showActionMenu() {
        return Objects.requireNonNull(Util.showMenu(actions)).item();
    }

}
