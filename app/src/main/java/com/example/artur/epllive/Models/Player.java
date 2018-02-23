package com.example.artur.epllive.Models;

import java.io.Serializable;

/**
 * Created by Artur on 2018-01-15.
 */

public class Player implements Serializable {

    private String name;
    private String surname;
    private String club;
    private int clubId;
    private int number;
    private int score;
    private int score2;
    private int position;

    public Player(String name, String surname, String club, int clubId, int number, int score) {
        this.name = name;
        this.surname = surname;
        this.club = club;
        this.clubId = clubId;
        this.number = number;
        this.score = score;
        this.score2 = score;
    }

    public Player(String name, String surname, int clubId, int number, int score) {
        this.name = name;
        this.surname = surname;
        this.club = "brak";
        this.clubId = clubId;
        this.number = number;
        this.score = score;
        this.score2 = score;
    }

    public Player(String name, String surname, int clubId, int number, int score, int score2, int position) {
        this.name = name;
        this.surname = surname;
        this.club = "brak";
        this.clubId = clubId;
        this.number = number;
        this.score = score;
        this.score2 = score2;
        this.position = position;
    }

    public Player(String name, String surname, String club, int clubId, int number, int score, int score2, int position) {
        this.name = name;
        this.surname = surname;
        this.club = club;
        this.clubId = clubId;
        this.number = number;
        this.score = score;
        this.score2 = score2;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
