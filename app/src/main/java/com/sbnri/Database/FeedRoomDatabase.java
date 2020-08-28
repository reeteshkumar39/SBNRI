package com.sbnri.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sbnri.data.model.RepoListItem;

@Database(entities = {RepoListItem.class}, version = 1, exportSchema = false)
public abstract class FeedRoomDatabase extends RoomDatabase {
    private static FeedRoomDatabase INSTANCE;

    public static FeedRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FeedRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FeedRoomDatabase.class, "sbnri_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract FeedDao dataDao();
}
