package com.example.minerest;

public class MenuItems {
    private String itemName;
    private String itemDesc;
    private String itemType;
    private int itemQuantity=0;
    private int itemImg;
    private int itemtypeImg;
    private int incImg;
    private int DecImg;


    public MenuItems(String itemName, String itemDesc, String itemType, int itemImg, int itemtypeImg, int itemQuantity) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.itemImg = itemImg;
        this.itemtypeImg = itemtypeImg;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }
    public String getItemDesc() {
        return itemDesc;
    }
    public String getItemType() {
        return itemType;
    }
    public int getItemQuantity() {
        return itemQuantity;
    }

    public int setItemQuantity(int itemQuantity) { this.itemQuantity = itemQuantity;
        return itemQuantity;
    }

    public int getItemImg() {
        return itemImg;
    }
    public int getItemtypeImg() {
        return itemtypeImg;
    }
    public int getIncImg() {
        return incImg;
    }
    public int getDecImg() {
        return DecImg;
    }
}
