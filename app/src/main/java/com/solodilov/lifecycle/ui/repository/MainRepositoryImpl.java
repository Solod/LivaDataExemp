package com.solodilov.lifecycle.ui.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.solodilov.lifecycle.LifeCycleApplication;
import com.solodilov.lifecycle.api.ApiManager;
import com.solodilov.lifecycle.database.AppDatabase;
import com.solodilov.lifecycle.model.SearchRequests;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepositoryImpl implements MainRepository {

    private AppDatabase db = LifeCycleApplication.getInstance().getDatabase();

    @Override
    public LiveData<List<SearchRequests>> getAllRequest() {
        return db.searchRequestDao().getOldRequest();
    }

    @Override
    public void requestForesmatic(MutableLiveData<SearchRequests> mForesmatic) {
        ApiManager.getInstance().getForesmatic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchRequests -> {
                            mForesmatic.setValue(searchRequests);
                        },
                        err -> Log.d("ERROR", err.getMessage()));
    }

    @Override
    public void setNewForesmatic(LiveData<SearchRequests> mForesmatic) {
        new Thread(() -> db.searchRequestDao().setNewForesmatic(mForesmatic.getValue())).start();
    }

    @Override
    public SearchRequests getSearchItem(long id) {
        return db.searchRequestDao().getOldRequestById(id);
    }
}
