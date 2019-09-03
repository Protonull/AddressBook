package io.protonull.addressbook.api;

import io.protonull.addressbook.utilities.ConsoleUtilities;

public interface IMenu {

    String[] getDisplayText();

    String getRequestText();

    void handleCommand(String command);

    default void process() {
        handleCommand(ConsoleUtilities.readLine());
    }

}
