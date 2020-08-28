package com.sbnri.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.sbnri.Database.FeedDao;
import com.sbnri.Database.FeedRoomDatabase;
import com.sbnri.data.model.RepoListItem;
import java.util.List;


public class DataRepository {
    private static DataRepository Instance = null;
    private LiveData<List<RepoListItem>> savedData;
    private FeedDao travelDao;


    public DataRepository(Application application) {
        FeedRoomDatabase db = FeedRoomDatabase.getDatabase(application);
        travelDao = db.dataDao();
        savedData = travelDao.getAllSavedData();

    }

    public static DataRepository getInstance(Application application) {
        if (Instance == null)
            Instance = new DataRepository(application);
        return Instance;
    }

    public LiveData<List<RepoListItem>> getSavedData() {
        return savedData;
    }
}
