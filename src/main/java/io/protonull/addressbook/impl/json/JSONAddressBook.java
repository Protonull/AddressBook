package io.protonull.addressbook.impl.json;

import io.protonull.addressbook.api.IAddressBook;
import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.impl.ContactAddress;
import io.protonull.addressbook.impl.ContactEntry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONAddressBook implements IAddressBook {

    private static Logger logger = LoggerFactory.getLogger(JSONAddressBook.class);

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
            logger.warn("Cannot load address book as the file location is invalid.");
            return;
        }
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(this.fileLocation)) {
            Object json = jsonParser.parse(reader);
            if (!(json instanceof JSONArray)) {
                logger.warn("Cannot load address book as the file is malformed.");
                return;
            }
            this.entries.clear();
            for (Object rawEntry : (JSONArray) json) {
                if (!(rawEntry instanceof JSONObject)) {
                    logger.warn("The following entry is being skipped, it's not a json object:");
                    logger.warn(rawEntry.toString());
                    continue;
                }
                JSONObject entry = (JSONObject) rawEntry;
                ContactEntry contactEntry = new ContactEntry();
                // Parse First Name
                contactEntry.setFirstName(JSONParseUtilities.getString(entry.get("firstName")));
                // Parse Other Names
                contactEntry.setOtherNames(JSONParseUtilities.getString(entry.get("otherNames")));
                // Parse Email Address
                contactEntry.setEmailAddress(JSONParseUtilities.getString(entry.get("emailAddress")));
                // Parse Email Address
                contactEntry.setPhoneNumber(JSONParseUtilities.getString(entry.get("phoneNumber")));
                // Parse Address
                if (entry.containsKey("address")) {
                    Object addressRaw = entry.get("address");
                    // If address is null, just skip
                    if (addressRaw == null) {}
                    // Otherwise ensure that it's an object
                    else if (!(addressRaw instanceof JSONObject)) {
                        logger.warn("The following entry is being skipped, its address is not a JSON object.");
                        logger.warn(rawEntry.toString());
                        continue;
                    }
                    // Otherwise attempt to parse address
                    else {
                        JSONObject address = (JSONObject) addressRaw;
                        ContactAddress contactAddress = new ContactAddress();
                        contactAddress.setStreet(JSONParseUtilities.getString(address.get("street")));
                        contactAddress.setTown(JSONParseUtilities.getString(address.get("town")));
                        contactAddress.setCountry(JSONParseUtilities.getString(address.get("country")));
                        contactEntry.setAddress(contactAddress);
                    }
                }
                // Add the entry to the entry list
                this.entries.add(contactEntry);
                logger.info("The following entry has been loaded: " + contactEntry.toString());
            }
        }
        catch (FileNotFoundException error) {
            logger.info("Could not find an existing address book, is this a new one?");
            return;
        }
        catch (ParseException error) {
            error.printStackTrace();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void save() {

    }

}
