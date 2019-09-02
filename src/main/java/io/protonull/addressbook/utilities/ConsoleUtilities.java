package io.protonull.addressbook.utilities;

import java.io.IOException;
import java.util.Scanner;

public final class ConsoleUtilities {

    public static final String LINE_BREAK = System.lineSeparator();

    private static final Scanner scanner = new Scanner(System.in);

    public static void clear() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            catch (InterruptedException | IOException e) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(LINE_BREAK);
                }
            }
        }
        else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static String readLine() {
        while (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

}
