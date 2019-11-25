package com.musica.bl.Album;

import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Song.Song;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private String image;
    private ArrayList<Artist> artists = new ArrayList<>();
    private List<Song> songs = new ArrayList<>();

    public Album(String name, LocalDate releaseDate, String image) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.image = image;
    }

    public Album(int id, String name, LocalDate releaseDate, String image) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.image = image;
    }

    public Album(int id) {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Artist artist) {
        this.artists.add(artist);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setSongs(Song song) {
        this.songs.add(song);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", image='" + image + '\'' +
                ", artists=" + artists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id == album.id &&
                Objects.equals(name, album.name) &&
                Objects.equals(releaseDate, album.releaseDate) &&
                Objects.equals(image, album.image);
    }
}
