package me.wuts0n.hausrafaelapp.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.databinding.ActivityContactBinding;
import me.wuts0n.hausrafaelapp.utils.DialogUtils;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;


public class ContactClickListener implements View.OnClickListener {

    private final Context mContext;
    private final ActivityContactBinding mBinding;


    public ContactClickListener(Context context, ActivityContactBinding binding) {
        mContext = context;
        mBinding = binding;
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        String title = null;
        Intent intent = null;

        if (mBinding.tvContactLocation.getId() == viewId) {
            title = mContext.getString(R.string.location);
            intent = IntentUtils.getMapsIntent(mBinding.tvContactLocation.getText());
        } else if (mBinding.tvContactPhone.getId() == viewId) {
            title = mContext.getString(R.string.phone);
            intent = IntentUtils.getPhoneIntent(mBinding.tvContactPhone.getText());
        } else if (mBinding.tvContactFax.getId() == viewId) {
            title = mContext.getString(R.string.fax);
            // how the heck are you supposed to handle a fax intent on a mobile phone?
        } else if (mBinding.tvContactEmail.getId() == viewId) {
            title = mContext.getString(R.string.email);
            intent = IntentUtils.getEmailIntent(mBinding.tvContactEmail.getText());
        } else if (mBinding.tvContactInternet.getId() == viewId) {
            title = mContext.getString(R.string.url);
            intent = IntentUtils.getInternetIntent(
                    mBinding.tvContactInternet.getContentDescription());
        }

        if (IntentUtils.isIntentValid(mContext, intent)) {
            mContext.startActivity(intent);
        } else {
            DialogUtils.createCopyDialog(mContext, title, ((TextView) v).getText());
        }
    }
}
