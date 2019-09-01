package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.impl.ContactEntry;

public class MainMenu implements IMenu {

    private IMenu next = null;

    @Override
    public IMenu getNextMenu() {
        return this.next == null ? this : this.next;
    }

    @Override
    public String[] getDisplayText() {
        return new String[] { "Please enter a command: LIST, NEW, SEARCH, CLOSE" };
    }

    @Override
    public void handleCommand(String command) {
        if (command.equalsIgnoreCase("NEW")) {
            this.next = new EditContactMenu(new ContactEntry());
        }
        else if (command.equalsIgnoreCase("LIST")) {
            this.next = new ListMenu();
        }
        else if (command.equalsIgnoreCase("SEARCH")) {
            this.next = new SearchMenu();
        }
        else if (command.equalsIgnoreCase("CLOSE")) {
            Program.addressBook.save();
            System.out.println("Closing.");
            Program.menu = null;
            Program.running = false;
        }
    }

}
