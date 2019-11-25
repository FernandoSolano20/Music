package com.musica.bl.Song;

import com.musica.bl.Album.Album;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Musican.Compositor.Compositor;
import com.musica.bl.User.User;

import java.time.LocalDate;
import java.util.Objects;

public class Song {
    private int id;
    private String name;
    private Gender gender;
    private Artist artist;
    private Compositor compositor;
    private LocalDate release;
    private Album album;
    private int score;
    private String song;
    private User creator;
    private int price;

    public Song(String name, Gender gender, Artist artist, Compositor compositor, LocalDate release, Album album, int score, String song, User creator, int price) {
        this.name = name;
        this.gender = gender;
        this.artist = artist;
        this.compositor = compositor;
        this.release = release;
        this.album = album;
        this.score = score;
        this.song = song;
        this.creator = creator;
        this.price = price;
    }

    public Song(int id, String name, LocalDate release, int score, User creator, String song, Gender gender, Compositor compositor, Artist artist, Album album, int price) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.artist = artist;
        this.compositor = compositor;
        this.release = release;
        this.album = album;
        this.score = score;
        this.song = song;
        this.creator = creator;
        this.price = price;
    }

    public Song(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Compositor getCompositor() {
        return compositor;
    }

    public void setCompositor(Compositor compositor) {
        this.compositor = compositor;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public Album getAlbum() {
        return album;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id +
                "," + name +
                "," + gender +
                "," + artist +
                "," + compositor +
                "," + release +
                "," + album +
                "," + score +
                "," + song +
                "," + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song1 = (Song) o;
        return id == song1.id &&
                score == song1.score &&
                Objects.equals(name, song1.name) &&
                Objects.equals(gender, song1.gender) &&
                Objects.equals(artist, song1.artist) &&
                Objects.equals(compositor, song1.compositor) &&
                Objects.equals(release, song1.release) &&
                Objects.equals(album, song1.album) &&
                Objects.equals(song, song1.song);
    }
}
