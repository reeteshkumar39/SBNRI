package com.sbnri.ui.list;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.adapter.RepoItemAdapter;
import com.sbnri.R;
import com.sbnri.base.BaseApplication;
import com.sbnri.base.BaseFragment;
import com.sbnri.data.model.RepoListItem;
import com.sbnri.databinding.FragmentListBinding;
import com.sbnri.util.InternetConnection;

import java.util.List;

public class ListFragment extends BaseFragment {

    private ListViewModel listViewModel;
    FragmentListBinding binding;
    RepoItemAdapter adapter;
    @Override
    protected int layoutRes() {
        return R.layout.fragment_list;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater,container, false);
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        View view = binding.getRoot();
        binding.setLifecycleOwner(getActivity());
        binding.setListViewModel(listViewModel);
        initialization(view);
        getFeeds();
        return view;
    }

    private void getFeeds() {
        if (InternetConnection.checkConnection(BaseApplication.get())) {
            listViewModel.itemPagedList.observe(getViewLifecycleOwner(), new Observer<PagedList<RepoListItem>>() {
                @Override
                public void onChanged(PagedList<RepoListItem> repoListItems) {
                    adapter.submitList(repoListItems);
                    binding.progressBar.setVisibility(View.GONE);
                }
            });
        } else {
            listViewModel.getApiResponseLiveData().observe(getViewLifecycleOwner(), new Observer<List<RepoListItem>>() {
                @Override
                public void onChanged(@Nullable List<RepoListItem> locationData) {
                    if (locationData != null) {
                        Toast.makeText(getContext(),locationData.get(0).getName(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }



    }

    private void initialization(View view) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.feedListView.setLayoutManager(layoutManager);
        adapter = new RepoItemAdapter(getActivity());
        binding.feedListView.setAdapter(adapter);

    }


}
