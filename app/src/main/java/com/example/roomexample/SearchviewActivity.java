package com.example.roomexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.roomexample.Room.Word;
import com.example.roomexample.Room.WordListAdapter;
import com.example.roomexample.Room.WordViewModel;

import java.util.List;

public class SearchviewActivity extends AppCompatActivity implements View.OnClickListener{
    View item;
    AutoCompleteTextView edit_search;
    private static final String TAG = "SearchviewActivity";
    private WordViewModel mWordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        item = findViewById(R.id.layout_searchview);
        item.findViewById(R.id.image_back).setOnClickListener(this);
        item.findViewById(R.id.image_close).setOnClickListener(this);
        findViewById(R.id.list_clear).setOnClickListener(this);
        edit_search = item.findViewById(R.id.edit_text_search);

        //자동 검색 구현
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.item_array,
                R.layout.support_simple_spinner_dropdown_item);
        edit_search.setAdapter(adapter);

        RecyclerView recyclerView = findViewById(R.id.recyclverview);
        final WordListAdapter ListAdapter = new WordListAdapter(this);
        recyclerView.setAdapter(ListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Model Provider
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        //observe : model의 LiveData를 관찰.
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                ListAdapter.setWords(words);
            }
        });

        edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String search = edit_search.getText().toString();

                // 검색 버튼 눌렀는지 확인
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (search.isEmpty()) {

                    } else {
                        //room db 저장
                        Word word = new Word(search);
                        mWordViewModel.insert(word);

                        //intent로 화면 이동하면 됌
                    }

                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:

                break;

            case R.id.image_close:
                edit_search.getText().clear();
                break;

            case R.id.list_clear:
                mWordViewModel.setAllDelete();
                break;
        }
    }
}
