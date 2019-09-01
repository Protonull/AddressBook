package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.impl.Utils;

public class SearchMenu implements IMenu {

    private IMenu next = this;

    @Override
    public IMenu getNextMenu() {
        return this.next;
    }

    @Override
    public String[] getDisplayText() {
        return new String[] { "Enter what you'd like to search for (or type EXIT):-" };
    }

    @Override
    public void handleCommand(String command) {
        if (command.equalsIgnoreCase("EXIT")) {
            this.next = new MainMenu();
        }
        else {
            IContactEntry found = null;
            for (IContactEntry entry : Program.addressBook.getEntries()) {
                if (Utils.isSimilar(entry.getFirstName(), command)) {
                    System.out.println("Found match based on first name.");
                    found = entry;
                    break;
                }
                if (Utils.isSimilar(entry.getOtherNames(), command)) {
                    System.out.println("Found match based on other names.");
                    found = entry;
                    break;
                }
                if (Utils.isSimilar(entry.getEmailAddress(), command)) {
                    System.out.println("Found match based on email address.");
                    found = entry;
                    break;
                }
                if (Utils.isSimilar(entry.getPhoneNumber(), command)) {
                    System.out.println("Found match based on phone number.");
                    found = entry;
                    break;
                }
                // TODO: FIND MATCH BASED ON ADDRESS
            }
            if (found == null) {
                System.out.println("Could not find a match.");
            }
            else {
                System.out.println("Found a match, or the closest thing to a match:-");
                System.out.println(found.toString());
                this.next = new MainMenu();
            }
        }
    }

}
