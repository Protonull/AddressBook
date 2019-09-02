package io.protonull.addressbook.api;

public interface IMenu {

    String[] getDisplayText();

    String getRequestText();

    void handleCommand(String command);

}
