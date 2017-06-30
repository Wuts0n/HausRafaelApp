package me.wuts0n.hausrafaelapp.adapter;


import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

abstract public class DatabaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final AppCompatActivity mActivity;
    protected Cursor mCursor;


    protected DatabaseAdapter(@NonNull AppCompatActivity activity, Cursor cursor) {
        mActivity = activity;
        swapCursor(cursor);
    }


    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    protected void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
