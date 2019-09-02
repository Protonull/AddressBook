package io.protonull.addressbook;

import io.protonull.addressbook.api.IAddressBook;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.impl.menus.MainMenu;
import io.protonull.addressbook.impl.json.JSONAddressBook;
import io.protonull.addressbook.utilities.ConsoleUtilities;
import java.io.File;
import java.util.Scanner;

public final class Program {


    public static IAddressBook addressBook;
    public static IMenu menu = null;
    public static boolean running = true;

    public static void main(String[] arguments) {
        addressBook = new JSONAddressBook();
        addressBook.setLocation(new File(System.getProperty("user.dir"), "/addressbook.json"));
        addressBook.load();
        menu = new MainMenu();
        Scanner scanner = new Scanner(System.in);
        displayMenuText();
        while (running && scanner.hasNextLine()) {
            IMenu oldMenu = menu;
            menu.handleCommand(scanner.nextLine());
            System.out.println("\r\n");
            if (menu != null) {
                menu = menu.getNextMenu();
            }
            ConsoleUtilities.clear();
            displayMenuText();
        }
        addressBook.save();
    }

    public static void displayMenuText() {
        if (menu != null) {
            for (String line : menu.getDisplayText()) {
                System.out.println(line);
            }
        }
    }

}
