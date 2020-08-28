package com.viewholder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sbnri.R;
import com.sbnri.components.RepoListView;
import com.sbnri.data.model.RepoListItem;
import com.sbnri.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoViewHolder extends RecyclerView.ViewHolder {
    Context context;
    @BindView(R.id.feedPost)
    public RepoListView repoListViewView;

    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void addData(Context context, RepoListItem feedData) {
        this.context = context;

        if (CommonUtil.isString(feedData.getName())) {
            repoListViewView.setTitle(feedData.getName());

        }
        if (CommonUtil.isString(feedData.getDescription())) {
            repoListViewView.setDescription(feedData.getDescription());

        }
        if (feedData.getLicense()!=null) {
            repoListViewView.setLicense(feedData.getLicense().getName());

        }
        if (feedData.getPermissions()!=null)
        repoListViewView.setPermissions(feedData.getPermissions().getAdmin());
        repoListViewView.setIssueCount(feedData.getOpenIssuesCount());

    }


}

