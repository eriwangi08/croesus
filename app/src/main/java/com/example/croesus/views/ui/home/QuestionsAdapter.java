package com.example.croesus.views.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.croesus.R;
import com.example.croesus.model.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionHolder> {

    private final Context mContext;
    private final QuestionViewModel viewModel;
    private List<Question> questions = new ArrayList<>();


    public QuestionsAdapter(Context context, QuestionViewModel questionViewModel) {
        this.mContext = context;
        this.viewModel = questionViewModel;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        return new QuestionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionHolder holder, int position) {
        final Question question = questions.get(position);

        holder.title.setText(question.getQuestionText().toString());

        if (question.getAnswer()) {
            holder.yes.setChecked(true);
            holder.no.setChecked(false);
        } else {
            holder.yes.setChecked(false);
            holder.no.setChecked(true);
        }

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question1 = questions.get(holder.getAdapterPosition());
                boolean checked = ((RadioButton) v).isChecked();
                if (checked) {
                    question1.setAnswer(true);
                }

                viewModel.update(question);
                notifyDataSetChanged();

                Toast.makeText(mContext, "Question updated", Toast.LENGTH_SHORT).show();
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question1 = questions.get(holder.getAdapterPosition());
                boolean checked = ((RadioButton) v).isChecked();
                if (checked) {
                    question1.setAnswer(false);
                }

                viewModel.update(question);
                notifyDataSetChanged();
                Toast.makeText(mContext, "Question updated", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    class QuestionHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RadioButton yes;
        private RadioButton no;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.question_title);
            this.yes = itemView.findViewById(R.id.yes);
            this.no = itemView.findViewById(R.id.no);
        }
    }

}
