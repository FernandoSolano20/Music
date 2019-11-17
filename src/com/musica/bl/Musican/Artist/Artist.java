package com.musica.bl.Artist;

import com.musica.bl.Person;

import java.time.LocalDate;
import java.util.Objects;

public class Artist extends Person {
    private LocalDate born;
    private LocalDate dead;
    private Country country;
    private int old;
    private String reference;
    private String description;
    private Gender gender;
    private String artist;

    public Artist(int id, String name, String lastName, LocalDate born, LocalDate dead, Country country, int old, String reference, String description, Gender gender, String artist) {
        super(id,name,lastName);
        this.born = born;
        this.dead = dead;
        this.country = country;
        this.old = old;
        this.reference = reference;
        this.description = description;
        this.gender = gender;
        this.artist = artist;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDate getDead() {
        return dead;
    }

    public void setDead(LocalDate dead) {
        this.dead = dead;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Artist{" +
                ", born=" + born +
                ", dead=" + dead +
                ", country=" + country +
                ", old=" + old +
                ", reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                ", gender=" + gender +
                ", artist='" + artist + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist1 = (Artist) o;
        return  old == artist1.old &&
                Objects.equals(born, artist1.born) &&
                Objects.equals(dead, artist1.dead) &&
                Objects.equals(country, artist1.country) &&
                Objects.equals(reference, artist1.reference) &&
                Objects.equals(description, artist1.description) &&
                Objects.equals(gender, artist1.gender) &&
                Objects.equals(artist, artist1.artist);
    }
}
