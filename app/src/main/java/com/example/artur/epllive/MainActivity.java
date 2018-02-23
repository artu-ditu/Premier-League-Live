package com.example.artur.epllive;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.artur.epllive.Models.Club;
import com.example.artur.epllive.Models.Club_Table;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button tableActivityButton;
    private Button statsActivityButton;
    private Button clubActivityButton;
    private Button scheduleActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowManager.init(new FlowConfig.Builder(this).build());

        SQLite.delete(Club_Table.class);
        fillTable();

        tableActivityButton = (Button)findViewById(R.id.tableActivityButton);
        View.OnClickListener tableActivityListener = new View.OnClickListener() {
            public void onClick(View v) {
                startTableActivity();
            }
        };
        tableActivityButton.setOnClickListener(tableActivityListener);

        statsActivityButton = (Button)findViewById(R.id.statsActivityButton);
        View.OnClickListener statsActivityListener = new View.OnClickListener() {
            public void onClick(View v) {
                startStatsActivity();
            }
        };
        statsActivityButton.setOnClickListener(statsActivityListener);

        clubActivityButton = (Button)findViewById(R.id.favClubActivityButton);
        View.OnClickListener clubActivityListener = new View.OnClickListener() {
            public void onClick(View v) {
                startClubActivity();
            }
        };
        clubActivityButton.setOnClickListener(clubActivityListener);

        scheduleActivityButton = (Button)findViewById(R.id.scheduleActivityButton);
        View.OnClickListener scheduleActivityListener = new View.OnClickListener() {
            public void onClick(View v) {
                startScheduleActivity();
            }
        };
        scheduleActivityButton.setOnClickListener(scheduleActivityListener);
    }

    private void startTableActivity(){
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    private void startStatsActivity() {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    private void startClubActivity() {
        Intent intent = new Intent(this, ClubActivity.class);
        startActivity(intent);
    }

    private void startScheduleActivity() {
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }

    public void fillTable(){
        Club club = new Club();

        //region 1
        club.setId("1");
        club.setName("Arsenal");
        club.setWins(11);
        club.setDraws(6);
        club.setDefeats(6);
        club.setScored(41);
        club.setConceded(30);
        club.setPoints(39);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 2
        club.setId("2");
        club.setName("Bournemouth");
        club.setWins(6);
        club.setDraws(6);
        club.setDefeats(11);
        club.setScored(24);
        club.setConceded(35);
        club.setPoints(24);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 3
        club.setId("3");
        club.setName("Brighton");
        club.setWins(5);
        club.setDraws(8);
        club.setDefeats(10);
        club.setScored(17);
        club.setConceded(29);
        club.setPoints(23);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 4
        club.setId("4");
        club.setName("Burnley");
        club.setWins(9);
        club.setDraws(7);
        club.setDefeats(7);
        club.setScored(19);
        club.setConceded(20);
        club.setPoints(34);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 5
        club.setId("5");
        club.setName("Chelsea");
        club.setWins(14);
        club.setDraws(5);
        club.setDefeats(4);
        club.setScored(41);
        club.setConceded(16);
        club.setPoints(47);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 6
        club.setId("6");
        club.setName("Crystal Palace");
        club.setWins(6);
        club.setDraws(7);
        club.setDefeats(10);
        club.setScored(21);
        club.setConceded(33);
        club.setPoints(25);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 7
        club.setId("7");
        club.setName("Everton");
        club.setWins(7);
        club.setDraws(6);
        club.setDefeats(10);
        club.setScored(25);
        club.setConceded(38);
        club.setPoints(27);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 8
        club.setId("8");
        club.setName("Huddersfield");
        club.setWins(6);
        club.setDraws(6);
        club.setDefeats(11);
        club.setScored(19);
        club.setConceded(39);
        club.setPoints(24);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 9
        club.setId("9");
        club.setName("Leicester");
        club.setWins(8);
        club.setDraws(7);
        club.setDefeats(8);
        club.setScored(34);
        club.setConceded(32);
        club.setPoints(31);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 10
        club.setId("10");
        club.setName("Liverpool");
        club.setWins(13);
        club.setDraws(8);
        club.setDefeats(2);
        club.setScored(54);
        club.setConceded(28);
        club.setPoints(47);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 11
        club.setId("11");
        club.setName("Man City");
        club.setWins(20);
        club.setDraws(2);
        club.setDefeats(1);
        club.setScored(67);
        club.setConceded(17);
        club.setPoints(62);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 12
        club.setId("12");
        club.setName("Man United");
        club.setWins(15);
        club.setDraws(5);
        club.setDefeats(3);
        club.setScored(48);
        club.setConceded(16);
        club.setPoints(50);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 13
        club.setId("13");
        club.setName("Newcastle");
        club.setWins(6);
        club.setDraws(5);
        club.setDefeats(12);
        club.setScored(21);
        club.setConceded(31);
        club.setPoints(23);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 14
        club.setId("14");
        club.setName("Southampton");
        club.setWins(4);
        club.setDraws(9);
        club.setDefeats(10);
        club.setScored(23);
        club.setConceded(34);
        club.setPoints(21);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 15
        club.setId("15");
        club.setName("Stoke");
        club.setWins(5);
        club.setDraws(5);
        club.setDefeats(13);
        club.setScored(43);
        club.setConceded(50);
        club.setPoints(20);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 16
        club.setId("16");
        club.setName("Swansea");
        club.setWins(4);
        club.setDraws(5);
        club.setDefeats(14);
        club.setScored(14);
        club.setConceded(35);
        club.setPoints(17);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 17
        club.setId("17");
        club.setName("Tottenham");
        club.setWins(13);
        club.setDraws(5);
        club.setDefeats(5);
        club.setScored(46);
        club.setConceded(21);
        club.setPoints(44);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 18
        club.setId("18");
        club.setName("Watford");
        club.setWins(7);
        club.setDraws(5);
        club.setDefeats(11);
        club.setScored(33);
        club.setConceded(42);
        club.setPoints(26);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 19
        club.setId("19");
        club.setName("West Brom");
        club.setWins(3);
        club.setDraws(10);
        club.setDefeats(10);
        club.setScored(18);
        club.setConceded(30);
        club.setPoints(19);
        club.setFavourite(false);
        club.save();
        //endregion

        //region 20
        club.setId("20");
        club.setName("West Ham");
        club.setWins(6);
        club.setDraws(7);
        club.setDefeats(10);
        club.setScored(29);
        club.setConceded(41);
        club.setPoints(25);
        club.setFavourite(false);
        club.save();
        //endregion

    }
}