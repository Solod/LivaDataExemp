package com.solodilov.lifecycle.adapters;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.solodilov.lifecycle.api.ApiManager;
import com.solodilov.lifecycle.model.SearchRequests;

import java.util.List;

public class OldListSource extends PositionalDataSource<SearchRequests> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<SearchRequests> callback) {
        List<SearchRequests> list = ApiManager.getInstance().getListPagin(params.requestedStartPosition, params.pageSize);
        callback.onResult(list,0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<SearchRequests> callback) {
        List<SearchRequests> list = ApiManager.getInstance().getListPagin(params.startPosition, params.loadSize);
        callback.onResult(list);
    }
}
