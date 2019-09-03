package io.protonull.addressbook;

import io.protonull.addressbook.api.IAddressBook;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.impl.menus.MainMenu;
import io.protonull.addressbook.impl.json.JSONAddressBook;
import io.protonull.addressbook.utilities.ConsoleUtilities;
import java.io.File;
import java.util.Stack;

public final class Program {

    private static final Stack<IMenu> menus = new Stack<>();

    public static IAddressBook addressBook;
    public static boolean running = true;

    public static void main(String[] arguments) {
        System.setErr(System.out);
        gotoNextMenu(new MainMenu());
        addressBook = new JSONAddressBook();
        addressBook.setLocation(new File(System.getProperty("user.dir"), "/addressbook.json"));
        addressBook.load();
        while (running) {
            // Clear the console
            ConsoleUtilities.clear();
            // If the menu stack somehow doesn't have a menu, exit out
            if (menus.isEmpty()) {
                ConsoleUtilities.printLine("Could not find a menu, exiting.");
                running = false;
                break;
            }
            IMenu menu = menus.peek();
            // If the menu is null, go back one
            if (menu == null) {
                menus.pop();
                continue;
            }
            // Otherwise print the menu's display text, if it has any
            String[] displayText = menu.getDisplayText();
            if (displayText != null) {
                for (String line : displayText) {
                    if (line == null || line.isEmpty()) {
                        ConsoleUtilities.printLine(" ");
                    }
                    else {
                        ConsoleUtilities.printLine(line);
                    }
                }
            }
            // And print its request text, if it has it
            String requestText = menu.getRequestText();
            if (requestText != null && !requestText.isEmpty()) {
                ConsoleUtilities.print(requestText + " ");
            }
            // The menu is then given the opportunity to process, which
            // by default will read the next line as a command input
            menu.process();
        }
        addressBook.save();
    }

    public static void gotoNextMenu(IMenu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("Cannot go to a null menu.");
        }
        menus.addElement(menu);
    }

    public static void gotoPreviousMenu() {
        IMenu menu = menus.pop();
        if (menus.empty()) {
            menus.addElement(menu);
        }
    }

    public static void gotoMainMenu() {
        IMenu menu = menus.firstElement();
        menus.clear();
        menus.addElement(menu);
    }

}
