package com.example.croesus.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.croesus.model.builders.CroesusDatabase;
import com.example.croesus.model.daos.UserDao;
import com.example.croesus.model.entities.User;

import java.util.List;

/**
 * This class is what the view model uses to interact with the data. The vm does not
 * care about where the data is from, whether from the db or from another api
 */

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> userList;

    public UserRepository(Application application) {
        CroesusDatabase database = CroesusDatabase.getInstance(application);
        userDao = database.userDao();

        userList = userDao.getAllUsers();
    }

    /**
     * Public api to be called by the view model to get all users in the database
     * @return
     */
    public LiveData<List<User>> getUserList() {
        return userList;
    }

    /**
     * Public api to be called by the view model to insert a user in the database
     * @param user
     */
    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    /**
     * Public api to be called by the view model to update a user in the database
     * @param user
     */
    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    /**
     * Public api to be called by the view model to delete a user in the database
     * @param user
     */
    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    /**
     * Public api to be called by the view model to insert a user in the database
     */
    public void deleteAllUsers() {
        new DeleteUsersAsyncTask(userDao).execute();
    }


    /**
     * Async task to persist/insert the user to the db
     */
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    /**
     * Async task to update the user to the db
     */
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... notes) {
            userDao.update(notes[0]);
            return null;
        }
    }

    /**
     * Async task to delete the user to the db
     */
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... notes) {
            userDao.insert(notes[0]);
            return null;
        }
    }

    /**
     * Async task to delete all users in the db
     */
    private static class DeleteUsersAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;

        private DeleteUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
