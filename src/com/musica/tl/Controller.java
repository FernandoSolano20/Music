package com.musica.tl;

import com.musica.bl.BibliotecaMusical;
import com.musica.bl.Country.Country;
import com.musica.bl.Gender.Gender;
import com.musica.bl.Musican.Artist.Artist;
import com.musica.bl.Song.Song;
import com.musica.bl.User.Admin.Admin;
import com.musica.bl.User.Client.Client;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class Controller {

    BibliotecaMusical logic = new BibliotecaMusical();

    public boolean registerClient(int id, String name, String lastName, int old, String country, String email, String pass, String userName, String image){
        Client client = new Client(id,userName,name,lastName,email,pass,image,old,country);
        return logic.registerUser(client);
    }

    public List<String> getAllUser(){
        ArrayList<String> result = (ArrayList<String>)logic.getAllUser();
        for (String item:result) {
            System.out.println(item);
        }
        return result;
    }

    public boolean isAdminOnDB(){
        return logic.isAdminOnDB();
    }

    public boolean registerAdmin(int id, String name, String lastName, String email, String pass, String userName, String image) {
        Admin admin = new Admin(id,userName,name,lastName,email,pass,image);
        return logic.registerUser(admin);
    }

    public boolean login(String email, String pass) {
        return logic.login(email,pass);
    }

    public boolean validateYears(int old){
        return logic.validateYears(old);
    }

    public boolean validatePassword(String pass){
        return logic.validatePassword(pass);
    }

    public boolean registerSong(String name, String gender, String artist, String compositor, int year, int month, int day, String album, int score, String pathSong) {
        //Song song = new Song(0,name,gender,artist,compositor, LocalDate.of(year,month,day),album,score,pathSong);
        //return logic.registerSong(song);
        return false;
    }

    public boolean registerGender(String name, String description) {
        Gender gender = new Gender(name,description);
        return logic.registerGender(gender);
    }

    public boolean registerArtist(String name, String lastName, String country, LocalDate dateBorn, LocalDate dateDead, String reference, String description, String gender, String artistName) {
        Gender gen = logic.searchGenderByName(gender);
        Country coun = logic.searchCountryByName(country);
        if(gen != null && coun != null){
            LocalDate actual = dateDead != null ? dateDead : LocalDate.now();
            int old = (int)YEARS.between(dateBorn, actual);
            Artist artist = new Artist(name,lastName,coun,old,dateBorn,dateDead,reference,description,gen,artistName);
            return logic.registerArtist(artist);
        }
        return false;
    }
}
