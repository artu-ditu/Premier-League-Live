package com.example.artur.epllive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Artur on 2018-01-15.
 */

public class StatsActivity extends AppCompatActivity {

    private Button scorersButton;
    private Button assistantsButton;
    private Button yellowCardsButton;
    private Button redCardsButton;
    private Button savesButton;
    private Button cleanSheetsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Intent intent = getIntent();

        scorersButton = (Button)findViewById(R.id.scorersButton);
        View.OnClickListener scorersButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                startShowStatsActivity("scorers");
            }
        };
        scorersButton.setOnClickListener(scorersButtonListener);

        assistantsButton = (Button)findViewById(R.id.assistantsButton);
        View.OnClickListener assistantsButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                startShowStatsActivity("assistants");
            }
        };
        assistantsButton.setOnClickListener(assistantsButtonListener);

        yellowCardsButton = (Button)findViewById(R.id.yellowCardsButton);
        View.OnClickListener yellowCardsButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                startShowStatsActivity("yellowCards");
            }
        };
        yellowCardsButton.setOnClickListener(yellowCardsButtonListener);

        redCardsButton = (Button)findViewById(R.id.redCardsButton);
        View.OnClickListener redCardsButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                startShowStatsActivity("redCards");
            }
        };
        redCardsButton.setOnClickListener(redCardsButtonListener);

        savesButton = (Button)findViewById(R.id.savesButton);
        View.OnClickListener savesButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                startShowStatsActivity("saves");
            }
        };
        savesButton.setOnClickListener(savesButtonListener);

        cleanSheetsButton = (Button)findViewById(R.id.cleanSheetsButton);
        View.OnClickListener cleanSheetsButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                startShowStatsActivity("cleanSheets");
            }
        };
        cleanSheetsButton.setOnClickListener(cleanSheetsButtonListener);
    }

    private void startShowStatsActivity(String statType) {
        Intent intent = new Intent(this, ShowStatsActivity.class);
        intent.putExtra("statType", statType);
        startActivity(intent);
    }
}
