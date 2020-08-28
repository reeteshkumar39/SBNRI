package com.sbnri.data.rest;
import com.sbnri.data.model.RepoListItem;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RepoService {
    //http://orgs/octokit/repos?page=1&per_page=10
    @GET("orgs/octokit/repos")
    Single<List<RepoListItem>> getRepositories();

    @GET("orgs/octokit/repos")
    Single<List<RepoListItem>> getRepositories(
            @Query("per_page") int perPage,
            @Query("page") int page
    );
}

