package me.wuts0n.hausrafaelapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.wuts0n.hausrafaelapp.databinding.ActivityMainBinding;
import me.wuts0n.hausrafaelapp.listener.MainActivityClickListener;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityClickListener listener = new MainActivityClickListener(this, mBinding);
        mBinding.tvMenuNews.setOnClickListener(listener);
        mBinding.tvMenuTeam.setOnClickListener(listener);
        mBinding.tvMenuContact.setOnClickListener(listener);
        mBinding.tvMenuAboutUs.setOnClickListener(listener);
        mBinding.tvMenuBusConnections.setOnClickListener(listener);
        mBinding.tvMenuGoogleMaps.setOnClickListener(listener);

        // filling database with dummy data
        /*me.wuts0n.hausrafaelapp.database.DBHelper dbhelper =
                new me.wuts0n.hausrafaelapp.database.DBHelper(this);
        android.database.sqlite.SQLiteDatabase writableDB = dbhelper.getWritableDatabase();
        new me.wuts0n.hausrafaelapp.testing.DummyData(this, writableDB);*/

    }

}
