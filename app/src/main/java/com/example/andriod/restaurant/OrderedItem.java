package com.example.andriod.restaurant;

/**
 * Created by Deepak on 7/15/2017.
 */

public class OrderedItem
{
    private String mItemname;
    private String mPrice;
    private String mQuantity;

    public OrderedItem(String itemName, String price, String quantity)
    {
        mItemname = itemName;
        mPrice = price;
        mQuantity =quantity;
    }

    public String getItemName() {
        return mItemname;
    }

    public String getPrice(){
        return  mPrice;
    }
    public String getQuantity(){
        return mQuantity;
    }
    public void setQuantity(String quantity)
    {
        mQuantity = quantity;
    }
}
