package com.sbnri.ui.list;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.sbnri.Database.FeedDao;
import com.sbnri.Database.FeedRoomDatabase;
import com.sbnri.base.BaseApplication;
import com.sbnri.data.model.RepoListItem;
import com.sbnri.retrofit.ApiInterface;
import com.sbnri.retrofit.RetrofitApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemDataSource extends PageKeyedDataSource<Integer, RepoListItem> {

    public static final int PAGE_SIZE = 5;
    private static final int FIRST_PAGE = 1;
    public static final int STATUS_CODE = 200;
    private ApiInterface apiInterface;
    private FeedDao feedDao;

    public ItemDataSource() {
        apiInterface = RetrofitApiClient.getInstance().create(ApiInterface.class);
        FeedRoomDatabase db = FeedRoomDatabase.getDatabase(BaseApplication.get());
        feedDao = db.dataDao();

    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, RepoListItem> callback) {

        apiInterface.getFeeds(PAGE_SIZE, FIRST_PAGE).enqueue(new Callback<List<RepoListItem>>() {
            @Override
            public void onResponse(Call<List<RepoListItem>> call, Response<List<RepoListItem>> response) {
                if (response.body() != null && response.code()==STATUS_CODE) {
//                    List<FeedListItem> feedList=response.body();
                    insertData(response.body());
                    callback.onResult(response.body(), null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<List<RepoListItem>> call, Throwable t) {
                Log.e("failure", "" + t);
                Log.e("failure", "" + call);
            }
        });
    }





    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, RepoListItem> callback) {
        apiInterface.getFeeds(PAGE_SIZE, params.key).enqueue(new Callback<List<RepoListItem>>() {
            @Override
            public void onResponse(Call<List<RepoListItem>> call, Response<List<RepoListItem>> response) {
                if (response.body() != null && response.code()==STATUS_CODE) {
                    Integer key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(response.body(), key);
                }
            }

            @Override
            public void onFailure(Call<List<RepoListItem>> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, RepoListItem> callback) {
        apiInterface.getFeeds(PAGE_SIZE, params.key).enqueue(new Callback<List<RepoListItem>>() {
            @Override
            public void onResponse(Call<List<RepoListItem>> call, Response<List<RepoListItem>> response) {
                if (response.body() != null && response.code()==STATUS_CODE) {
                    Integer key = response.body().size() > 0 ? params.key + 1 : null;
                    insertData(response.body());
                    callback.onResult(response.body(), key);
                }
            }

            @Override
            public void onFailure(Call<List<RepoListItem>> call, Throwable t) {
                Log.e("failure", "" + t);
            }
        });
    }
    public void insertData(List<RepoListItem> repoListItems) {

         new insertAsyncTask(feedDao).execute(repoListItems);
    }
    private static class insertAsyncTask extends AsyncTask<List<RepoListItem>, Void, Void> {

        private FeedDao mAsyncTaskDao;

        insertAsyncTask(FeedDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<RepoListItem>[] lists) {
            mAsyncTaskDao.insert(lists[0]);
            return null;
        }
    }
}
