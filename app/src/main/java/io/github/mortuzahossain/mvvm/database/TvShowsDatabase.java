package io.github.mortuzahossain.mvvm.database;
/*
 * Created by mortuza on 4/2/21 | 7:51 PM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.github.mortuzahossain.mvvm.dao.TvShowsDao;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;

@Database(entities = {TvShowsItem.class},version = 1,exportSchema = false)
public abstract class TvShowsDatabase extends RoomDatabase {
    private static TvShowsDatabase database;

    public static synchronized TvShowsDatabase getTvShowsDatabase(Context context){
        if (database == null){
            database = Room.databaseBuilder(context,TvShowsDatabase.class,"tvshowsdb").build();
        }
        return database;
    }

    public abstract TvShowsDao getTvShowsDao();
}
