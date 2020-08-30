package com.sbnri.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.sbnri.Database.FeedDao;
import com.sbnri.Database.FeedRoomDatabase;
import com.sbnri.data.model.RepoListItem;
import java.util.List;


public class DataRepository {
    private static DataRepository Instance = null;
    private LiveData<List<RepoListItem>> savedData;
    private FeedDao travelDao;
    public final LiveData<PagedList<RepoListItem>> pagedList;


    public DataRepository(Application application) {
        FeedRoomDatabase db = FeedRoomDatabase.getDatabase(application);
        travelDao = db.dataDao();
        savedData = travelDao.getAllSavedData();
        pagedList = new LivePagedListBuilder<>(
                travelDao.getAllPagedData(), 50).build();


    }

    public static DataRepository getInstance(Application application) {
        if (Instance == null)
            Instance = new DataRepository(application);
        return Instance;
    }

    public LiveData<List<RepoListItem>> getSavedData() {
        return savedData;
    }

    public LiveData<PagedList<RepoListItem>> getPagedList() {
        return pagedList;
    }
}
