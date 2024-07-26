package com.moksh.tui;

import java.util.HashMap;
import java.util.List;

public class Util {
    public static void clearTerminal() {
        System.out.println("\033[H\033[2J");
    }

    public static <T> MenuItem<T> showMenu(List<MenuItem<T>> choices) {
        clearTerminal();
        if (choices == null) {
            return null;
        }

        if (choices.isEmpty()) {
            return null;
        }

        int length = choices.size();
        int width = getWidth(length);

        System.out.println("Please choose one of the following by the number");

        for (int i = 0; i < length; i++) {
            MenuItem<T> item = choices.get(i);
            String name = item.name();

            String numberPadded = padNumber(i + 1, width);

            System.out.printf("%s. %s\n", numberPadded, name);
        }

        int choiceNum = 0;
        boolean done = false;

        while (!done || choiceNum <= 0 || choiceNum > length) {
            try {
                var choice = System.console().readLine("Please select an option: ");
                choiceNum = Integer.parseInt(choice);
                done = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }

        int index = choiceNum - 1;

        return choices.get(index);
    }

    public static int getWidth(int maxNum) {
        String asString = Integer.toString(maxNum);

        return asString.length();
    }

    public static String padNumber(int number, int width) {
        StringBuilder asString = new StringBuilder(Integer.toString(number));

        while (asString.length() < width) {
            asString.insert(0, " ");
        }

        return asString.toString();
    }
}
