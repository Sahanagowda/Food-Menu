package com.cs442.sbasappa_a3;

/**
 * Created by Sahana on 10/4/2015.
 */
public class Item {
    private int id;
    private String name;
    private String price;

    private String shortDesc;
    private String ingredients;
    private String cookingStyle;


    public Item(){
    }

    public Item(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Item(int id, String name, String price, String ingredients, String receipe) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.cookingStyle = receipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getCookingStyle() {
        return cookingStyle;
    }

    public void setCookingStyle(String cookingStyle) {
        this.cookingStyle = cookingStyle;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}