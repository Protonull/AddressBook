package io.protonull.addressbook.api;

public interface IContactEntry {

    String getFirstName();

    String getOtherNames();

    String getEmailAddress();

    String getPhoneNumber();

    IContractAddress getAddress();

    void setFirstName(String firstName);

    void setOtherNames(String otherNames);

    void setEmailAddress(String emailAddress);

    void setPhoneNumber(String phoneNumber);

    void setAddress(IContractAddress address);

}
