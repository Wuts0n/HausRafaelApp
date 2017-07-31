package me.wuts0n.hausrafaelapp;

import android.content.ContentValues;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Intents.Insert;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter.TeamListContract;
import me.wuts0n.hausrafaelapp.databinding.ActivityTeamMemberBinding;
import me.wuts0n.hausrafaelapp.firebase.object.TeamMemberObject;
import me.wuts0n.hausrafaelapp.listener.TeamMemberClickListener;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;


public class TeamMemberActivity extends NavigateUpActivity {

    private ActivityTeamMemberBinding mBinding;
    private TeamMemberClickListener mClickListener;
    private String mKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_member);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_team_member);
        mClickListener = new TeamMemberClickListener(this, mBinding);

        Intent intent = getIntent();
        mKey = intent.getStringExtra(TeamListContract.KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference = FirebaseReference.getDatabaseReference().child("team_member");
        attachDatabaseReadListener();
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
                ImageView imageView = mBinding.primaryInfo.teamMemberImageView.ivFace;
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] imageInByte = byteArrayOutputStream.toByteArray();
                values.put(Photo.PHOTO, imageInByte);
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

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    updateChild(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateChild(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("DatabaseError", databaseError.toString());
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void updateChild(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey().replace(" ", ". ");
        TeamMemberObject entry = dataSnapshot.getValue(TeamMemberObject.class);
        if (entry != null && mKey.equals(key)) {
            entry.setName(key);
            setContent(entry);
        }
    }

    private void setContent(TeamMemberObject obj) {
        if (!this.isDestroyed()) {
            String name = obj.getName();
            String description = obj.getDescription();
            String phone = obj.getPhone();
            String fax = obj.getFax();
            String email = obj.getEmail();
            mBinding.primaryInfo.tvName.setText(name);
            mBinding.primaryInfo.tvDescription.setText(description == null ? "" : description);
            ImageView ivFace = mBinding.primaryInfo.teamMemberImageView.ivFace;
            Glide.with(ivFace.getContext())
                    .load(obj.getPicture())
                    .asBitmap()
                    .placeholder(R.drawable.ic_placeholder_face)
                    .into(ivFace);
            mBinding.primaryInfo.teamMemberImageView.ivFace.setContentDescription(name);
            TextView tvPhone = mBinding.secondaryInfo.tvPhone;
            TextView tvFax = mBinding.secondaryInfo.tvFax;
            TextView tvEmail = mBinding.secondaryInfo.tvEmail;
            if (phone != null) {
                tvPhone.setText(phone);
                tvPhone.setOnClickListener(mClickListener);
                tvPhone.setVisibility(View.VISIBLE);
            } else {
                tvPhone.setOnClickListener(null);
                tvPhone.setVisibility(View.INVISIBLE);
            }
            if (fax != null) {
                tvFax.setText(fax);
                tvFax.setOnClickListener(mClickListener);
                tvFax.setVisibility(View.VISIBLE);
            } else {
                tvFax.setOnClickListener(null);
                tvFax.setVisibility(View.INVISIBLE);
            }
            if (email != null) {
                tvEmail.setText(email);
                tvEmail.setOnClickListener(mClickListener);
                tvEmail.setVisibility(View.VISIBLE);
            } else {
                tvEmail.setOnClickListener(null);
                tvEmail.setVisibility(View.INVISIBLE);
            }
        }
    }
}
