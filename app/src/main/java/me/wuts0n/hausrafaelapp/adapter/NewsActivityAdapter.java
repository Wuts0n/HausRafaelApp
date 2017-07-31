package me.wuts0n.hausrafaelapp.adapter;


import android.database.Cursor;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.Set;

import me.wuts0n.hausrafaelapp.NewsActivity.Severity;
import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.database.NewsTable;
import me.wuts0n.hausrafaelapp.listener.NewsMessageBoxClickListener;
import me.wuts0n.hausrafaelapp.utils.WDateUtils;

public class NewsActivityAdapter extends SQLiteDatabaseAdapter {

    private final Set<String> mKeys;


    public NewsActivityAdapter(@NonNull AppCompatActivity activity,
                               Cursor cursor,
                               Set<String> keys) {
        super(activity, cursor);
        mKeys = keys;
    }


    @Override
    public NewsActivityAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.news_item, viewGroup, false);
        view.setFocusable(true);
        return new NewsActivityAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsActivityAdapterViewHolder thisHolder = (NewsActivityAdapterViewHolder) holder;
        mCursor.moveToPosition(position);
        String key = mCursor.getString(mCursor.getColumnIndex(NewsTable.COLUMN_KEY));
        String timestamp = mCursor.getString(mCursor.getColumnIndex(NewsTable.COLUMN_DATE));
        Date date = WDateUtils.getDateFromTimestamp(timestamp);
        // one week == 604800000 milliseconds
        long millis = 604800000;
        if (mKeys.contains(key) && WDateUtils.isDateWithin(date, millis)) {
            thisHolder.constraintLayout.setMaxHeight(1337);
            String text = mCursor.getString(mCursor.getColumnIndex(NewsTable.COLUMN_TEXT));
            int severity = mCursor.getInt(mCursor.getColumnIndex(NewsTable.COLUMN_SEVERITY));
            if (Severity.Info.getValue() == severity) {
                setCompoundDrawable(thisHolder.messageView, R.drawable.ic_news_info);
            } else if (Severity.Attention.getValue() == severity) {
                setCompoundDrawable(thisHolder.messageView, R.drawable.ic_news_attention);
            } else if (Severity.Warning.getValue() == severity) {
                setCompoundDrawable(thisHolder.messageView, R.drawable.ic_news_warning);
            }
            thisHolder.messageView.setText(text);
            timestamp = WDateUtils.getLocalFormatDateString(date);
            if (timestamp != null) {
                thisHolder.dateView.setText(
                        mActivity.getString(R.string.received).concat(": ").concat(timestamp));
            }
        } else {
            thisHolder.constraintLayout.setMaxHeight(0);
        }
    }

    private void setCompoundDrawable(TextView textView, int id) {
        if (Build.VERSION.SDK_INT >= 21) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    mActivity.getDrawable(id), null, null, null);
        } else {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    mActivity.getResources().getDrawable(id), null, null, null);
        }
    }


    public class NewsActivityAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView messageView;
        public final TextView dateView;
        public final ConstraintLayout constraintLayout;

        NewsActivityAdapterViewHolder(View view) {
            super(view);

            messageView = (TextView) view.findViewById(R.id.tv_news_message);
            dateView = (TextView) view.findViewById(R.id.tv_news_date);
            constraintLayout = (ConstraintLayout) view.findViewById(R.id.cl_news_message_box);

            constraintLayout.setOnClickListener(
                    new NewsMessageBoxClickListener(mActivity, this));
        }
    }
}
