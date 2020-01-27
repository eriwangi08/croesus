package com.example.croesus.views.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.croesus.model.entities.Survey;
import com.example.croesus.model.repositories.SurveyRepository;

import java.util.List;

public class SurveyViewModel extends AndroidViewModel {

    private SurveyRepository surveyRepository;
    private LiveData<List<Survey>> surveys;

    /**
     * Constructor for the survey view model. To be called in either a fragment or activity
     *
     * @param application Application passed from the calling activity/fragment
     */
    public SurveyViewModel(@NonNull Application application) {
        super(application);

        surveyRepository = new SurveyRepository(application);
        surveys = surveyRepository.getSurveys();
    }


    /**
     * Insert method called from the activity with no knowledge of any db functions
     *
     * @param survey
     */
    public void insert(Survey survey) {
        surveyRepository.insert(survey);
    }

    /**
     * Update method called from the activity with no knowledge of any db functions
     *
     * @param survey
     */
    public void update(Survey survey) {
        surveyRepository.update(survey);
    }

    /**
     * Delete method called from the activity with no knowledge of any db functions
     *
     * @param survey
     */
    public void delete(Survey survey) {
        surveyRepository.delete(survey);
    }

    /**
     * Get all surveys in the database
     *
     * @return
     */
    public LiveData<List<Survey>> getSurveys() {
        return surveys;
    }
}
