package com.musica.bl.Musican;

import com.musica.bl.Person;

import java.util.Objects;

public abstract class Musican  extends Person {
    private String country;
    private int old;

    public Musican(int id, String name, String lastName, String country, int old) {
        super(id, name, lastName);
        this.country = country;
        this.old = old;
    }

    public Musican(String name, String lastName, String country, int old) {
        super(name, lastName);
        this.country = country;
        this.old = old;
    }

    public Musican(int id) {
        super(id);
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    @Override
    public String toString() {
        return super.toString() +
                "," + country +
                "," + old;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Musican musican = (Musican) o;
        return old == musican.old &&
                Objects.equals(country, musican.country);
    }
}
