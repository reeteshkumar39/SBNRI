package com.sbnri.retrofit;
import com.sbnri.data.model.RepoListItem;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("orgs/octokit/repos")
    Call<List<RepoListItem>> getFeeds(
            @Query("per_page") int limit,
            @Query("page") int pageNo

    );


}
