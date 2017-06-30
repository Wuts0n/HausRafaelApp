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

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.database.DBNewsEntry;
import me.wuts0n.hausrafaelapp.database.DBNewsEntry.Severity;
import me.wuts0n.hausrafaelapp.listener.NewsMessageBoxClickListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsActivityAdapter extends DatabaseAdapter {

    private final DateFormat mFormat;


    public NewsActivityAdapter(@NonNull AppCompatActivity activity, Cursor cursor) {
        super(activity, cursor);
        // support for 'X' format character apparently first added in API level 24
        if (Build.VERSION.SDK_INT < 24) {
            mFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        } else {
            mFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault());
        }
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

        String text = mCursor.getString(mCursor.getColumnIndex(DBNewsEntry.COLUMN_TEXT));
        int severity = mCursor.getInt(mCursor.getColumnIndex(DBNewsEntry.COLUMN_SEVERITY));
        if (Severity.Info.getValue() == severity) {
            setCompoundDrawable(thisHolder.messageView, R.drawable.ic_news_info);
        } else if (Severity.Attention.getValue() == severity) {
            setCompoundDrawable(thisHolder.messageView, R.drawable.ic_news_attention);
        } else if (Severity.Warning.getValue() == severity) {
            setCompoundDrawable(thisHolder.messageView, R.drawable.ic_news_warning);
        }
        thisHolder.messageView.setText(text);
        String dateString = mCursor.getString(mCursor.getColumnIndex(DBNewsEntry.COLUMN_TIMESTAMP));
        try {
            if (Build.VERSION.SDK_INT < 24) {
                int i = dateString.lastIndexOf('+');
                if (i < 0) {
                    i = dateString.lastIndexOf('-');
                }
                String firstPart = dateString.substring(0, i);
                String secondPart = dateString.substring(i).replaceAll(":", "");
                dateString = firstPart.concat(secondPart);
            }
            Date date = mFormat.parse(dateString);
            String formattedDate = DateFormat
                    .getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(date)
                    .concat(" ")
                    .concat(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date));
            thisHolder.dateView.setText(" ~ ".concat(formattedDate));
        } catch (ParseException e) {}
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
