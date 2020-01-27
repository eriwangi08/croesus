package com.example.croesus.views.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.croesus.model.entities.Question;
import com.example.croesus.model.repositories.QuestionRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private LiveData<List<Question>> questions;

    /**
     * Constructor for the question view model. To be called in either a fragment or activity
     *
     * @param application Application passed from the calling activity/fragment
     */
    public QuestionViewModel(@NonNull Application application, int id) {
        super(application);

        questionRepository = new QuestionRepository(application, id);
        questions = questionRepository.getQuestions();
    }


    /**
     * Insert method called from the activity with no knowledge of any db functions
     *
     * @param question
     */
    public void insert(Question question) {
        questionRepository.insert(question);
    }

    /**
     * Update method called from the activity with no knowledge of any db functions
     *
     * @param question
     */
    public void update(Question question) {
        questionRepository.update(question);
    }

    /**
     * Delete method called from the activity with no knowledge of any db functions
     *
     * @param question
     */
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    /**
     * Get all questions in the database
     *
     * @return
     */
    public LiveData<List<Question>> getQuestions() {
        return questions;
    }
}
