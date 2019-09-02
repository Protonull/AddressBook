package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.api.IContractAddress;
import io.protonull.addressbook.api.IMenu;
import io.protonull.addressbook.utilities.ConsoleUtilities;

public class EditContactMenu implements IMenu {

    private static final String setFirstNameCommand = "SET FIRST_NAME";

    private IContactEntry entry;

    public EditContactMenu(IContactEntry entry) {
        this.entry = entry;
    }

    @Override
    public String[] getDisplayText() {
        String firstName = this.entry.getFirstName();
        String otherNames = this.entry.getOtherNames();
        String email = this.entry.getEmailAddress();
        String phone = this.entry.getPhoneNumber();
        IContractAddress address = this.entry.getAddress();
        return new String[] {
                "Current Contact:-",
                null,
                "First Name: " + (firstName == null || firstName.isEmpty() ? "Not set." : firstName),
                "Other Names: " + (otherNames == null || otherNames.isEmpty() ? "None set." : otherNames),
                "Email: " + (email == null || email.isEmpty() ? "Not set." : email),
                "Phone: " + (phone == null || phone.isEmpty() ? "Not set." : phone),
                "Address: " + (address == null ? "Not set." : address.toString()),
                null,
                "Available commands: SET [FIRST_NAME, OTHER_NAMES, EMAIL, PHONE, ADDRESS], DONE, BACK"
        };
    }

    @Override
    public String getRequestText() {
        return "Please enter a command:";
    }

    @Override
    public void handleCommand(String command) {
        if (command == null) {
            return;
        }
        String normaliseCommand = command.toUpperCase();
        switch (normaliseCommand) {
            case "DONE":
                Program.addressBook.getEntries().remove(this.entry);
                Program.addressBook.getEntries().add(this.entry);
                ConsoleUtilities.printLine("Added this contact to the address book.");
            case "BACK":
                Program.gotoMainMenu();
                return;
        }
        if (normaliseCommand.startsWith(setFirstNameCommand)) {
            String firstName = command.substring(setFirstNameCommand.length() + 1);
            if (firstName.isEmpty()) {
                this.entry.setFirstName(null);
                ConsoleUtilities.printLine("Cleared this contact of its first name.");
            }
            else {
                this.entry.setFirstName(firstName);
                ConsoleUtilities.printLine("Set the contact's first name to: " + firstName);
            }
            return;
        }
        // TODO: Add setters for the other pieces of information
    }

}
