package com.solodilov.lifecycle.ui.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.solodilov.lifecycle.LifeCycleApplication;
import com.solodilov.lifecycle.api.ApiManager;
import com.solodilov.lifecycle.database.AppDatabase;
import com.solodilov.lifecycle.model.SearchRequests;

import io.reactivex.Single;

public class MainRepositoryImpl implements MainRepository {

    private AppDatabase db = LifeCycleApplication.getInstance().getDatabase();

    @Override
    public Single<SearchRequests> requestForesmatic() {
       return ApiManager.getInstance().getForesmatic();
    }

    @Override
    public void setNewForesmatic(LiveData<SearchRequests> mForesmatic) {
        new Thread(() -> db.searchRequestDao().setNewForesmatic(mForesmatic.getValue())).start();
    }

    @Override
    public DataSource.Factory<Integer, SearchRequests> getPagedSearchRequest() {
        return db.searchRequestDao().concertsByDate();
    }
}
