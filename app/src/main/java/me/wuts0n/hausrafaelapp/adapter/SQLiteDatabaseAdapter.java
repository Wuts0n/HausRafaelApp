package me.wuts0n.hausrafaelapp.adapter;


import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public abstract class SQLiteDatabaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final AppCompatActivity mActivity;
    protected Cursor mCursor;


    protected SQLiteDatabaseAdapter(@NonNull AppCompatActivity activity, Cursor cursor) {
        mActivity = activity;
        swapCursor(cursor);
    }


    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
