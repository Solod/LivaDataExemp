package com.solodilov.lifecycle.ui.view_model;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.solodilov.lifecycle.model.SearchRequests;
import com.solodilov.lifecycle.ui.repository.MainRepository;
import com.solodilov.lifecycle.ui.repository.MainRepositoryImpl;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel implements LifecycleObserver {

    LiveData<PagedList<SearchRequests>> pagedList;
    private MutableLiveData<SearchRequests> mForesmatic;
    private MainRepository repository;

    private CompositeDisposable compositeDisposable;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        if (repository == null) {
            repository = new MainRepositoryImpl();
        }

        if (mForesmatic == null) {
            mForesmatic = new MutableLiveData<>();
        }

        if (pagedList == null) {
            pagedList = new LivePagedListBuilder<>(repository.getPagedSearchRequest(), 10).build();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        compositeDisposable.clear();
        compositeDisposable = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if(compositeDisposable==null){
            compositeDisposable = new CompositeDisposable();
        }

    }

    public LiveData<SearchRequests> getForesmaticItem() {
        return mForesmatic;
    }

    public void requestRandomForesmatic() {
        compositeDisposable.add(repository.requestForesmatic()
                .subscribeOn(Schedulers.io())
                .subscribe(foresmatic -> mForesmatic.postValue(foresmatic)));
    }

    public void setNewForesmatic() {
        repository.setNewForesmatic(mForesmatic);
    }

    public LiveData<PagedList<SearchRequests>> subscribeToData() {
        return pagedList;
    }
}
