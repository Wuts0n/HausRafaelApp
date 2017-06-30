package me.wuts0n.hausrafaelapp.listener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.wuts0n.hausrafaelapp.databinding.ActivityMainBinding;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;
import me.wuts0n.hausrafaelapp.utils.NavigationUtils;


public class MainActivityClickListener implements View.OnClickListener {

    private final AppCompatActivity mActivity;
    private final ActivityMainBinding mBinding;


    public MainActivityClickListener(AppCompatActivity activity, ActivityMainBinding binding) {
        mActivity = activity;
        mBinding = binding;
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
            intent = IntentUtils.getBusConnectionsIntent();
        } else if (mBinding.tvMenuGoogleMaps.getId() == viewId) {
            intent = IntentUtils.getDefaultMapsIntent();
        }
        if (IntentUtils.isIntentValid(mActivity, intent)) {
            mActivity.startActivity(intent);
            NavigationUtils.setNavigateNextTransition(mActivity);
        }

    }


}
