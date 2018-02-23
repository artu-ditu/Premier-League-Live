package com.example.artur.epllive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artur.epllive.Adapters.MatchAdapter;
import com.example.artur.epllive.Models.Club;
import com.example.artur.epllive.Models.Club_Table;
import com.example.artur.epllive.Models.Match;
import com.inverce.mod.core.IM;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by Artur on 2018-01-15.
 */

public class ScheduleActivity extends AppCompatActivity {

    private static final String QUERY_URL = "https://fantasy.premierleague.com/drf/fixtures/?event=";

    private Button previousGameweekButton;
    private Button nextGameweekButton;

    private ListView MatchesListView;
    private ArrayList<Match> MatchesList;

    private int gameweek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Intent intent = getIntent();

        MatchesList = new ArrayList<Match>();
        MatchesListView = (ListView)findViewById(R.id.matches_list_view);

        gameweek = 24;
        final TextView gameweekText = (TextView)findViewById(R.id.gameweek_number);
        gameweekText.setText(Integer.toString(gameweek));

        queryPlayers(gameweek);

        previousGameweekButton = (Button)findViewById(R.id.previous);
        View.OnClickListener previousGameweekListener = new View.OnClickListener() {
            public void onClick(View v) {
                gameweek -= 1;
                gameweekText.setText(Integer.toString(gameweek));
                queryPlayers(gameweek);
            }
        };
        previousGameweekButton.setOnClickListener(previousGameweekListener);

        nextGameweekButton = (Button)findViewById(R.id.next);
        View.OnClickListener nextGameweekListener = new View.OnClickListener() {
            public void onClick(View v) {
                gameweek += 1;
                gameweekText.setText(Integer.toString(gameweek));
                queryPlayers(gameweek);
            }
        };
        nextGameweekButton.setOnClickListener(nextGameweekListener);
    }

    private void queryPlayers(int gameweekNumber) {

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(QUERY_URL).append(Integer.toString(gameweekNumber));
        AsyncHttpClient client = new AsyncHttpClient();

        try{
            client.get(urlStringBuilder.toString(),
                    new JsonHttpResponseHandler() {

                        @Override
                        protected Object parseResponse(String responseBody) throws JSONException {
                            if (null == responseBody)
                                return null;
                            Object result = null;
                            String jsonString = responseBody.trim();
                            if (jsonString.startsWith("{") || jsonString.startsWith("[")) {
                                result = new JSONTokener(jsonString).nextValue();
                            }
                            if (result == null) {
                                result = jsonString;
                            }
                            return result;
                        }

                        @Override
                        public void onSuccess(JSONArray jsonObjects) {
                            try {
                                getMatches(jsonObjects);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            ListAdapter customAdapter = new MatchAdapter(IM.context(), R.layout.clubs_list_fragment, MatchesList);
                            MatchesListView.setAdapter(customAdapter);
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable throwable, JSONArray error) {
                            Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("queriedMatch", statusCode + " " + throwable.getMessage());
                        }
                    });
        } catch (Exception e) {
            Log.e("WS", "ERROR: ", e);
        }

    }

    private void getMatches(JSONArray jsonObjects) throws JSONException {
        MatchesList.clear();
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newMatch = jsonObjects.getJSONObject(i);
            StringBuilder score = new StringBuilder();
            score.append(newMatch.optString("team_h_score")).append(":").append(newMatch.optString("team_a_score"));

            String date = newMatch.optString("kickoff_time").replace("T", " ");
            date = date.substring(0, date.length() - 4);

            Match queriedMatch = new Match(
                    newMatch.optString("team_h"),
                    newMatch.optString("team_a"),
                    (score.toString().equals("null:null")) ? "-:-" : score.toString(),
                    date);
            Club queriedMatchHome = new Select().from(Club.class).where(Club_Table.id.is(queriedMatch.getIdHome())).querySingle();
            queriedMatch.setNameHome(queriedMatchHome.getName());
            Club queriedMatchAway = new Select().from(Club.class).where(Club_Table.id.is(queriedMatch.getIdAway())).querySingle();
            queriedMatch.setNameAway(queriedMatchAway.getName());
            MatchesList.add(queriedMatch);
        }
    }

}