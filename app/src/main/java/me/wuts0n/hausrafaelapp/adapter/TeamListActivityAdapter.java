package me.wuts0n.hausrafaelapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.firebase.object.TeamMemberObject;
import me.wuts0n.hausrafaelapp.listener.TeamListClickListener;


public class TeamListActivityAdapter extends FirebaseDatabaseAdapter {

    public TeamListActivityAdapter(@NonNull AppCompatActivity activity, Object[] entries) {
        super(activity, entries);
    }

    @Override
    public TeamListAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mActivity)
                .inflate(R.layout.team_list_item, viewGroup, false);
        view.setFocusable(true);
        return new TeamListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TeamListAdapterViewHolder thisHolder = (TeamListAdapterViewHolder) holder;
        TeamMemberObject obj = (TeamMemberObject) mEntries[position];

        LinearLayout linearLayout = thisHolder.linearLayout;
        linearLayout.setContentDescription(obj.getName());

        TextView textView = thisHolder.textView;
        textView.setText(obj.getName());

        ImageView imageView = thisHolder.imageView;
        Glide.with(imageView.getContext())
                .load(obj.getPicture())
                .asBitmap()
                .placeholder(R.drawable.ic_placeholder_face)
                .into(imageView);
    }


    public static class TeamListContract {
        // key for Intent.putExtra, delivered to TeamMemberActivity
        public static final String KEY = "THIS_IS_A_KEY";
    }


    public class TeamListAdapterViewHolder extends RecyclerView.ViewHolder {

        final LinearLayout linearLayout;
        final TextView textView;
        final ImageView imageView;

        TeamListAdapterViewHolder(View view) {
            super(view);

            linearLayout = (LinearLayout) view.findViewById(R.id.ll_team_list);
            textView = (TextView) view.findViewById(R.id.tv_team_member_name);
            imageView = (ImageView) view.findViewById(R.id.iv_list_face);

            view.setOnClickListener(new TeamListClickListener(mActivity, this));
        }
    }
}
