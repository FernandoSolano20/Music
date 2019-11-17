package com.musica.bl.Musican;

import com.musica.bl.Person;

public abstract class Musican  extends Person {
    private Country country;
    private int old;

    public Muiscan(int id, String name, String lastName, Country country, int old) {
        super(id, name, lastName);
        this.country = country;
        this.old = old;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }
}
