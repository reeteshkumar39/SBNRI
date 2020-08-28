package com.sbnri.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import com.sbnri.data.model.RepoListItem;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, RepoListItem>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, RepoListItem>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

}
