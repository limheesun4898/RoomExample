package com.example.roomexample.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * From word_table")
    LiveData<List<Word>> getAllWords();

    @Query("DELETE FROM word_table")
    void setAllDelete();

    @Insert
    void insert(Word word);
}
