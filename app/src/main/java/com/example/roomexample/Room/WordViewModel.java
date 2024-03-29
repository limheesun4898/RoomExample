package com.example.roomexample.Room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWord;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWord = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){ return mAllWord;}

    public void setAllDelete(){ mRepository.delete();}

    public void insert(Word word) { mRepository.insert(word);}

}
