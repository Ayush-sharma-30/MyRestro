package com.example.minerest;

public class CartItems {
    private String itemNameCart;
    private String itemPriceCart;
    private String itemQtyCart;
    private String totalpriceCart;
    private int itemimgCart;

    public CartItems(String itemNameCart, String itemPriceCart, String itemQtyCart, String totalpriceCart, int itemimgCart) {
        this.itemNameCart = itemNameCart;
        this.itemPriceCart = itemPriceCart;
        this.itemQtyCart = itemQtyCart;
        this.totalpriceCart = totalpriceCart;
        this.itemimgCart = itemimgCart;
    }

    public String getItemNameCart() { return itemNameCart; }
    public String getItemPriceCart() { return itemPriceCart; }
    public String getItemQtyCart() { return itemQtyCart; }
    public String getTotalpriceCart() { return totalpriceCart; }
    public int getItemimgCart() { return itemimgCart; }


    public void setItemNameCart(String itemNameCart) { this.itemNameCart = itemNameCart; }
    public void setItemPriceCart(String itemPriceCart) { this.itemPriceCart = itemPriceCart; }
    public void setItemQtyCart(String itemQtyCart) { this.itemQtyCart = itemQtyCart; }
    public void setTotalpriceCart(String totalpriceCart) { this.totalpriceCart = totalpriceCart; }
    public void setItemimgCart(int itemimgCart) { this.itemimgCart = itemimgCart; }
}
