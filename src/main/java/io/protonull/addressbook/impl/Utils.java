package io.protonull.addressbook.impl;

public class Utils {

    public static boolean isSimilar(String former, String latter) {
        if (former.equalsIgnoreCase(latter)) {
            return true;
        }
        if (former.contains(latter)) {
            return true;
        }
        return false;
    }

}
