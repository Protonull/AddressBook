package io.protonull.addressbook.impl.json;

import io.protonull.addressbook.api.IAddressBook;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.impl.ContactAddress;
import io.protonull.addressbook.impl.ContactEntry;
import io.protonull.addressbook.utilities.ConsoleUtilities;
import io.protonull.addressbook.utilities.StringUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONAddressBook implements IAddressBook {

    private List<IContactEntry> entries = new ArrayList<>();
    private File fileLocation = null;

    @Override
    public List<IContactEntry> getEntries() {
        return this.entries;
    }

    @Override
    public void setLocation(File location) {
        this.fileLocation = location;
    }

    @Override
    public void load() {
        if (this.fileLocation == null) {
            ConsoleUtilities.printWarning("Cannot load an address book as the file location is invalid.");
            return;
        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(this.fileLocation)) {
            Object json = jsonParser.parse(reader);
            if (!(json instanceof JSONArray)) {
                ConsoleUtilities.printWarning("Cannot load address book as the file is malformed.");
                return;
            }
            this.entries.clear();
            for (Object rawEntry : (JSONArray) json) {
                if (!(rawEntry instanceof JSONObject)) {
                    ConsoleUtilities.printWarning(
                            "The following entry is being skipped, it's not a json object: " + rawEntry.toString());
                    continue;
                }
                JSONObject entry = (JSONObject) rawEntry;
                ContactEntry contactEntry = new ContactEntry();
                // Parse First Name
                contactEntry.setFirstName(StringUtilities.getString(entry.get("firstName")));
                // Parse Other Names
                contactEntry.setOtherNames(StringUtilities.getString(entry.get("otherNames")));
                // Parse Email Address
                contactEntry.setEmailAddress(StringUtilities.getString(entry.get("emailAddress")));
                // Parse Email Address
                contactEntry.setPhoneNumber(StringUtilities.getString(entry.get("phoneNumber")));
                // Parse Address
                if (entry.containsKey("address")) {
                    Object addressRaw = entry.get("address");
                    // If address is null, just skip
                    if (addressRaw == null) {}
                    // Otherwise ensure that it's an object
                    else if (!(addressRaw instanceof JSONObject)) {
                        ConsoleUtilities.printWarning(
                                "The following entry is being skipped, its address is not a JSON object: " +
                                rawEntry.toString());
                        continue;
                    }
                    // Otherwise attempt to parse address
                    else {
                        JSONObject address = (JSONObject) addressRaw;
                        ContactAddress contactAddress = new ContactAddress();
                        contactAddress.setStreet(StringUtilities.getString(address.get("street")));
                        contactAddress.setTown(StringUtilities.getString(address.get("town")));
                        contactAddress.setCountry(StringUtilities.getString(address.get("country")));
                        contactEntry.setAddress(contactAddress);
                    }
                }
                // Add the entry to the entry list
                this.entries.add(contactEntry);
                ConsoleUtilities.printLine("The following entry has been loaded: " + contactEntry.toString());
            }
        }
        catch (FileNotFoundException error) {
            ConsoleUtilities.printLine("Could not find an existing address book, is this a new one?");
        }
        catch (ParseException error) {
            ConsoleUtilities.printError("Could not load that address book, there was an error in parsing.");
            error.printStackTrace();
        }
        catch (IOException error) {
            ConsoleUtilities.printError(
                    "Could not load that address book, there was an accessing or reading the file.");
            error.printStackTrace();
        }
    }

    @Override
    public void save() {

    }

}
