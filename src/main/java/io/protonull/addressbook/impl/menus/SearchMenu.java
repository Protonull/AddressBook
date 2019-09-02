package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.utilities.StringUtilities;

public class SearchMenu implements IMenu {

    @Override
    public String[] getDisplayText() {
        return null;
    }

    @Override
    public String getRequestText() {
        return "Enter what you'd like to search for (or type EXIT):";
    }

    @Override
    public void handleCommand(String command) {
        if (command.equalsIgnoreCase("EXIT")) {
            Program.gotoMainMenu();
        }
        else {
            IContactEntry found = null;
            for (IContactEntry entry : Program.addressBook.getEntries()) {
                if (StringUtilities.isSimilar(entry.getFirstName(), command)) {
                    System.out.println("Found match based on first name.");
                    found = entry;
                    break;
                }
                if (StringUtilities.isSimilar(entry.getOtherNames(), command)) {
                    System.out.println("Found match based on other names.");
                    found = entry;
                    break;
                }
                if (StringUtilities.isSimilar(entry.getEmailAddress(), command)) {
                    System.out.println("Found match based on email address.");
                    found = entry;
                    break;
                }
                if (StringUtilities.isSimilar(entry.getPhoneNumber(), command)) {
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
                Program.gotoMainMenu();
            }
        }
    }

}
