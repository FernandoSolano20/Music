package com.musica.bl;

import com.musica.bl.Country.Country;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Gender.GenderDao;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Musican.Artist.ArtistDao;
import com.musica.bl.Song.Song;
import com.musica.bl.Song.SongDao;
import com.musica.bl.User.User;
import com.musica.bl.User.UserDao;
import com.musica.dl.DataAccess;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaMusical {
    private DataAccess dl = new DataAccess();
    private ArrayList<Gender> genders = new ArrayList<>();
    private ArrayList<Country> countries = new ArrayList<>();
    Dao dao;

    public boolean registerUser(User user){
        dao = new UserDao();
        return dao.save(user);
    }

    public List<String> getAllUser(){
        dao = new UserDao();
        ArrayList<String> result = new ArrayList<>();
        List<User> users = dao.getAll();
        if(users.size() > 0){
            for (User user:users) {
                result.add(user.toString());
            }
        }
        else {
            result.add("No hay usuarios");
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return new UserDao().isAdminOnDB();
    }

    public boolean login(String email, String pass) {
        return new UserDao().login(email,pass);
    }

    public boolean validateYears(int old) {
        return old <= 18;
    }

    public boolean validatePassword(String pass) {
        return true;
    }

    public boolean registerSong(Song song) {
        dao = new SongDao();
        return dao.save(song);
    }

    public boolean registerGender(Gender gender) {
        dao = new GenderDao();
        return dao.save(gender);
    }

    public boolean registerArtist(Artist artist) {
        dao = new ArtistDao();
        return dao.save(artist);
    }

    public Gender searchGenderByName(String name){
        genders.add(new Gender(1,"Romance","Hola"));
        Gender gen = null;
        for (Gender gender:genders) {
            if (gender.getName().equals(name)){
                gen = gender;
                break;
            }
        }
        return gen;
    }

    public Country searchCountryByName(String name){
        countries.add(new Country("Country"));
        Country coun = null;
        for (Country country:countries) {
            if (country.getName().equals(name)){
                coun = country;
                break;
            }
        }
        return coun;
    }
}
