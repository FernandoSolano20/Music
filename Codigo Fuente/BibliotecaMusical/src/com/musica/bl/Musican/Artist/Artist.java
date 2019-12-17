package com.musica.bl.Musican.Artist;

import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Musican;
import com.musica.bl.Person;

import java.time.LocalDate;
import java.util.Objects;

public class Artist extends Musican {
    private LocalDate born;
    private LocalDate dead;
    private String reference;
    private String description;
    private Gender gender;
    private String artist;

    public Artist(int id, String name, String lastName, String country, int old, LocalDate born, LocalDate dead, String reference, String description, Gender gender, String artist) {
        super(id,name,lastName,country,old);
        this.born = born;
        this.dead = dead;
        this.reference = reference;
        this.description = description;
        this.gender = gender;
        this.artist = artist;
    }

    public Artist(String name, String lastName, String country, int old, LocalDate born, LocalDate dead, String reference, String description, Gender gender, String artist) {
        super(name, lastName, country, old);
        this.born = born;
        this.dead = dead;
        this.reference = reference;
        this.description = description;
        this.gender = gender;
        this.artist = artist;
    }

    public Artist(int id){
        super(id);
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
        return super.toString() + ","
                + born +
                "," + dead +
                "," + reference +
                "," + description +
                "," + gender +
                "," + artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist1 = (Artist) o;
        return  Objects.equals(born, artist1.born) &&
                Objects.equals(dead, artist1.dead) &&
                Objects.equals(reference, artist1.reference) &&
                Objects.equals(description, artist1.description) &&
                Objects.equals(gender, artist1.gender) &&
                Objects.equals(artist, artist1.artist);
    }
}
