package com.example.croesus.model.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions",
        foreignKeys =
        @ForeignKey(entity = Survey.class, parentColumns = "id", childColumns = "survey_id",
                onDelete = ForeignKey.CASCADE))
public class Question {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int questionId;

    private String questionText;
    private boolean answer;

    private int survey_id;

    public Question(int questionId, String questionText, boolean answer, int survey_id) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.answer = answer;
        this.survey_id = survey_id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }
}
