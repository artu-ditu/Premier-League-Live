package com.example.artur.epllive.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.artur.epllive.Models.Player;
import com.example.artur.epllive.R;
import com.inverce.mod.core.IM;

import java.util.List;

/**
 * Created by Artur on 2018-01-15.
 */

public class PlayerAdapter extends ArrayAdapter<Player> {

    private List<Player> Players;

    public PlayerAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public PlayerAdapter(Context context, int resource, List<Player> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View v = LayoutInflater.from(getContext()).inflate(R.layout.players_list_fragment,null);

        final Player player = getItem(position);

        if (player != null) {
            TextView cposition = (TextView)v.findViewById(R.id.player_position);
            TextView name = (TextView)v.findViewById(R.id.player_name);
            TextView club = (TextView)v.findViewById(R.id.player_club);
            TextView number = (TextView)v.findViewById(R.id.player_number);
            TextView score = (TextView)v.findViewById(R.id.player_score);

            if (cposition!= null) {
                cposition.setText(Integer.toString(position+1));
            }

            if (name!= null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(player.getName());
                stringBuilder.append(" ");
                stringBuilder.append(player.getSurname());
                name.setText(stringBuilder.toString());
            }

            if (club != null) {
                club.setText(player.getClub());
            }

            if (number != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(", #");
                stringBuilder.append(Integer.toString(player.getNumber()));
                number.setText(stringBuilder);
            }

            if (score != null) {
                score.setText(Integer.toString(player.getScore()));
            }
        }

        return v;
    }
}
