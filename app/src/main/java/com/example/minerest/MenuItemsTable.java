package com.example.minerest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MenuItemsTable {

    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "pname")
    public String pname;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "price")
    public int price;

    @ColumnInfo(name = "qnt")
    public int qnt;

    public MenuItemsTable(int pid, String pname, String type, int price, int qnt) {
        this.pid = pid;
        this.pname = pname;
        this.type=type;
        this.price = price;
        this.qnt = qnt;
    }

    public int getPid() { return pid; }

    public void setPid(int pid) { this.pid = pid; }

    public String getPname() { return pname; }

    public void setPname(String pname) { this.pname = pname; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public int getQnt() { return qnt; }

    public void setQnt(int qnt) { this.qnt = qnt; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
