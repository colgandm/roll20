package com.example.daniel.roll20.activities;

import com.example.daniel.roll20.R;
import com.example.daniel.roll20.dndCharacter.Character;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class CharacterDisplayActivity extends FragmentActivity {

    private final Character character = new Character("Toby");
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
        Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);
        verifyStoragePermissions(this);
        loadCharacterFromFile();
    }

    private void loadCharacterFromFile() {
        // character = characterMapper.ReadCharacterFromFile();
        displayCharacter(character);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    private void displayCharacter(Character character) {
        TextView characterName = (TextView)findViewById(R.id.characterName);
        characterName.setText(character.getCharacterName());

        TextView dndClass = (TextView)findViewById(R.id.dndClass);
        dndClass.setText(character.getDnDClass());

        TextView background = (TextView)findViewById(R.id.background);
        background.setText(character.getBackground());

        TextView playerName = (TextView)findViewById(R.id.playerName);
        playerName.setText(character.getPlayerName());

        TextView Race = (TextView)findViewById(R.id.Race);
        Race.setText(character.getRace());

        TextView alignment = (TextView)findViewById(R.id.alignment);
        alignment.setText(character.getAlignment());

        TextView xp = (TextView)findViewById(R.id.xp);
        xp.setText(String.valueOf(character.getXp()));

        // TextView armourClass = (TextView)findViewById(R.id.armourClass);
        // armourClass.setText(String.valueOf(character.getArmourClass()));
        //
        // TextView initiative = (TextView)findViewById(R.id.initiative);
        // initiative.setText(String.valueOf(character.getInitiative()));
        //
        // TextView speed = (TextView)findViewById(R.id.speed);
        // speed.setText(String.valueOf(character.getSpeed()));

        TextView strength = (TextView)findViewById(R.id.strengthShield);
        strength.setText(String.valueOf(character.getStrength()));

        TextView dexterity = (TextView)findViewById(R.id.dexterityShield);
        dexterity.setText(String.valueOf(character.getDexterity()));

        TextView constitution = (TextView)findViewById(R.id.constitutionShield);
        constitution.setText(String.valueOf(character.getConstitution()));

        TextView intelligence = (TextView)findViewById(R.id.intelligenceShield);
        intelligence.setText(String.valueOf(character.getIntelligence()));

        TextView wisdom = (TextView)findViewById(R.id.wisdomShield);
        wisdom.setText(String.valueOf(character.getWisdom()));

        TextView charisma = (TextView)findViewById(R.id.charismaShield);
        charisma.setText(String.valueOf(character.getCharisma()));
    }

}
