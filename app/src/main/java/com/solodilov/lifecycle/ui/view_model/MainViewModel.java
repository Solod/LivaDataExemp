package com.solodilov.lifecycle.ui.view_model;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.solodilov.lifecycle.model.SearchRequests;
import com.solodilov.lifecycle.ui.repository.MainRepository;
import com.solodilov.lifecycle.ui.repository.MainRepositoryImpl;

import java.util.List;

public class MainViewModel extends ViewModel implements LifecycleObserver {

    private LiveData<List<SearchRequests>> mOldList;
    private MutableLiveData<SearchRequests> mForesmatic;
    private MainRepository repository;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        if (repository == null) {
            repository = new MainRepositoryImpl();
        }
        if (mOldList == null) {
            mOldList = repository.getAllRequest();
        }

        if(mForesmatic==null){
            mForesmatic = new MutableLiveData<>();
        }
    }

    public LiveData<List<SearchRequests>> getOldList(){
        return mOldList;
    }

    public LiveData<SearchRequests> getForesmaticItem() {
        return mForesmatic;
    }

    public void requestRandomForesmatic() {
        repository.requestForesmatic(mForesmatic);
    }

    public void setNewForesmatic() {
        repository.setNewForesmatic(mForesmatic);
    }

    public SearchRequests getSearchForFragment(long id) {
        return repository.getSearchItem(id);
    }
}
