package com.musica.bl.User.Client;

import com.musica.bl.User.IUser;
import com.musica.bl.User.User;

public class Client extends User implements IUser {
    private int old;
    private String country;

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

    @Override
    public String toString() {
        return "Client{" +
                "" + super.toString() +
                "old=" + old +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Client)) return false;
        Client client = (Client) o;
        return super.equals(client);
    }
}
