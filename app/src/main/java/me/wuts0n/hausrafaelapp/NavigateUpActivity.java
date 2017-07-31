package me.wuts0n.hausrafaelapp;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

import me.wuts0n.hausrafaelapp.utils.NavigationUtils;


abstract public class NavigateUpActivity extends AppCompatActivity {

    protected DatabaseReference mDatabaseReference;
    protected ChildEventListener mChildEventListener;


    @Override
    protected void onPause() {
        super.onPause();
        mDatabaseReference.removeEventListener(mChildEventListener);
        mDatabaseReference = null;
        mChildEventListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                NavigationUtils.setNavigatePreviousTransition(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
