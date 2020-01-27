package com.example.croesus.views.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.croesus.R;
import com.example.croesus.model.entities.Question;

import java.util.List;

public class QuestionsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //pull the int from navigation
        int surveyId = QuestionsFragmentArgs.fromBundle(getArguments()).getSurveyId();

        QuestionViewModelFactory questionViewModelFactory = new QuestionViewModelFactory(getActivity().getApplication(), surveyId);
        QuestionViewModel questionViewModel = ViewModelProviders.of(this, questionViewModelFactory).get(QuestionViewModel.class);

        View root = inflater.inflate(R.layout.fragment_questions, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.question_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final QuestionsAdapter questionsAdapter = new QuestionsAdapter(getContext(), questionViewModel);
        recyclerView.setAdapter(questionsAdapter);


        questionViewModel.getQuestions().observe(getActivity(), new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                questionsAdapter.setQuestions(questions);
            }
        });

        return root;
    }
}
