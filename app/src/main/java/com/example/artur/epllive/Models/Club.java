package com.example.artur.epllive.Models;

import com.example.artur.epllive.Database.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Artur on 2018-01-14.
 */

@Table(database = MyDatabase.class, name = "tblClub")
public class Club extends BaseModel {
    @PrimaryKey
    @Column
    String id;

    @Column
    String name;

    @Column
    int wins;

    @Column
    int draws;

    @Column
    int defeats;

    @Column
    int scored;

    @Column
    int conceded;

    @Column
    int points;

    @Column
    Boolean isFavourite;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getScored() {
        return scored;
    }

    public void setScored(int scored) {
        this.scored = scored;
    }

    public int getConceded() {
        return conceded;
    }

    public void setConceded(int conceded) {
        this.conceded = conceded;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }
}
