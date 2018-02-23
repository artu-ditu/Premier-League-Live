package com.example.artur.epllive.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artur.epllive.Models.Match;
import com.example.artur.epllive.R;
import com.inverce.mod.core.IM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artur on 2018-01-15.
 */

public class MatchAdapter extends ArrayAdapter<Match> {

    private ArrayList<Match> Matches;

    public MatchAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MatchAdapter(Context context, int resource, List<Match> items) {
        super(context, resource, items);
    }
    public void update(ArrayList<Match> newlist) {
        Matches.clear();
        Matches.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View v = LayoutInflater.from(getContext()).inflate(R.layout.matches_list_fragment,null);

        final Match match = getItem(position);

        if (match != null) {
            TextView date = (TextView)v.findViewById(R.id.match_date);
            ImageView homeLogo = (ImageView)v.findViewById(R.id.match_home_logo);
            TextView homeText = (TextView)v.findViewById(R.id.match_home);
            TextView score = (TextView)v.findViewById(R.id.match_score);
            ImageView awayLogo = (ImageView)v.findViewById(R.id.match_away_logo);
            TextView awayText = (TextView)v.findViewById(R.id.match_away);

            if (date!= null) {
                date.setText(match.getDateString());
            }

            if (homeLogo!= null) {
                homeLogo.setImageResource(IM.context().getResources().getIdentifier(match.getNameHome().toLowerCase().replaceAll(" ","_"), "drawable", IM.context().getPackageName()));
            }

            if (homeText!= null) {
                homeText.setText(match.getNameHome());
            }

            if (score != null) {
                score.setText(match.getScore());
            }

            if (awayLogo != null) {
                awayLogo.setImageResource(IM.context().getResources().getIdentifier(match.getNameAway().toLowerCase().replaceAll(" ","_"), "drawable", IM.context().getPackageName()));
            }

            if (awayText!= null) {
                awayText.setText(match.getNameAway());
            }
        }

        return v;
    }

}
