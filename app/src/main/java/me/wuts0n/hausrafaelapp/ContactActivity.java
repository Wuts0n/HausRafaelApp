package me.wuts0n.hausrafaelapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import me.wuts0n.hausrafaelapp.database.DBContactEntry;
import me.wuts0n.hausrafaelapp.database.DBHelper;
import me.wuts0n.hausrafaelapp.databinding.ActivityContactBinding;
import me.wuts0n.hausrafaelapp.listener.ContactClickListener;
import me.wuts0n.hausrafaelapp.utils.BitmapUtils;
import me.wuts0n.hausrafaelapp.utils.UriUtils;

public class ContactActivity extends NavigateUpActivity {

    private ActivityContactBinding mBinding;
    private Cursor mCursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact);

        DBHelper dbhelper = new DBHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        mCursor = db.query(DBContactEntry.TABLE_NAME,
                null, null, null, null, null, DBContactEntry._ID);
        mCursor.moveToFirst();

        mBinding.ivPicture.setImageBitmap(BitmapUtils.getBitMapFromByteArray(
                mCursor.getBlob(mCursor.getColumnIndex(DBContactEntry.COLUMN_PICTURE))));
        mBinding.tvContactHeading.setText(mCursor.getString(
                mCursor.getColumnIndex(DBContactEntry.COLUMN_DESCRIPTION)));
        mBinding.tvContactLocation.setText(mCursor.getString(
                mCursor.getColumnIndex(DBContactEntry.COLUMN_LOCATION)).replaceAll("[,;] ", ",\n"));
        mBinding.tvContactPhone.setText(mCursor.getString(
                mCursor.getColumnIndex(DBContactEntry.COLUMN_PHONE)));
        mBinding.tvContactFax.setText(mCursor.getString(
                mCursor.getColumnIndex(DBContactEntry.COLUMN_FAX)));
        mBinding.tvContactEmail.setText(mCursor.getString(
                mCursor.getColumnIndex(DBContactEntry.COLUMN_EMAIL)));
        String url = mCursor.getString(mCursor.getColumnIndex(DBContactEntry.COLUMN_WEBSITE))
                .trim();
        mBinding.tvContactInternet.setContentDescription(url);
        mBinding.tvContactInternet.setText(UriUtils.getAuthority(url));

        ContactClickListener listener = new ContactClickListener(this, mBinding);

        mBinding.tvContactLocation.setOnClickListener(listener);
        mBinding.tvContactPhone.setOnClickListener(listener);
        mBinding.tvContactFax.setOnClickListener(listener);
        mBinding.tvContactEmail.setOnClickListener(listener);
        mBinding.tvContactInternet.setOnClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        mCursor.close();
        super.onDestroy();
    }
}
