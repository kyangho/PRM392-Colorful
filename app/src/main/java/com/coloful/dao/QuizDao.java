package com.coloful.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coloful.constant.Constant;
import com.coloful.model.Account;
import com.coloful.model.Question;
import com.coloful.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDao {

    AccountDao accountDao = new AccountDao();
    DBHelper db;
    SQLiteDatabase sqLiteDatabase;

    public List<Quiz> search(Context context, String key) {
        List<Quiz> quizList = new ArrayList<>();
        db = new DBHelper(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM quiz WHERE title MATCH ?;", new String[]{key});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Quiz q = new Quiz();
            q.setId(cursor.getInt(0));
            q.setTitle(cursor.getString(1));
            Account account = accountDao.getAccountForQuiz(context, cursor.getInt(3));
            q.setAuthor(account);
            quizList.add(q);
            cursor.moveToNext();
        }
        return quizList;
    }

    public Quiz getQuizById(Context context, int id) {
        db = new DBHelper(context);
        sqLiteDatabase = db.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM quiz WHERE id = ?;", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            Quiz q = new Quiz();
            q.setId(cursor.getInt(0));
            q.setTitle(cursor.getString(1));
            Account account = accountDao.getAccountForQuiz(context, cursor.getInt(3));
            q.setAuthor(account);
            return q;
        }

        return null;
    }
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
