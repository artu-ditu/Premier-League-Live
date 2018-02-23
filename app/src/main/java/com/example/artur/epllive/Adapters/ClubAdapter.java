package com.example.artur.epllive.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artur.epllive.Models.Club;
import com.example.artur.epllive.R;

import java.util.List;

import com.inverce.mod.core.IM;

import org.w3c.dom.Text;

/**
 * Created by Artur on 2018-01-14.
 */

public class ClubAdapter extends ArrayAdapter<Club> {

    private List<Club> Clubs;

    public ClubAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ClubAdapter(Context context, int resource, List<Club> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View v = LayoutInflater.from(getContext()).inflate(R.layout.clubs_list_fragment,null);

        final Club club = getItem(position);

        if (club != null) {
            TextView cposition = (TextView)v.findViewById(R.id.club_position);
            TextView name = (TextView)v.findViewById(R.id.club_name);
            TextView played = (TextView)v.findViewById(R.id.club_played);
            TextView won = (TextView)v.findViewById(R.id.club_won);
            TextView drawn = (TextView)v.findViewById(R.id.club_drawn);
            TextView lost = (TextView)v.findViewById(R.id.club_lost);
            TextView scored = (TextView)v.findViewById(R.id.club_scored);
            TextView conceded = (TextView)v.findViewById(R.id.club_conceded);
            TextView difference = (TextView)v.findViewById(R.id.club_difference);
            TextView points = (TextView)v.findViewById(R.id.club_points);
            ImageView logo = (ImageView)v.findViewById(R.id.club_logo);

            if (cposition!= null) {
                cposition.setText(Integer.toString(position+1));
            }

            if (name!= null) {
                name.setText(club.getName());
            }

            if (played != null) {
                played.setText(Integer.toString(club.getWins()+club.getDraws()+club.getDefeats()));
            }

            if (won != null) {
                won.setText(Integer.toString(club.getWins()));
            }

            if (drawn != null) {
                drawn.setText(Integer.toString(club.getDraws()));
            }

            if (lost != null) {
                lost.setText(Integer.toString(club.getDefeats()));
            }

            if (scored != null) {
                scored.setText(Integer.toString(club.getScored()));
            }

            if (conceded != null) {
                conceded.setText(Integer.toString(club.getConceded()));
            }

            if (difference != null) {
                difference.setText(Integer.toString(club.getScored()-club.getConceded()));
            }

            if (points != null) {
                points.setText(Integer.toString(club.getPoints()));
            }

            if (logo != null) {
                logo.setImageResource(IM.context().getResources().getIdentifier(club.getName().toLowerCase().replaceAll(" ","_"), "drawable", IM.context().getPackageName()));
            }
        }

        return v;
    }
}
