package com.example.scorekeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    private int score1Value, score2Value;
    private ImageButton plus1, plus2, minus1, minus2;
    private TextView score1, score2;
    private String STATE_SCORE_1 = "score1";
    private String STATE_SCORE_2 = "score2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.score1 = (TextView) findViewById(R.id.score1);
        this.score2 = (TextView) findViewById(R.id.score2);
        this.score1Value = Integer.parseInt((String) score1.getText());
        this.score2Value = Integer.parseInt((String) score2.getText());
        this.minus1 = (ImageButton) findViewById(R.id.minus1);
        this.minus2 = (ImageButton) findViewById(R.id.minus2);
        this.plus1 = (ImageButton) findViewById(R.id.plus1);
        this.plus2 = (ImageButton) findViewById(R.id.plus2);
        checkInstance(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//Check if the correct item was clicked
        if (item.getItemId() == R.id.night_mode) {
            AppCompatDelegate.getDefaultNightMode();
//Get the night mode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
//Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//Save the scores
        outState.putInt(STATE_SCORE_1, this.score1Value);
        outState.putInt(STATE_SCORE_2, this.score2Value);
        super.onSaveInstanceState(outState);
    }

    public void decreaseScore(View v) {

        if (v.getId() == this.minus1.getId() && this.score1Value > 0) {
            this.score1Value -= 1;
            this.score1.setText(Integer.toString(this.score1Value));
        } else if (v.getId() == this.minus2.getId() && this.score2Value > 0) {
            this.score2Value -= 1;
            this.score2.setText(Integer.toString(this.score2Value));
        }
    }

    public void increaseScore(View v) {

        if (v.getId() == this.plus1.getId()) {
            this.score1Value += 1;
            this.score1.setText(Integer.toString(this.score1Value));
        } else if (v.getId() == this.plus2.getId()) {
            this.score2Value += 1;
            this.score2.setText(Integer.toString(this.score2Value));

        }
    }

    private void checkInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.score1Value = savedInstanceState.getInt(STATE_SCORE_1);
            this.score2Value = savedInstanceState.getInt(STATE_SCORE_2);
//Set the score text views
            this.score1.setText(String.valueOf(this.score1Value));
            this.score2.setText(String.valueOf(this.score2Value));
        }
    }
}