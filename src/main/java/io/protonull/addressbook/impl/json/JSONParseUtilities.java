package io.protonull.addressbook.impl.json;

public class JSONParseUtilities {

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
