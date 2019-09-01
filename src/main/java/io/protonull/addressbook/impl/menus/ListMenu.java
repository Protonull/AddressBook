package io.protonull.addressbook.impl.menus;

import io.protonull.addressbook.Program;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.api.IContractAddress;
import io.protonull.addressbook.api.IMenu;

public class ListMenu implements IMenu {

    private IMenu next = this;
    private int currentIndex = 0;

    @Override
    public IMenu getNextMenu() {
        return this.next;
    }

    @Override
    public String[] getDisplayText() {
        IContactEntry entry = Program.addressBook.getEntries().get(currentIndex);
        String firstName = entry.getFirstName();
        String otherNames = entry.getOtherNames();
        String email = entry.getEmailAddress();
        String phone = entry.getPhoneNumber();
        IContractAddress address = entry.getAddress();
        return new String[] {
                "Current Contact In List:-",
                "First Name: " + (firstName == null || firstName.isEmpty() ? "Not set." : firstName),
                "Other Names: " + (otherNames == null || otherNames.isEmpty() ? "None set." : otherNames),
                "Email: " + (email == null || email.isEmpty() ? "Not set." : email),
                "Phone: " + (phone == null || phone.isEmpty() ? "Not set." : phone),
                "Address: " + (address == null ? "Not set." : address.toString()),
                "Please enter a command: BACK, REMOVE, EDIT, NEXT, PREV"
        };
    }

    @Override
    public void handleCommand(String command) {
        if (command.equalsIgnoreCase("BACK")) {
            this.next = new MainMenu();
        }
        else if (command.equalsIgnoreCase("DELETE") || command.equalsIgnoreCase("REMOVE")) {
            Program.addressBook.getEntries().remove(currentIndex);
            System.out.println("Removed that entry from the address book.");
        }
        else if (command.equalsIgnoreCase("EDIT")) {
            this.next = new EditContactMenu(Program.addressBook.getEntries().get(this.currentIndex));
        }
        else if (command.equalsIgnoreCase("PREV")) {
            if (--this.currentIndex < 0) {
                this.currentIndex = Math.max(Program.addressBook.getEntries().size() - 1, 0);
            }
        }
        else if (command.equalsIgnoreCase("NEXT")) {
            if (++this.currentIndex >= Program.addressBook.getEntries().size()) {
                this.currentIndex = 0;
            }
        }
    }

}
