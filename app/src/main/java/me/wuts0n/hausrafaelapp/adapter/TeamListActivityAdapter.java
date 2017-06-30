package me.wuts0n.hausrafaelapp.adapter;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.database.DBMemberEntry;
import me.wuts0n.hausrafaelapp.listener.TeamListClickListener;


public class TeamListActivityAdapter extends DatabaseAdapter {


    public TeamListActivityAdapter(@NonNull AppCompatActivity activity, Cursor cursor) {
        super(activity, cursor);
    }


    @Override
    public TeamListAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mActivity)
                .inflate(R.layout.team_list_item, viewGroup, false);
        view.setFocusable(true);
        return new TeamListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TeamListAdapterViewHolder thisHolder = (TeamListAdapterViewHolder) holder;
        mCursor.moveToPosition(position);

        String name = mCursor.getString(mCursor.getColumnIndex(DBMemberEntry.COLUMN_NAME));
        thisHolder.memberView.setText(name);
        thisHolder.memberView.setContentDescription(name);
        byte[] image = mCursor.getBlob(mCursor.getColumnIndex(DBMemberEntry.COLUMN_PICTURE));
        if (image != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                thisHolder.memberView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        mActivity.getDrawable(R.mipmap.ic_milka_lalu),                      // start
                        null,
                        mActivity.getDrawable(R.drawable.ic_navigate_next),                 // end
                        null);
            } else {
                thisHolder.memberView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        mActivity.getResources().getDrawable(R.mipmap.ic_milka_lalu),       // start
                        null,
                        mActivity.getResources().getDrawable(R.drawable.ic_navigate_next),  // end
                        null);
            }
        }
    }


    public class TeamListAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView memberView;

        TeamListAdapterViewHolder(View view) {
            super(view);

            memberView = (TextView) view.findViewById(R.id.tv_team_member_name);

            view.setOnClickListener(new TeamListClickListener(mActivity, this, mCursor));
        }
    }
}
