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
    private MutableLiveData<Boolean> loadingState;
    public ListViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        apiResponseLiveData = dataRepository.getSavedData();
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

//    public void fetchFromWeb() {
//        travelDataRepository.getAllData();
//    }
//
//    public void updateData(FeedListItem locationData) {
//        travelDataRepository.updateData(locationData);
//    }

    public MutableLiveData<Boolean> getLoadingState() {
        return loadingState;
    }
}
