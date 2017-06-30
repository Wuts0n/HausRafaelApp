package me.wuts0n.hausrafaelapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.wuts0n.hausrafaelapp.adapter.AboutUsActivityAdapter;
import me.wuts0n.hausrafaelapp.database.DBAboutUsEntry;
import me.wuts0n.hausrafaelapp.database.DBHelper;

public class AboutUsActivity extends NavigateUpActivity {

    private AboutUsActivityAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        DBHelper dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        mCursor = db.query(DBAboutUsEntry.TABLE_NAME,
                null, null, null, null, null, DBAboutUsEntry._ID);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_about_us);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new AboutUsActivityAdapter(this, mCursor);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mCursor.close();
        super.onDestroy();
    }
}
