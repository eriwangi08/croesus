package com.example.croesus.model.builders;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.croesus.model.daos.QuestionDao;
import com.example.croesus.model.daos.SurveyDao;
import com.example.croesus.model.daos.UserDao;
import com.example.croesus.model.entities.Question;
import com.example.croesus.model.entities.Survey;
import com.example.croesus.model.entities.User;

/**
 * This class is used to build the complete app database
 */
@Database(entities = {User.class, Survey.class, Question.class}, version = 1, exportSchema = false)
public abstract class CroesusDatabase extends RoomDatabase {
    private static CroesusDatabase instance; // this is to convert this to be a singleton

    public abstract UserDao userDao();

    public abstract SurveyDao surveyDao();

    public abstract QuestionDao questionDao();

    /**
     * Method used to instantiate the database
     *
     * @param context
     * @return
     */
    public static synchronized CroesusDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CroesusDatabase.class,
                    "croesus_database") // db name on the device
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }


    /**
     * This is a room call back that we use to seed values into the database
     */
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute(); // call the task to seed the dbs
        }
    };

    /**
     * This async task is to insert (seed) values in the database
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;
        private SurveyDao surveyDao;
        private QuestionDao questionDao;

        private PopulateDbAsyncTask(CroesusDatabase croesusDatabase) {
            userDao = croesusDatabase.userDao();
            surveyDao = croesusDatabase.surveyDao();
            questionDao = croesusDatabase.questionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("Username", "", 123456789));
            surveyDao.insert(new Survey(0, "Survey on savings", "This has questions to do with saving"));
            surveyDao.insert(new Survey(1, "Survey on debt", "This has questions to do with debt"));
            questionDao.insert(new Question(1, "Are you in any savings group", false, 0));
            questionDao.insert(new Question(2, "Would you join any savings group", true, 0));
            questionDao.insert(new Question(3, "Do you have any debt", false, 1));
            questionDao.insert(new Question(4, "Are you listed in the CRB", false, 1));
            questionDao.insert(new Question(5, "Is anyone you know in debt", false, 1));
            return null;
        }
    }
}
