package com.example.croesus.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.croesus.model.builders.CroesusDatabase;
import com.example.croesus.model.daos.QuestionDao;
import com.example.croesus.model.entities.Question;

import java.util.List;

public class QuestionRepository {
    private QuestionDao questionDao;
    private LiveData<List<Question>> questions;

    public QuestionRepository(Application application, int id) {
        CroesusDatabase database = CroesusDatabase.getInstance(application);
        questionDao = database.questionDao();

        questions = questionDao.getQuestions(id);
    }

    /**
     * Public api to be called by the view model to get all users in the database
     * @return
     */
    public LiveData<List<Question>> getQuestions() {
        return questions;
    }

    /**
     * Public api to be called by the view model to insert a user in the database
     * @param question
     */
    public void insert(Question question) {
        new QuestionRepository.InsertQuestionAsyncTask(questionDao).execute(question);
    }

    /**
     * Public api to be called by the view model to update a user in the database
     * @param question
     */
    public void update(Question question) {
        new QuestionRepository.UpdateQuestionAsyncTask(questionDao).execute(question);
    }

    /**
     * Public api to be called by the view model to delete a user in the database
     * @param question
     */
    public void delete(Question question) {
        new QuestionRepository.DeleteQuestionAsyncTask(questionDao).execute(question);
    }


    /**
     * Async task to persist/insert the question to the db
     */
    private static class InsertQuestionAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao questionDao;

        private InsertQuestionAsyncTask(QuestionDao userDao) {
            this.questionDao = userDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.insert(questions[0]);
            return null;
        }
    }

    /**
     * Async task to update the question to the db
     */
    private static class UpdateQuestionAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao questionDao;

        private UpdateQuestionAsyncTask(QuestionDao userDao) {
            this.questionDao = userDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.update(questions[0]);
            return null;
        }
    }

    /**
     * Async task to delete the question from the db
     */
    private static class DeleteQuestionAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDao questionDao;

        private DeleteQuestionAsyncTask(QuestionDao userDao) {
            this.questionDao = userDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.delete(questions[0]);
            return null;
        }
    }
}
