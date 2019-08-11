package ru.otus.dataset;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class HiberUserDataSet extends HiberDataSet {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhoneDataSet> phone = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    public HiberUserDataSet() {
    }

    public HiberUserDataSet(
        final String name,
        final int age,
        final List<PhoneDataSet> phones,
        final AddressDataSet address
    ) {
        this.name = name;
        this.age = age;
        this.phone = phones;
        this.address = address;
    }

    public String name() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int age() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public List<PhoneDataSet> phone() {
        return phone;
    }

    public void setPhone(final List<PhoneDataSet> phone) {
        this.phone = phone;
    }

    public AddressDataSet address() {
        return address;
    }

    public void setAddress(final AddressDataSet address) {
        this.address = address;
    }
}
