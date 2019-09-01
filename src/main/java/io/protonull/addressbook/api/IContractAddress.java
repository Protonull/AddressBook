package io.protonull.addressbook.api;

public interface IContractAddress {

    String getStreet();

    String getTown();

    String getCountry();

    void setStreet(String street);

    void setTown(String town);

    void setCountry(String country);

}
