package me.wuts0n.hausrafaelapp.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.database.DBAboutUsEntry;


public class AboutUsActivityAdapter extends DatabaseAdapter {


    public AboutUsActivityAdapter(@NonNull AppCompatActivity activity, Cursor cursor) {
        super(activity, cursor);
    }


    @Override
    public AboutUsActivityAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mActivity)
                .inflate(R.layout.about_us_item, viewGroup, false);
        view.setFocusable(true);
        return new AboutUsActivityAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AboutUsActivityAdapterViewHolder thisHolder = (AboutUsActivityAdapterViewHolder) holder;
        mCursor.moveToPosition(position);

        String heading = mCursor.getString(mCursor.getColumnIndex(DBAboutUsEntry.COLUMN_HEADING));
        thisHolder.headingView.setText(heading);
        String text = mCursor.getString(mCursor.getColumnIndex(DBAboutUsEntry.COLUMN_TEXT));
        thisHolder.textView.setText(text);
    }


    private class AboutUsActivityAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView headingView;
        final TextView textView;

        AboutUsActivityAdapterViewHolder(View view) {
            super(view);

            headingView = (TextView) view.findViewById(R.id.tv_about_us_heading);
            textView = (TextView) view.findViewById(R.id.tv_about_us_text);
        }
    }
}
