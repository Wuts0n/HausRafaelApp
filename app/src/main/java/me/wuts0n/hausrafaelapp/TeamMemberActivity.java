package me.wuts0n.hausrafaelapp;

import android.content.ContentValues;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Intents.Insert;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import me.wuts0n.hausrafaelapp.database.DBMemberEntry;
import me.wuts0n.hausrafaelapp.databinding.ActivityTeamMemberBinding;
import me.wuts0n.hausrafaelapp.listener.TeamMemberClickListener;
import me.wuts0n.hausrafaelapp.utils.BitmapUtils;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;

import java.util.ArrayList;

public class TeamMemberActivity extends NavigateUpActivity {

    private ActivityTeamMemberBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_member);

        Intent intent = getIntent();
        String name = intent.getStringExtra(DBMemberEntry.COLUMN_NAME);
        String description = intent.getStringExtra(DBMemberEntry.COLUMN_DESCRIPTION);
        byte[] image = intent.getByteArrayExtra(DBMemberEntry.COLUMN_PICTURE);
        String phone = intent.getStringExtra(DBMemberEntry.COLUMN_PHONE);
        String fax = intent.getStringExtra(DBMemberEntry.COLUMN_FAX);
        String email = intent.getStringExtra(DBMemberEntry.COLUMN_EMAIL);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_team_member);
        TeamMemberClickListener listener = new TeamMemberClickListener(this, mBinding);

        mBinding.primaryInfo.tvName.setText(name);
        mBinding.primaryInfo.tvDescription.setText(description);
        mBinding.primaryInfo.ivFace.setImageBitmap(BitmapUtils.getBitMapFromByteArray(image));
        mBinding.primaryInfo.ivFace.setContentDescription(name);
        mBinding.secondaryInfo.tvPhone.setText(phone);
        mBinding.secondaryInfo.tvPhone.setOnClickListener(listener);
        mBinding.secondaryInfo.tvFax.setText(fax);
        mBinding.secondaryInfo.tvFax.setOnClickListener(listener);
        mBinding.secondaryInfo.tvEmail.setText(email);
        mBinding.secondaryInfo.tvEmail.setOnClickListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.team_member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_export:
                Intent intent = new Intent(Intent.ACTION_INSERT, Contacts.CONTENT_URI);
                intent.setType(Contacts.CONTENT_TYPE);
                intent.putExtra(Insert.NAME, mBinding.primaryInfo.tvName.getText());
                intent.putExtra(Insert.NOTES, mBinding.primaryInfo.tvDescription.getText());
                intent.putExtra(Insert.PHONE, mBinding.secondaryInfo.tvPhone.getText());
                intent.putExtra(Insert.PHONE_TYPE, Phone.TYPE_WORK);
                intent.putExtra(Insert.SECONDARY_PHONE, mBinding.secondaryInfo.tvFax.getText());
                intent.putExtra(Insert.SECONDARY_PHONE_TYPE, Phone.TYPE_FAX_WORK);
                intent.putExtra(Insert.EMAIL, mBinding.secondaryInfo.tvEmail.getText());
                intent.putExtra(Insert.EMAIL_TYPE, Email.TYPE_WORK);
                intent.putExtra(Insert.COMPANY, this.getString(R.string.company));

                // add picture to export
                ArrayList<ContentValues> list = new ArrayList<>();
                ContentValues values = new ContentValues();
                values.put(Data.MIMETYPE, Photo.CONTENT_ITEM_TYPE);
                values.put(Photo.PHOTO,
                        getIntent().getByteArrayExtra(DBMemberEntry.COLUMN_PICTURE));
                list.add(values);
                intent.putExtra(Insert.DATA, list);

                if (IntentUtils.isIntentValid(this, intent)) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this,
                            getString(R.string.export_unsuccessful),
                            Toast.LENGTH_LONG).show();
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
