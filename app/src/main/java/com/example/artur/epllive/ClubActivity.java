package com.example.artur.epllive;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artur.epllive.Adapters.PlayerAdapter;
import com.example.artur.epllive.Adapters.SquadAdapter;
import com.example.artur.epllive.Models.Club;
import com.example.artur.epllive.Models.Club_Table;
import com.example.artur.epllive.Models.Player;
import com.example.artur.epllive.Sensors.ShakeDetector;
import com.inverce.mod.core.IM;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by Artur on 2018-01-15.
 */

public class ClubActivity extends AppCompatActivity {

    private static final String QUERY_URL = "https://fantasy.premierleague.com/drf/elements/";

    private ListView SquadListView;
    ArrayList<Player> SquadList;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    TextView headerText;
    TextView favName;
    ImageView favLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        Intent intent = getIntent();

        SquadList = new ArrayList<Player>();
        SquadListView = (ListView)findViewById(R.id.squad_list_view);

        headerText = (TextView)findViewById(R.id.fav_club_text);
        favName = (TextView)findViewById(R.id.fav_name);
        favLogo = (ImageView)findViewById(R.id.fav_logo);
        getFavClub();

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                handleShakeEvent(count);
            }
        });
    }

    private void getFavClub() {
        List<Club> ClubsList = new Select().from(Club.class).where(Club_Table.isFavourite.is(true)).queryList();

        if(ClubsList.size() == 0) {
            headerText.setText(R.string.FavClubFalse);
        }
        else {
            favName.setText(ClubsList.get(0).getName());
            favLogo.setImageResource(IM.context().getResources().getIdentifier(ClubsList.get(0).getName().toLowerCase().replaceAll(" ","_"), "drawable", IM.context().getPackageName()));
            queryPlayers(Integer.parseInt(ClubsList.get(0).getId()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    private void handleShakeEvent(int count) {
        headerText.setText(R.string.FavClubTrue);
        List<Club> ClubsList = new Select().from(Club.class).queryList();

        Random randomizer = new Random();
        Club randomClub = ClubsList.get(randomizer.nextInt(ClubsList.size()));

        Toast.makeText(IM.context(),"Wylosowałeś nowy klub!",Toast.LENGTH_SHORT).show();

        for(int i = 1; i<21; i++){
            String idTemp = Integer.toString(i);
            Club tempClub = new Select().from(Club.class).where(Club_Table.id.is(idTemp)).querySingle();
            tempClub.setFavourite(false);
            tempClub.save();
        }

        randomClub.setFavourite(true);
        randomClub.save();
        favName.setText(randomClub.getName());
        favLogo.setImageResource(IM.context().getResources().getIdentifier(randomClub.getName().toLowerCase().replaceAll(" ","_"), "drawable", IM.context().getPackageName()));
        queryPlayers(Integer.parseInt(randomClub.getId()));
    }

    private void queryPlayers(final int club) {

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
                                getSquad(jsonObjects, club);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            sortPlayers();

                            ListAdapter customAdapter = new SquadAdapter(IM.context(), R.layout.squad_list_fragment, SquadList);
                            SquadListView.setAdapter(customAdapter);
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable throwable, JSONArray error) {
                            Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("queriedSquad", statusCode + " " + throwable.getMessage());
                        }
                    });
        } catch (Exception e) {
            Log.e("WS", "ERROR: ", e);
        }

    }

    private void getSquad(JSONArray jsonObjects, int club) throws JSONException {
        SquadList.clear();
        for (int i = 0; i < jsonObjects.length(); i++){
            JSONObject newPlayer = jsonObjects.getJSONObject(i);
            if(Integer.parseInt(newPlayer.optString("team")) == club){
                Player queriedPlayer = new Player(
                        newPlayer.optString("first_name"),
                        newPlayer.optString("second_name"),
                        Integer.parseInt(newPlayer.optString("team")),
                        Integer.parseInt(newPlayer.optString("squad_number")),
                        Integer.parseInt(newPlayer.optString("goals_scored")),
                        Integer.parseInt(newPlayer.optString("assists")),
                        Integer.parseInt(newPlayer.optString("element_type")));
                Club queriedPlayerClub = new Select().from(Club.class).where(Club_Table.id.is(Integer.toString(club))).querySingle();
                queriedPlayer.setClub(queriedPlayerClub.getName());
                SquadList.add(queriedPlayer);
            }
        }
    }

    private void sortPlayers(){
        Collections.sort(SquadList, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getNumber() - player2.getNumber();
            }
        });

        Collections.sort(SquadList, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                return player1.getPosition() - player2.getPosition();
            }
        });
    }

}