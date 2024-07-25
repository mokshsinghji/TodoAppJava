package com.moksh.tui;

public class Util {
    public static void clearTerminal() {
        System.out.println("\033[H\033[2J");
    }
}
