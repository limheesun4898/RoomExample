package com.example.roomexample.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {
    //    @PrimaryKey(autoGenerate = true) // 프라이머리키를 자동으로 설정해줌
//    private int id;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    public Word(@NonNull String word) {
        this.word = word;
    }


    public String getWord() {
        return word;
    }


    @Override
    public String toString() {
        return "Item_searchword{" +
                "word='" + word + '\'' +
                '}';
    }
}
