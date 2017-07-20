package me.wuts0n.hausrafaelapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.wuts0n.hausrafaelapp.adapter.NewsActivityAdapter;
import me.wuts0n.hausrafaelapp.database.DatabaseHelper;
import me.wuts0n.hausrafaelapp.database.NewsTable;

public class NewsActivity extends NavigateUpActivity {

    private NewsActivityAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase readableDatabase = databaseHelper.getReadableDatabase();
        NewsTable newsTable = new NewsTable(readableDatabase, null);

        mAdapter = new NewsActivityAdapter(this, newsTable.selectAll());

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }


    public enum Severity {
        Info(0),
        Attention(1),
        Warning(2);

        private final int value;

        Severity(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }
    }
}
