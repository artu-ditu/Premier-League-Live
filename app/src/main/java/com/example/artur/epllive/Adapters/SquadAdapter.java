package com.example.artur.epllive.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.artur.epllive.Models.Player;
import com.example.artur.epllive.R;

import java.util.List;

/**
 * Created by Artur on 2018-01-15.
 */

public class SquadAdapter extends ArrayAdapter<Player> {

    private List<Player> Players;

    public SquadAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public SquadAdapter(Context context, int resource, List<Player> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View v = LayoutInflater.from(getContext()).inflate(R.layout.squad_list_fragment,null);

        final Player player = getItem(position);

        if (player != null) {
            TextView cposition = (TextView)v.findViewById(R.id.player_position);
            TextView name = (TextView)v.findViewById(R.id.player_name);
            TextView number = (TextView)v.findViewById(R.id.player_number);
            TextView score = (TextView)v.findViewById(R.id.player_goals);
            TextView score2 = (TextView)v.findViewById(R.id.player_assists);

            if (cposition!= null) {
                if (player.getPosition()==1) cposition.setText(R.string.Goalkeeper);
                else if (player.getPosition()==2) cposition.setText(R.string.Defender);
                else if (player.getPosition()==3) cposition.setText(R.string.Midfielder);
                else cposition.setText(R.string.Forward);
            }

            if (name!= null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(player.getName());
                stringBuilder.append(" ");
                stringBuilder.append(player.getSurname());
                name.setText(stringBuilder.toString());
            }

            if (number != null) {
                number.setText(Integer.toString(player.getNumber()));
            }

            if (score != null) {
                score.setText(Integer.toString(player.getScore()));
            }

            if (score2 != null) {
                score2.setText(Integer.toString(player.getScore2()));
            }
        }

        return v;
    }
}
