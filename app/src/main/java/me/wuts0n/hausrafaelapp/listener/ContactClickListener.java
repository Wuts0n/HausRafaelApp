package me.wuts0n.hausrafaelapp.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.utils.DialogUtils;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;


public class ContactClickListener implements View.OnClickListener {

    private final TextView mTvContactLocation;
    private final TextView mTvContactPhone;
    private final TextView mTvContactFax;
    private final TextView mTvContactEmail;
    private final TextView mTvContactInternet;

    private final Activity mActivity;


    public ContactClickListener(Activity activity) {
        mActivity = activity;

        mTvContactLocation = (TextView) mActivity.findViewById(R.id.tv_contact_location);
        mTvContactPhone = (TextView) mActivity.findViewById(R.id.tv_contact_phone);
        mTvContactFax = (TextView) mActivity.findViewById(R.id.tv_contact_fax);
        mTvContactEmail = (TextView) mActivity.findViewById(R.id.tv_contact_email);
        mTvContactInternet = (TextView) mActivity.findViewById(R.id.tv_contact_internet);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        String title = null;
        Intent intent = null;

        if (mTvContactLocation.getId() == viewId) {
            title = mActivity.getString(R.string.location);
            intent = IntentUtils.getMapsIntent(mTvContactLocation.getText());
        } else if (mTvContactPhone.getId() == viewId) {
            title = mActivity.getString(R.string.phone);
            intent = IntentUtils.getPhoneIntent(mTvContactPhone.getText());
        } else if (mTvContactFax.getId() == viewId) {
            title = mActivity.getString(R.string.fax);
            // how the heck are you supposed to handle a fax intent on a mobile phone?
        } else if (mTvContactEmail.getId() == viewId) {
            title = mActivity.getString(R.string.email);
            intent = IntentUtils.getEmailIntent(mTvContactEmail.getText());
        } else if (mTvContactInternet.getId() == viewId) {
            title = mActivity.getString(R.string.url);
            intent = IntentUtils.getInternetIntent(mTvContactInternet.getContentDescription());
        }

        if (IntentUtils.isIntentValid(mActivity, intent)) {
            mActivity.startActivity(intent);
        } else {
            DialogUtils.createCopyDialog(mActivity, title, ((TextView) v).getText());
        }
    }
}
