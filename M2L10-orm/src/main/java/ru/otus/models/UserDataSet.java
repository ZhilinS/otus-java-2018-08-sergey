package ru.otus.models;

public final class UserDataSet extends DataSet {

    private long id;
    private String name;
    private int age;

    public UserDataSet(
        final long id,
        final String name,
        final int age
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public int age() {
        return this.age;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof UserDataSet)) {
            return false;
        }
        final UserDataSet from = (UserDataSet) obj;
        return from.age() == this.age
            && from.name().equals(this.name)
            && from.id() == this.id;
    }

    @Override
    public String toString() {
        return String.format(
            "User: id=%d, name=%s, age=%s",
            this.id,
            this.name,
            this.age
        );
    }
}
