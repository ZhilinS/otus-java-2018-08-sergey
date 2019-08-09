/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class PhoneDataSet extends HiberDataSet {

    @Column(name = "number")
    private String number;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private HiberUserDataSet person;

    public PhoneDataSet() {
    }

    public PhoneDataSet(
        final String number,
        final HiberUserDataSet person
    ) {
        this.number = number;
        this.person = person;
    }

    public String number() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public HiberUserDataSet getPerson() {
        return person;
    }

    public void setPerson(final HiberUserDataSet person) {
        this.person = person;
    }
}
