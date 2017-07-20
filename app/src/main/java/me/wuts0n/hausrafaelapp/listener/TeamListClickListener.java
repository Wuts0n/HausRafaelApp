package me.wuts0n.hausrafaelapp.listener;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter.TeamListAdapterViewHolder;
import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter.TeamListContract;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;
import me.wuts0n.hausrafaelapp.utils.NavigationUtils;

public class TeamListClickListener implements View.OnClickListener {

    private final Activity mActivity;
    private final TeamListAdapterViewHolder mHolder;


    public TeamListClickListener(Activity activity, TeamListAdapterViewHolder holder) {
        mActivity = activity;
        mHolder = holder;
    }


    /**
     * This gets called by the child views during a click.
     *
     * @param v the View that was clicked
     */
    @Override
    public void onClick(View v) {
        Intent intent = IntentUtils.getTeamMemberActivityIntent(mActivity);
        intent.putExtra(TeamListContract.KEY,
                ((TextView) mHolder.itemView.findViewById(R.id.tv_team_member_name)).getText());
        mActivity.startActivity(intent);
        NavigationUtils.setNavigateNextTransition((AppCompatActivity) mActivity);
    }
}
