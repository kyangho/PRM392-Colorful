package com.coloful.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.coloful.constant.Constant;
import com.coloful.model.Question;

import java.util.List;

public class QuizDao {
    DBHelper db;
    SQLiteDatabase sqLiteDatabase;

    public void addQuiz(Context context, String author, String title){
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constant.Quiz.TITLE.getValue(), title);
        cv.put(Constant.Quiz.AUTHOR.getValue(), author);
        long result = sqLiteDatabase.insert(Constant.Quiz.TABLE_NAME.getValue(), null,cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }   else {
            Toast.makeText(context, "Added success", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean insertQuiz(Context context, Quiz quiz, List<Question> questionList) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.Quiz.TITLE.getValue(), quiz.getTitle());
        contentValues.put(Constant.Quiz.AUTHOR.getValue(), quiz.getAuthor().getId());

        long quizId = sqLiteDatabase.insert(Constant.Quiz.TABLE_NAME.getValue(), null, contentValues);
        if (quizId <= 0) {
            return false;
        } else {
            for (Question question : questionList) {
                ContentValues values = new ContentValues();
                values.put(Constant.Question.Content.getValue(), question.getContent());
                values.put(Constant.Question.QUIZ_ID.getValue(), quizId);
                long questionId = sqLiteDatabase.insert(Constant.Question.TABLE_NAME.getValue(), null, values);

                if (questionId > 0) {
                    ContentValues values1 = new ContentValues();
                    values1.put(Constant.Answer.CONTENT.getValue(), question.getAnswer());
                    values1.put(Constant.Answer.QUES_ID.getValue(), questionId);
                    long answerId = sqLiteDatabase.insert(Constant.Answer.TABLE_NAME.getValue(), null, values1);

                    if (answerId <= 0) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            sqLiteDatabase.close();
            return true;
        }
    }

}
