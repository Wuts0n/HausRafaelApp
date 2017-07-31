package me.wuts0n.hausrafaelapp.listener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.databinding.ActivityMainBinding;
import me.wuts0n.hausrafaelapp.firebase.object.SimpleLinkObject;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;
import me.wuts0n.hausrafaelapp.utils.NavigationUtils;


public class MainActivityClickListener implements View.OnClickListener {

    private final AppCompatActivity mActivity;
    private final ActivityMainBinding mBinding;
    private final DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private String mBusConnectionUrl;
    private String mMapsUrl;


    public MainActivityClickListener(AppCompatActivity activity, ActivityMainBinding binding) {
        mActivity = activity;
        mBinding = binding;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference().child("simple_links");
        attachFirebaseReadListener();
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = null;
        if (mBinding.tvMenuNews.getId() == viewId) {
            intent = IntentUtils.getNewsActivityIntent(mActivity);
        } else if (mBinding.tvMenuTeam.getId() == viewId) {
            intent = IntentUtils.getTeamListActivityIntent(mActivity);
        } else if (mBinding.tvMenuContact.getId() == viewId) {
            intent = IntentUtils.getContactActivityIntent(mActivity);
        } else if (mBinding.tvMenuAboutUs.getId() == viewId) {
            intent = IntentUtils.getAboutUsActivityIntent(mActivity);
        } else if (mBinding.tvMenuBusConnections.getId() == viewId) {
            if (mBusConnectionUrl != null) {
                intent = IntentUtils.getInternetIntent(mBusConnectionUrl);
            } else {
                Toast.makeText(mActivity, R.string.no_internet_connection, Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (mBinding.tvMenuGoogleMaps.getId() == viewId) {
            intent = IntentUtils.getInternetIntent(mMapsUrl);
        }
        if (IntentUtils.isIntentValid(mActivity, intent)) {
            mActivity.startActivity(intent);
            NavigationUtils.setNavigateNextTransition(mActivity);
        }
    }

    private void attachFirebaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    SimpleLinkObject entry = dataSnapshot.getValue(SimpleLinkObject.class);
                    String key = dataSnapshot.getKey();
                    String url = (entry != null ? entry.getUrl() : null);
                    if (key.equals("bus_connection")) {
                        mBusConnectionUrl = url;
                    } else if (key.equals("maps")) {
                        mMapsUrl = url;
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    SimpleLinkObject entry = dataSnapshot.getValue(SimpleLinkObject.class);
                    String key = dataSnapshot.getKey();
                    String url = (entry != null ? entry.getUrl() : null);
                    if (key.equals("bus_connection")) {
                        mBusConnectionUrl = url;
                    } else if (key.equals("maps")) {
                        mMapsUrl = url;
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    if (key.equals("bus_connection")) {
                        mBusConnectionUrl = null;
                    } else if (key.equals("maps")) {
                        mMapsUrl = null;
                    }
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.wtf("FirebaseDatabaseError", databaseError.toString());
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
