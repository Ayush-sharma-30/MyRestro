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
    private int price;


    public MenuItems(String itemName, String itemDesc, String itemType, int itemImg, int itemtypeImg, int itemQuantity, int price) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.itemImg = itemImg;
        this.itemtypeImg = itemtypeImg;
        this.itemQuantity = itemQuantity;
        this.price=price;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

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

    public int setItemQuantity(int itemQuantity) { this.itemQuantity = itemQuantity;return itemQuantity; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setItemDesc(String itemDesc) { this.itemDesc = itemDesc; }
    public void setItemType(String itemType) { this.itemType = itemType; }
    public void setItemImg(int itemImg) { this.itemImg = itemImg; }
    public void setItemtypeImg(int itemtypeImg) { this.itemtypeImg = itemtypeImg; }
    public void setIncImg(int incImg) { this.incImg = incImg; }
    public void setDecImg(int decImg) { DecImg = decImg; }


}
