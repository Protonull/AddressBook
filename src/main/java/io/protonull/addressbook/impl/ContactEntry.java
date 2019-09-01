package io.protonull.addressbook.impl;

import io.protonull.addressbook.api.IContactEntry;
import io.protonull.addressbook.api.IContractAddress;

public class ContactEntry implements IContactEntry {

    private String firstName = null;
    private String otherNames = null;
    private String emailAddress = null;
    private String phoneNumber = null;
    private IContractAddress address = null;

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getOtherNames() {
        return this.otherNames;
    }

    @Override
    public String getEmailAddress() {
        return this.emailAddress;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public IContractAddress getAddress() {
        return this.address;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setAddress(IContractAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Contact[");
        if (this.firstName == null || this.firstName.isEmpty()) {
            builder.append("NO_FIRST_NAME");
        }
        else {
            builder.append(this.firstName);
        }
        builder.append(",");
        if (this.otherNames == null || this.otherNames.isEmpty()) {
            builder.append("NO_OTHER_NAMES");
        }
        else {
            builder.append(this.otherNames);
        }
        builder.append(",");
        if (this.emailAddress == null || this.emailAddress.isEmpty()) {
            builder.append("NO_EMAIL");
        }
        else {
            builder.append(this.emailAddress);
        }
        builder.append(",");
        if (this.phoneNumber == null || this.phoneNumber.isEmpty()) {
            builder.append("NO_PHONE");
        }
        else {
            builder.append(this.phoneNumber);
        }
        builder.append(",");
        if (this.address == null) {
            builder.append("NO_ADDRESS");
        }
        else {
            builder.append(this.address);
        }
        builder.append("]");
        return builder.toString();
    }

}
