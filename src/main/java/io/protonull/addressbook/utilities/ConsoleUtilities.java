package io.protonull.addressbook.utilities;

import java.io.IOException;

public final class ConsoleUtilities {

    public static void clear() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            catch (InterruptedException | IOException e) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("\r\n");
                }
            }
        }
        else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

}
