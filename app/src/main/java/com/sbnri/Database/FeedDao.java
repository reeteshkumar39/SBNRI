package com.sbnri.Database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sbnri.data.model.RepoListItem;

import java.util.List;

@Dao
public interface FeedDao {

    @Query("SELECT * from sbnri_table")
    LiveData<List<RepoListItem>> getAllSavedData();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<RepoListItem> repoListItemList);

    @Query("SELECT * FROM sbnri_table")
    DataSource.Factory<Integer, RepoListItem> getAllPagedData();

}

