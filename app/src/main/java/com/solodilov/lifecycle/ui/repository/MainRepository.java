package com.solodilov.lifecycle.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.solodilov.lifecycle.model.SearchRequests;

import java.util.List;

public interface MainRepository {
    LiveData<List<SearchRequests>> getAllRequest();

    void requestForesmatic(MutableLiveData<SearchRequests> mForesmatic);

    void setNewForesmatic(LiveData<SearchRequests> mForesmatic);

    SearchRequests getSearchItem(long id);
}
