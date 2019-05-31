package com.solodilov.lifecycle.ui.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.solodilov.lifecycle.R;
import com.solodilov.lifecycle.adapters.OldSearchAdapter;
import com.solodilov.lifecycle.bases.BaseFragment;
import com.solodilov.lifecycle.ui.view_model.MainViewModel;

import butterknife.BindView;

public class MainFragment extends BaseFragment {

    @BindView(R.id.rv_old_search_request)
    protected RecyclerView mOldSearchRv;

    private MainViewModel viewModel;
    private OldSearchAdapter mOldSearchAdapter = new OldSearchAdapter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        getLifecycle().addObserver(viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_query_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRV();
    }

    private void initRV() {
        mOldSearchRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mOldSearchRv.setAdapter(mOldSearchAdapter);
        viewModel.subscribeToData().observe(this, listSearch -> {
            Parcelable recyclerViewState = mOldSearchRv.getLayoutManager().onSaveInstanceState();
            mOldSearchAdapter.submitList(listSearch);
            mOldSearchRv.postDelayed(() -> mOldSearchRv.getLayoutManager().onRestoreInstanceState(recyclerViewState),200);
        });
    }
}

