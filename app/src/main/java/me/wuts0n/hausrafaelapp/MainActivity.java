package me.wuts0n.hausrafaelapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.wuts0n.hausrafaelapp.databinding.ActivityMainBinding;
import me.wuts0n.hausrafaelapp.listener.MainActivityClickListener;
import me.wuts0n.hausrafaelapp.service.NewsIntentService;

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

        Intent intent = new Intent(this, NewsIntentService.class);
        startService(intent);
    }
}
