package io.protonull.addressbook.utilities;

public final class StringUtilities {

    public static boolean isSimilar(String former, String latter) {
        if (former.equalsIgnoreCase(latter)) {
            return true;
        }
        if (former.contains(latter)) {
            return true;
        }
        return false;
    }

    public static <T> String getString(T value) {
        if (value == null) {
            return null;
        }
        else if (value instanceof String) {
            return (String) value;
        }
        else if (value instanceof Number) {
            return value + "";
        }
        else {
            return null;
        }
    }

}
