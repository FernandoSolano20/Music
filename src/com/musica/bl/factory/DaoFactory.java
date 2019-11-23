package com.musica.bl.factory;

import com.musica.bl.Album.IAlbumDao;
import com.musica.bl.Gender.IGenderDao;
import com.musica.bl.Musican.Artist.IArtistDao;
import com.musica.bl.Musican.Compositor.ICompositorDao;
import com.musica.bl.ReproductionList.IReproductionListDao;
import com.musica.bl.Song.ISongDao;
import com.musica.bl.User.Client.IClientDao;
import com.musica.bl.User.IUserDao;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory(int tipo){
        switch (tipo){
            // es MySql
            case 1:
                return new MySqlDaoFactory();

            default: return null;
        }
    }

    public abstract IUserDao getUserDao();
    public abstract IClientDao getClientDao();
    public abstract IGenderDao getGenderDao();
    public abstract ISongDao getSongDao();
    public abstract IReproductionListDao getReproductionListDao();
    public abstract IArtistDao getArtistDao();
    public abstract ICompositorDao getCompositorDao();
    public abstract IAlbumDao getAlbumDao();
}
