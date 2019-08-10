package com.example.roomexample.Room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllwords;

    public WordRepository(Application application){
        WordDatabase db = WordDatabase.getDatabase(application);
        //room db에 있는 dao를 가져온다.
        mWordDao = db.wordDao();
        mAllwords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){ return mAllwords;}

    //insert
    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao){ mAsyncTaskDao = dao;}
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    //delete
    public void delete(){
        new deleteAsyncTask(mWordDao).execute();
    }

    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncTaskDao;
        deleteAsyncTask(WordDao dao) { mAsyncTaskDao = dao;}
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.setAllDelete();
            return null;
        }
    }

}
