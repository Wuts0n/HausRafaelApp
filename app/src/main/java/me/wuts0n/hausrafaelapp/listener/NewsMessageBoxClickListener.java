package me.wuts0n.hausrafaelapp.listener;

import android.content.Context;
import android.view.View;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.adapter.NewsActivityAdapter.NewsActivityAdapterViewHolder;
import me.wuts0n.hausrafaelapp.utils.DialogUtils;


public class NewsMessageBoxClickListener implements View.OnClickListener {

    private final Context mContext;
    private final NewsActivityAdapterViewHolder mHolder;


    public NewsMessageBoxClickListener(Context context, NewsActivityAdapterViewHolder holder) {
        mContext = context;
        mHolder = holder;
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (mHolder.constraintLayout.getId() == viewId) {
            StringBuilder sb = new StringBuilder();
            sb.append(mHolder.messageView.getText()).append(mHolder.dateView.getText());
            DialogUtils.createCopyDialog(mContext, mContext.getString(R.string.message), sb);
        }
    }
}
