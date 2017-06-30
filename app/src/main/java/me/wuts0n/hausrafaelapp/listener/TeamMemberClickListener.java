package me.wuts0n.hausrafaelapp.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.databinding.ActivityTeamMemberBinding;
import me.wuts0n.hausrafaelapp.utils.DialogUtils;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;

/**
 * handles clicks on Phone, Fax and Email
 */

public class TeamMemberClickListener implements View.OnClickListener {

    private final Context mContext;
    private final ActivityTeamMemberBinding mBinding;


    public TeamMemberClickListener(Context context, ActivityTeamMemberBinding binding) {
        mContext = context;
        mBinding = binding;
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = null;
        String title = null;
        if (mBinding.secondaryInfo.tvPhone.getId() == viewId) {
            intent = IntentUtils.getPhoneIntent(((TextView) v).getText());
            title = mContext.getString(R.string.phone);
        } else if (mBinding.secondaryInfo.tvFax.getId() == viewId) {
            // intent = ???
            title = mContext.getString(R.string.fax);
        } else if (mBinding.secondaryInfo.tvEmail.getId() == viewId) {
            intent = IntentUtils.getEmailIntent(((TextView) v).getText());
            title = mContext.getString(R.string.email);
        }
        if (IntentUtils.isIntentValid(mContext, intent)) {
            mContext.startActivity(intent);
        } else {
            DialogUtils.createCopyDialog(mContext, title, ((TextView) v).getText());
        }
    }
}
