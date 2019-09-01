package io.protonull.addressbook.api;

public interface IMenu {

    IMenu getNextMenu();

    String[] getDisplayText();

    void handleCommand(String command);

}
