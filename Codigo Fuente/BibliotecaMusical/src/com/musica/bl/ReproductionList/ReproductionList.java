package com.musica.bl.ReproductionList;

import com.musica.bl.Song.Song;
import com.musica.bl.User.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReproductionList {
    private int id;
    private LocalDate create;
    private String name;
    private double score;
    private User user;
    private List<Song> songs = new ArrayList<>();

    public ReproductionList(int id, LocalDate create, String name, double score, User user, List<Song> songs) {
        this.id = id;
        this.create = create;
        this.name = name;
        this.score = score;
        this.user = user;
        this.songs = songs;
    }

    public ReproductionList(int id, LocalDate create, String name, double score, User user) {
        this.id = id;
        this.create = create;
        this.name = name;
        this.score = score;
        this.user = user;
    }

    public ReproductionList(LocalDate create, String name, User user) {
        this.create = create;
        this.name = name;
        this.user = user;
    }

    public ReproductionList(int id) {
        this.id = id;
    }

    public ReproductionList() {

    }

    public ReproductionList(int id, LocalDate create, String name, double score) {
        this.id = id;
        this.create = create;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreate() {
        return create;
    }

    public void setCreate(LocalDate create) {
        this.create = create;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore() {
        int prom = 0;
        for (Song song:songs) {
           prom += song.getScore();
        }
        if(songs.size() != 0){
            prom = prom / songs.size();
        }
        this.score = prom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(Song song) {
        this.songs.add(song);
    }

    @Override
    public String toString() {
        return id +
                "," + create +
                "," + name  +
                "," + score +
                "," + user +
                "," + songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReproductionList that = (ReproductionList) o;
        return id == that.id &&
                Double.compare(that.score, score) == 0 &&
                Objects.equals(create, that.create) &&
                Objects.equals(name, that.name) &&
                Objects.equals(user, that.user);
    }

    public void replaceSong(Song song) {
        for(int i = 0; i < songs.size(); i++) {
            if(songs.get(i).getId() == song.getId()) {
                songs.set(i,song);
            }
        }
    }
}
