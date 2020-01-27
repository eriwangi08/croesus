package com.example.croesus.views.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.croesus.R;
import com.example.croesus.model.entities.Survey;

import java.util.List;

public class SurveysFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SurveyViewModel surveysViewModel = ViewModelProviders.of(this).get(SurveyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_surveys, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.survey_recycler_view);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        final SurveysAdapter surveysAdapter = new SurveysAdapter(getContext(), this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                // this is a feature of android navigations. Last implementation file in the build.gradle file
                SurveysFragmentDirections.OpenQuestions questions = SurveysFragmentDirections.openQuestions();
                questions.setSurveyId(position);
                Navigation.findNavController(view).navigate(questions);
            }
        });
        recyclerView.setAdapter(surveysAdapter);


        surveysViewModel.getSurveys().observe(getActivity(), new Observer<List<Survey>>() {
            @Override
            public void onChanged(List<Survey> surveys) {
                surveysAdapter.setSurveys(surveys);
            }
        });

        return root;
    }
}