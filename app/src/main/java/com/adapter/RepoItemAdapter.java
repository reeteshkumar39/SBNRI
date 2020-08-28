package com.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.sbnri.R;
import com.sbnri.data.model.RepoListItem;
import com.viewholder.RepoViewHolder;


public class RepoItemAdapter extends PagedListAdapter<RepoListItem, RepoViewHolder> {

    private Context mCtx;

    public RepoItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.component_git_repo, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        RepoListItem repoListItem = getItem(position);
        if (repoListItem != null) {
            holder.addData(mCtx, repoListItem);
        }

    }

    private static DiffUtil.ItemCallback<RepoListItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<RepoListItem>() {
                @Override
                public boolean areItemsTheSame(RepoListItem oldItem, RepoListItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(RepoListItem oldItem, RepoListItem newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
