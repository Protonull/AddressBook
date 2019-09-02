package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.utilities.ConsoleUtilities;
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
                    ConsoleUtilities.printLine("Found match based on first name.");
                    found = entry;
                    break;
                }
                if (StringUtilities.isSimilar(entry.getOtherNames(), command)) {
                    ConsoleUtilities.printLine("Found match based on other names.");
                    found = entry;
                    break;
                }
                if (StringUtilities.isSimilar(entry.getEmailAddress(), command)) {
                    ConsoleUtilities.printLine("Found match based on email address.");
                    found = entry;
                    break;
                }
                if (StringUtilities.isSimilar(entry.getPhoneNumber(), command)) {
                    ConsoleUtilities.printLine("Found match based on phone number.");
                    found = entry;
                    break;
                }
                // TODO: FIND MATCH BASED ON ADDRESS
            }
            if (found == null) {
                ConsoleUtilities.printLine("Could not find a match.");
            }
            else {
                ConsoleUtilities.printLine("Found a match, or the closest thing to a match:-");
                ConsoleUtilities.printLine(found.toString());
                Program.gotoMainMenu();
            }
        }
    }

}
