package me.wuts0n.hausrafaelapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.wuts0n.hausrafaelapp.adapter.NewsActivityAdapter;
import me.wuts0n.hausrafaelapp.database.DBHelper;
import me.wuts0n.hausrafaelapp.database.DBNewsEntry;

public class NewsActivity extends NavigateUpActivity {

    private NewsActivityAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        DBHelper dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        mCursor = db.query(DBNewsEntry.TABLE_NAME, null, null, null, null, null, DBNewsEntry._ID);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NewsActivityAdapter(this, mCursor);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onDestroy() {
        mCursor.close();
        super.onDestroy();
    }
}
