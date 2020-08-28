package com.sbnri.data.rest;

import com.sbnri.data.model.RepoListItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class RepoRepository {

    private final RepoService repoService;

    @Inject
    public RepoRepository(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<RepoListItem>> getRepositories(int defaultPageSize, int pageNo) {
        return repoService.getRepositories(defaultPageSize,pageNo);
    }

}
