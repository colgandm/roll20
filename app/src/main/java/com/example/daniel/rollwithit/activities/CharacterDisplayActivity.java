package com.example.daniel.rollwithit.activities;

import com.example.daniel.rollwithit.R;
import com.example.daniel.rollwithit.dialogs.AttributeDialogFragment;
import com.example.daniel.rollwithit.dialogs.DetailsDialogFragment;
import com.example.daniel.rollwithit.fragments.CharacterAttributesFragment;
import com.example.daniel.rollwithit.fragments.CharacterDetailsFragment;
import com.example.daniel.rollwithit.interfaces.AttributeDialogListener;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class CharacterDisplayActivity extends FragmentActivity implements AttributeDialogListener {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
    private String characterName;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCharacterName(getIntent().getStringExtra("characterName"));
        setContentView(R.layout.activity_character_display);
        verifyStoragePermissions(this);
    }

    @Override
    public void onUpdatedAttribute(int value, String attribute) {
        CharacterAttributesFragment fragment = (CharacterAttributesFragment)getFragmentManager()
            .findFragmentById(R.id.characterAttributesFragment);
        fragment.reloadCharacterAttributes(fragment.getView(), value, attribute);
    }

    @Override
    public void onUpdatedDetail(String value, String attribute) {
        CharacterDetailsFragment fragment = (CharacterDetailsFragment)getFragmentManager()
            .findFragmentById(R.id.CharacterDetailsFragment);
        fragment.reloadCharacterDetails(fragment.getView(), value, attribute);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.i("INFO", "Back button pressed Activity finished");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void raiseAttributeDialog(String attributeName) {
        Bundle args = new Bundle();
        args.putString("attributeName", attributeName);
        AttributeDialogFragment dialog = new AttributeDialogFragment();
        dialog.setArguments(args);
        dialog.show(getFragmentManager(), "attributeDialog");
    }

    public void raiseDetailsDialog(String detailName) {
        Bundle args = new Bundle();
        args.putString("detailName", detailName);
        DetailsDialogFragment dialog = new DetailsDialogFragment();
        dialog.setArguments(args);
        dialog.show(getFragmentManager(), "detailsDialog");
    }

    public String getCharacterName() {
        return characterName;
    }

    private void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}