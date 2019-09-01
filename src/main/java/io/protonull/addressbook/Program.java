package io.protonull.addressbook;

import io.protonull.addressbook.api.IAddressBook;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.impl.menus.MainMenu;
import io.protonull.addressbook.impl.json.JSONAddressBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.util.Scanner;

public final class Program {

    private static Logger logger = LoggerFactory.getLogger(Program.class);

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
