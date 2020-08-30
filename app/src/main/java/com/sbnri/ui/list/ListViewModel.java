package com.sbnri.ui.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.sbnri.data.model.RepoListItem;
import com.sbnri.repository.DataRepository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    LiveData<PagedList<RepoListItem>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, RepoListItem>> liveDataSource;
    private LiveData<List<RepoListItem>> apiResponseLiveData;
    private DataRepository dataRepository;


    public final LiveData<PagedList<RepoListItem>> pagedList;

    public ListViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        apiResponseLiveData = dataRepository.getSavedData();
        pagedList = dataRepository.getPagedList();
        //loadingState = travelDataRepository.getLoadingState();
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
    public LiveData<List<RepoListItem>> getApiResponseLiveData() {
        return apiResponseLiveData;
    }

    public LiveData<PagedList<RepoListItem>> getPagedList() {
        return pagedList;
    }
}
