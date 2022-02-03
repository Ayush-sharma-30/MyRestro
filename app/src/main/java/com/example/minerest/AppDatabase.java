package com.example.minerest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MenuItemsTable.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract MenuItemsDao MenuItemsDao();
}
