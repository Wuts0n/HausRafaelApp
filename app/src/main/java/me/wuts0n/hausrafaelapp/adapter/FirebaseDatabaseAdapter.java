package me.wuts0n.hausrafaelapp.adapter;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public abstract class FirebaseDatabaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final Activity mActivity;
    protected Object[] mEntries;


    public FirebaseDatabaseAdapter(@NonNull Activity activity, Object[] entries) {
        mActivity = activity;
        mEntries = entries;
    }


    @Override
    public int getItemCount() {
        return mEntries.length;
    }

    public void swapArray(Object[] entries) {
        mEntries = entries;
        notifyDataSetChanged();
    }
}
