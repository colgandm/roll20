package com.example.daniel.roll20.activities;

import com.example.daniel.roll20.R;
import com.example.daniel.roll20.utils.DiceRoller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RollerActivity extends AppCompatActivity {

    private final DiceRoller diceRoller = new DiceRoller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roller);
        initializeSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    private void initializeSpinner() {
        // Dice Spinner
        Spinner diceSpinner = (Spinner)findViewById(R.id.dice_spinner);
        ArrayAdapter<CharSequence> diceAdapter = ArrayAdapter.createFromResource(this, R.array.dice_array,
            android.R.layout.simple_spinner_item);
        diceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diceSpinner.setAdapter(diceAdapter);
    }

    public void rollDice(View view) {

        hideSoftKeyboard(this, view);
        Spinner diceSpinner = (Spinner)findViewById(R.id.dice_spinner);
        int numOfSides = Integer.parseInt(diceSpinner.getSelectedItem().toString());

        EditText numOfRollText = (EditText)findViewById(R.id.numberOfRolls);
        if (numOfRollText.getText().toString().matches("")) {
            Toast.makeText(this, "Please enter the number of rolls", Toast.LENGTH_SHORT).show();
            return;
        }

        int numOfRolls = Integer.parseInt(numOfRollText.getText().toString());

        int[] result = diceRoller.roll(numOfRolls, numOfSides);

        TextView rollResult = (TextView)findViewById(R.id.roll_result);
        rollResult.setText(prettyPrint(result));

        TextView rollTotal = (TextView)findViewById(R.id.roll_total);
        rollTotal.setText(totalPrint(result));
    }

    private String prettyPrint(int[] results) {
        String resultString = "Results : ";
        for (int result : results) {
            resultString += result + " ";
        }
        return resultString;
    }

    private String totalPrint(int[] results) {
        int resultsTotal = 0;
        for (int result : results) {
            resultsTotal += result;
        }
        return "Total : " + String.valueOf(resultsTotal);
    }

}
