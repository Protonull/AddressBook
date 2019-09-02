package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.impl.ContactEntry;

public class MainMenu implements IMenu {

    @Override
    public String[] getDisplayText() {
        return new String[] { "Available commands: LIST, NEW, SEARCH, CLOSE" };
    }

    @Override
    public String getRequestText() {
        return "Please enter a command:";
    }

    @Override
    public void handleCommand(String command) {
        if (command.equalsIgnoreCase("NEW")) {
            Program.gotoNextMenu(new EditContactMenu(new ContactEntry()));
        }
        else if (command.equalsIgnoreCase("LIST")) {
            Program.gotoNextMenu(new ListMenu());
        }
        else if (command.equalsIgnoreCase("SEARCH")) {
            Program.gotoNextMenu(new SearchMenu());
        }
        else if (command.equalsIgnoreCase("CLOSE")) {
            Program.addressBook.save();
            Program.running = false;
            System.out.println("Closing.");
        }
    }

}
