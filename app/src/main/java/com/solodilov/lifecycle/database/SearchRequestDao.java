package com.solodilov.lifecycle.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.solodilov.lifecycle.model.SearchRequests;

import java.util.List;

@Dao
public interface SearchRequestDao {

    @Query("SELECT * FROM foresmatic ORDER BY id DESC")
    LiveData<List<SearchRequests>> getOldRequest();

    @Query("SELECT * FROM foresmatic ORDER BY id DESC")
    DataSource.Factory<Integer, SearchRequests> concertsByDate();

    @Query("SELECT * FROM foresmatic WHERE id=:id ORDER BY id DESC")
    SearchRequests getOldRequestById(long id);

    @Insert
    void setNewForesmatic(SearchRequests value);
}
