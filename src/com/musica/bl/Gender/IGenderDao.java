package com.musica.bl.Gender;

import com.musica.bl.Dao;

import java.util.List;

public interface IGenderDao extends Dao<Gender> {
    Gender searchGenderByName(String name);
}
