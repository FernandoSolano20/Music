package com.musica.bl.User.Client;

import com.musica.bl.Song.Song;
import com.musica.bl.User.User;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private int old;
    private String country;
    private List<Song> catalog = new ArrayList<>();

    public Client(int id, String userName, String name, String lastName, String email, String pass, String image, int old, String country) {
        super(id, userName, name, lastName, email, pass, image);
        setType("Cliente");
        this.old = old;
        this.country = country;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Song> getCatalog() {
        return catalog;
    }

    public void setSongOnCatalog(Song song) {
        catalog.add(song);
    }

    @Override
    public String toString() {
        return  super.toString() +
                "," + old +
                "," + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Client)) return false;
        Client client = (Client) o;
        return super.equals(client);
    }

    public void replaceSong(Song song) {
        for(int i = 0; i < catalog.size(); i++) {
            if(catalog.get(i).getId() == song.getId()) {
                catalog.set(i,song);
            }
        }
    }
}
