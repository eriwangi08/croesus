package com.example.croesus.model.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.croesus.model.builders.CroesusDatabase;
import com.example.croesus.model.daos.SurveyDao;
import com.example.croesus.model.entities.Survey;

import java.util.List;

public class SurveyRepository {
    private SurveyDao surveyDao;
    private LiveData<List<Survey>> surveys;

    public SurveyRepository(Application application) {
        CroesusDatabase database = CroesusDatabase.getInstance(application);
        surveyDao = database.surveyDao();

        surveys = surveyDao.getAll();
    }

    /**
     * Public api to be called by the view model to get all users in the database
     * @return
     */
    public LiveData<List<Survey>> getSurveys() {
        return surveys;
    }

    /**
     * Public api to be called by the view model to insert a user in the database
     * @param survey
     */
    public void insert(Survey survey) {
        new SurveyRepository.InsertSurveyAsyncTask(surveyDao).execute(survey);
    }

    /**
     * Public api to be called by the view model to update a user in the database
     * @param survey
     */
    public void update(Survey survey) {
        new SurveyRepository.UpdateSurveyAsyncTask(surveyDao).execute(survey);
    }

    /**
     * Public api to be called by the view model to delete a user in the database
     * @param survey
     */
    public void delete(Survey survey) {
        new SurveyRepository.DeleteSurveyAsyncTask(surveyDao).execute(survey);
    }


    /**
     * Async task to persist/insert the survey to the db
     */
    private static class InsertSurveyAsyncTask extends AsyncTask<Survey, Void, Void> {

        private SurveyDao surveyDao;

        private InsertSurveyAsyncTask(SurveyDao userDao) {
            this.surveyDao = userDao;
        }

        @Override
        protected Void doInBackground(Survey... surveys) {
            surveyDao.insert(surveys[0]);
            return null;
        }
    }

    /**
     * Async task to update the survey to the db
     */
    private static class UpdateSurveyAsyncTask extends AsyncTask<Survey, Void, Void> {

        private SurveyDao surveyDao;

        private UpdateSurveyAsyncTask(SurveyDao userDao) {
            this.surveyDao = userDao;
        }

        @Override
        protected Void doInBackground(Survey... surveys) {
            surveyDao.update(surveys[0]);
            return null;
        }
    }

    /**
     * Async task to delete the survey from the db
     */
    private static class DeleteSurveyAsyncTask extends AsyncTask<Survey, Void, Void> {

        private SurveyDao surveyDao;

        private DeleteSurveyAsyncTask(SurveyDao userDao) {
            this.surveyDao = userDao;
        }

        @Override
        protected Void doInBackground(Survey... surveys) {
            surveyDao.delete(surveys[0]);
            return null;
        }
    }
}
