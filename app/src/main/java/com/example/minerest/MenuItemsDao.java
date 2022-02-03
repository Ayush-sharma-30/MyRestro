package com.example.minerest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MenuItemsDao {
    @Insert
    void insertrecord(MenuItemsTable menuItemsTable);


    @Query("SELECT EXISTS(SELECT * FROM MenuItemsTable WHERE pid = :productid)")
    Boolean is_exist(int productid);


    @Query("SELECT * FROM MenuItemsTable")
    List<MenuItemsTable> getallproduct();

    @Query("DELETE FROM MenuItemsTable WHERE pid = :id")
    void deleteById(int id);
}
