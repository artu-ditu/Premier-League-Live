package com.example.artur.epllive.Database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Artur on 2018-01-14.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDatabaseDB";
    public static final int VERSION = 1;
}