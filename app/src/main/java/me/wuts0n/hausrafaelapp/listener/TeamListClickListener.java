package me.wuts0n.hausrafaelapp.listener;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter.TeamListAdapterViewHolder;
import me.wuts0n.hausrafaelapp.database.DBMemberEntry;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;
import me.wuts0n.hausrafaelapp.utils.NavigationUtils;

public class TeamListClickListener implements View.OnClickListener {

    private final AppCompatActivity mActivity;
    private final TeamListAdapterViewHolder mHolder;
    private final Cursor mCursor;


    public TeamListClickListener(AppCompatActivity activity,
                                 TeamListAdapterViewHolder holder,
                                 Cursor cursor) {
        mActivity = activity;
        mHolder = holder;
        mCursor = cursor;
    }


    /**
     * This gets called by the child views during a click.
     *
     * @param v the View that was clicked
     */
    @Override
    public void onClick(View v) {
        mCursor.moveToPosition(mHolder.getAdapterPosition());
        Intent intent = IntentUtils.getTeamMemberActivityIntent(mActivity);
        intent.putExtra(DBMemberEntry.COLUMN_NAME,
                mCursor.getString(mCursor.getColumnIndex(DBMemberEntry.COLUMN_NAME)));
        intent.putExtra(DBMemberEntry.COLUMN_DESCRIPTION,
                mCursor.getString(mCursor.getColumnIndex(DBMemberEntry.COLUMN_DESCRIPTION)));
        intent.putExtra(DBMemberEntry.COLUMN_PICTURE,
                mCursor.getBlob(mCursor.getColumnIndex(DBMemberEntry.COLUMN_PICTURE)));
        intent.putExtra(DBMemberEntry.COLUMN_PHONE,
                mCursor.getString(mCursor.getColumnIndex(DBMemberEntry.COLUMN_PHONE)));
        intent.putExtra(DBMemberEntry.COLUMN_FAX,
                mCursor.getString(mCursor.getColumnIndex(DBMemberEntry.COLUMN_FAX)));
        intent.putExtra(DBMemberEntry.COLUMN_EMAIL,
                mCursor.getString(mCursor.getColumnIndex(DBMemberEntry.COLUMN_EMAIL)));
        mActivity.startActivity(intent);
        NavigationUtils.setNavigateNextTransition(mActivity);
    }
}
