package com.example.croesus.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.croesus.model.entities.Survey;

import java.util.List;

@Dao
public interface SurveyDao {

    @Insert
    void insert(Survey survey);

    @Update
    void update(Survey survey);

    @Delete
    void delete(Survey survey);

    @Query("SELECT * FROM surveys")
    LiveData<List<Survey>> getAll();
}
