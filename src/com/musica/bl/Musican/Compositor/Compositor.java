package com.musica.bl.Musican.Compositor;

import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Musican;
import com.musica.bl.Person;

import java.util.ArrayList;
import java.util.Objects;

public class Compositor extends Musican {

    private ArrayList<Gender> genders = new ArrayList<Gender>();

    public Compositor(int id, String name, String lastName, String country, int old) {
        super(id, name, lastName, country, old);
    }

    public Compositor(String name, String lastName, String country, int old) {
        super(name, lastName, country, old);
    }

    public Compositor(int id) {
        super(id);
    }

    public ArrayList<Gender> getGenders() {
        return genders;
    }

    public void setGenders(Gender gender) {
        this.genders.add(gender);
    }

    @Override
    public String toString() {
        return "Compositor{" +
                "genders=" + genders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Compositor that = (Compositor) o;
        return Objects.equals(genders, that.genders);
    }
}
