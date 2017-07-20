package me.wuts0n.hausrafaelapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.firebase.object.AboutUsObject;


public class AboutUsActivityAdapter extends FirebaseDatabaseAdapter {


    public AboutUsActivityAdapter(@NonNull Activity activity, Object[] entries) {
        super(activity, entries);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mActivity)
                .inflate(R.layout.about_us_item, viewGroup, false);
        view.setFocusable(true);
        return new AboutUsActivityAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AboutUsActivityAdapterViewHolder thisHolder = (AboutUsActivityAdapterViewHolder) holder;

        AboutUsObject obj = (AboutUsObject) mEntries[position];

        thisHolder.headingView.setText(obj.getHeading());
        thisHolder.textView.setText(obj.getText());
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
