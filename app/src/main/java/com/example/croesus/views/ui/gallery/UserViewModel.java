package com.example.croesus.views.ui.gallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.croesus.model.entities.User;
import com.example.croesus.model.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> users;

    /**
     * Constructor for the user view model. To be called in either a fragment or activity
     *
     * @param application Application passed from the calling activity/fragment
     */
    public UserViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        users = userRepository.getUserList();
    }


    /**
     * Insert method called from the activity with no knowledge of any db functions
     *
     * @param user
     */
    public void insert(User user) {
        userRepository.insert(user);
    }

    /**
     * Update method called from the activity with no knowledge of any db functions
     *
     * @param user
     */
    public void update(User user) {
        userRepository.update(user);
    }

    /**
     * Delete method called from the activity with no knowledge of any db functions
     *
     * @param user
     */
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * DeleteAll method called from the activity with no knowledge of any db functions
     */
    public void deleteAll() {
        userRepository.deleteAllUsers();
    }

    /**
     * Get all users in the database
     *
     * @return
     */
    public LiveData<List<User>> getUsers() {
        return users;
    }
}
