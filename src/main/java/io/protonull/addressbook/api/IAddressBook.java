package io.protonull.addressbook.api;

import java.io.File;
import java.util.List;

public interface IAddressBook {

    List<IContactEntry> getEntries();

    void setLocation(File location);

    void load();

    void save();

}
