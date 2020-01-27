package com.example.croesus.model.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.croesus.model.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM users") //this is used to delete all users
    void deleteAllUsers();

    @Query("SELECT * FROM users") // get all users in the db
    LiveData<List<User>> getAllUsers();
}
