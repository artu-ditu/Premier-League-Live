package com.example.artur.epllive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.artur.epllive.Adapters.ClubAdapter;
import com.example.artur.epllive.Models.Club;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Artur on 2018-01-14.
 */

public class TableActivity extends AppCompatActivity {

    private ListView ClubsListView;
    private List<Club> ClubsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Intent intent = getIntent();

        GetClubs();
        ClubsListView = (ListView)findViewById(R.id.clubs_list_view);
        ListAdapter customAdapter = new ClubAdapter(this, R.layout.clubs_list_fragment, ClubsList);
        ClubsListView.setAdapter(customAdapter);
    }

    private void GetClubs() {
        ClubsList = new Select().from(Club.class).queryList();

        Collections.sort(ClubsList, new Comparator<Club>() {
            @Override
            public int compare(Club club1, Club club2) {
                int club2dif = club2.getScored() - club2.getConceded();
                int club1dif = club1.getScored() - club1.getConceded();
                return club2dif - club1dif;
            }
        });

        Collections.sort(ClubsList, new Comparator<Club>() {
            @Override
            public int compare(Club club1, Club club2) {
                return club2.getPoints() - club1.getPoints();
            }
        });
    }
}
