package com.sbnri.data.model;

import java.io.Serializable;
import java.util.List;

public class FeedListResponse implements Serializable {
    private List<RepoListItem> feedListResponses;

    public List<RepoListItem> getFeedListResponses() {
        return feedListResponses;
    }

    public void setFeedListResponses(List<RepoListItem> feedListRespons) {
        this.feedListResponses = feedListRespons;
    }
}
