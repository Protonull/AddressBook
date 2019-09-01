package io.protonull.addressbook.impl;

import io.protonull.addressbook.api.IContractAddress;

public class ContactAddress implements IContractAddress {

    private String street = null;
    private String town = null;
    private String country = null;

    @Override
    public String getStreet() {
        return this.street;
    }

    @Override
    public String getTown() {
        return this.town;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Address[");
        if (this.street == null || this.street.isEmpty()) {
            builder.append("NO_STREET");
        }
        else {
            builder.append(this.street);
        }
        builder.append(",");
        if (this.town == null || this.town.isEmpty()) {
            builder.append("NO_TOWN");
        }
        else {
            builder.append(this.town);
        }
        builder.append(",");
        if (this.country == null || this.country.isEmpty()) {
            builder.append("NO_COUNTRY");
        }
        else {
            builder.append(this.country);
        }
        builder.append("]");
        return builder.toString();
    }

}
