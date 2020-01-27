package com.example.croesus.views.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.croesus.R;
import com.example.croesus.model.entities.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveysAdapter extends RecyclerView.Adapter<SurveysAdapter.SurveyHolder> {

    private final Context mContext;
    private final SurveysFragment mFragment;
    private List<Survey> surveys = new ArrayList<>();
    private RecyclerViewClickListener mListener;


    public SurveysAdapter(Context context, SurveysFragment fragment, RecyclerViewClickListener listener) {
        this.mContext = context;
        this.mFragment = fragment;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public SurveyHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.survey_item, parent, false);

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return new SurveyHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyHolder holder, int position) {
        Survey survey = surveys.get(position);

        holder.surveyTitle.setText(survey.getName());
        holder.surveyDescription.setText(survey.getDescription());
    }

    @Override
    public int getItemCount() {
        return surveys.size();
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
        notifyDataSetChanged();
    }

    class SurveyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView surveyTitle;
        private TextView surveyDescription;
        private RecyclerViewClickListener listener;

        public SurveyHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            this.surveyTitle = itemView.findViewById(R.id.survey_title);
            this.surveyDescription = itemView.findViewById(R.id.survey_description);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }

}
