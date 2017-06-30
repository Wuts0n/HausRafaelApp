package me.wuts0n.hausrafaelapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter;
import me.wuts0n.hausrafaelapp.database.DBHelper;
import me.wuts0n.hausrafaelapp.database.DBMemberEntry;

public class TeamListActivity extends NavigateUpActivity {

    private TeamListActivityAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        DBHelper dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        mCursor = db.query(DBMemberEntry.TABLE_NAME,
                null, null, null, null, null, DBMemberEntry._ID);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_team_list);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new TeamListActivityAdapter(this, mCursor);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mCursor.close();
        super.onDestroy();
    }

}
