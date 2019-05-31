package com.solodilov.lifecycle.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.solodilov.lifecycle.model.SearchRequests;

import io.reactivex.Single;


public interface MainRepository {

    Single<SearchRequests> requestForesmatic();

    void setNewForesmatic(LiveData<SearchRequests> mForesmatic);


    DataSource.Factory<Integer, SearchRequests> getPagedSearchRequest();
}
