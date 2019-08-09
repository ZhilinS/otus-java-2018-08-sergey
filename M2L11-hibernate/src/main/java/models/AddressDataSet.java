/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressDataSet extends HiberDataSet {

    @Column(name = "address")
    private String address;

    @OneToOne
    private HiberUserDataSet user;

    public AddressDataSet() { }

    public AddressDataSet(
        final String address
    ) {
        this.address = address;
    }

    public String address() {
        return this.address;
    }
}
