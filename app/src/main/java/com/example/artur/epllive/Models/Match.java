package com.example.artur.epllive.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Artur on 2018-01-15.
 */

public class Match implements Serializable {

    private String idHome;
    private String idAway;
    private String nameHome;
    private String nameAway;
    private String score;
    private String dateString;

    public Match(String idHome, String idAway, String score, String dateString) {
        this.idHome = idHome;
        this.idAway = idAway;
        this.score = score;
        this.dateString = dateString;
    }

    public String getIdHome() {
        return idHome;
    }

    public void setIdHome(String idHome) {
        this.idHome = idHome;
    }

    public String getIdAway() {
        return idAway;
    }

    public void setIdAway(String idAway) {
        this.idAway = idAway;
    }

    public String getNameHome() {
        return nameHome;
    }

    public void setNameHome(String nameHome) {
        this.nameHome = nameHome;
    }

    public String getNameAway() {
        return nameAway;
    }

    public void setNameAway(String nameAway) {
        this.nameAway = nameAway;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
