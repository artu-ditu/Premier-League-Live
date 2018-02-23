package com.example.artur.epllive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artur.epllive.Adapters.PlayerAdapter;
import com.example.artur.epllive.Models.Club;
import com.example.artur.epllive.Models.Club_Table;
import com.example.artur.epllive.Models.Player;
import com.inverce.mod.core.IM;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Artur on 2018-01-15.
 */

public class ShowStatsActivity extends AppCompatActivity {

    private static final String QUERY_URL = "https://fantasy.premierleague.com/drf/elements/";

    TextView textShowStats;

    private ListView PlayersListView;
    ArrayList<Player> PlayersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);
        Intent intent = getIntent();

        textShowStats = (TextView)findViewById(R.id.textShowStats);

        PlayersList = new ArrayList<Player>();

        String statType;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                statType = null;
            } else {
                statType = extras.getString("statType");
            }
        } else {
            statType = (String) savedInstanceState.getSerializable("statType");
        }

        switch (statType){
            case "scorers":
                textShowStats.setText(R.string.topScorers);
                break;
            case "assistants":
                textShowStats.setText(R.string.topAssistants);
                break;
            case "yellowCards":
                textShowStats.setText(R.string.mostYellow);
                break;
            case "redCards":
                textShowStats.setText(R.string.mostRed);
                break;
            case "saves":
                textShowStats.setText(R.string.mostSaves);
                break;
            case "cleanSheets":
                textShowStats.setText(R.string.mostCleanSheets);
                break;
            default:
                break;
        }

        queryPlayers(statType);
    }

    private void queryPlayers(final String statType) {

        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append(QUERY_URL);
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
                                switch (statType){
                                    case "scorers":
                                        getScorers(jsonObjects);
                                        break;
                                    case "assistants":
                                        getAssistants(jsonObjects);
                                        break;
                                    case "yellowCards":
                                        getYellowCards(jsonObjects);
                                        break;
                                    case "redCards":
                                        getRedCards(jsonObjects);
                                        break;
                                    case "saves":
                                        getSaves(jsonObjects);
                                        break;
                                    case "cleanSheets":
                                        getCleanSheets(jsonObjects);
                                        break;
                                    default:
                                        break;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            sortPlayers();
                            getFirstTwenty();

                            PlayersListView = (ListView)findViewById(R.id.players_list_view);
                            ListAdapter customAdapter = new PlayerAdapter(IM.context(), R.layout.clubs_list_fragment, PlayersList);
                            PlayersListView.setAdapter(customAdapter);
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable throwable, JSONArray error) {
                            Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("queriedScorer", statusCode + " " + throwable.getMessage());
                        }
                    });
        } catch (Exception e) {
            Log.e("WS", "ERROR: ", e);
        }

    }

    private void getScorers(JSONArray jsonObjects) throws JSONException {
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("goals_scored")) > 5){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("goals_scored")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(newPlayer.optString("team"))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                PlayersList.add(queriedPlayer);
            }
        }
    }

    private void getAssistants(JSONArray jsonObjects) throws JSONException {
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("assists")) > 3){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("assists")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(newPlayer.optString("team"))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                PlayersList.add(queriedPlayer);
            }
        }
    }

    private void getYellowCards(JSONArray jsonObjects) throws JSONException {
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("yellow_cards")) > 3){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("yellow_cards")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(newPlayer.optString("team"))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                PlayersList.add(queriedPlayer);
            }
        }
    }

    private void getRedCards(JSONArray jsonObjects) throws JSONException {
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("red_cards")) > 0){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("red_cards")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(newPlayer.optString("team"))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                PlayersList.add(queriedPlayer);
            }
        }
    }

    private void getSaves(JSONArray jsonObjects) throws JSONException {
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("element_type")) == 1){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("saves")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(newPlayer.optString("team"))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                PlayersList.add(queriedPlayer);
            }
        }
    }

    private void getCleanSheets(JSONArray jsonObjects) throws JSONException {
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("element_type")) == 1){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("clean_sheets")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(newPlayer.optString("team"))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                PlayersList.add(queriedPlayer);
            }
        }
    }

    private void sortPlayers(){
        Collections.sort(PlayersList, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player2.getScore() - player1.getScore();
            }
        });
    }

    private void getFirstTwenty() {
        ArrayList<Player> firstTwenty = new ArrayList<Player>();
        for(int i = 0; i < 20; i++){
            firstTwenty.add(PlayersList.get(i));
        }
        PlayersList = firstTwenty;
    }

}
