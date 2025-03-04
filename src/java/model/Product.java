/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Product {

    private int pId;
    private String pName;
    private String image;
    private Double price;
    private String description;
    private int quantity;
    private Date importDate;
    private int categoryId;
    private int sell_id;

    public Product() {
    }

    public Product(int pId, String pName, String image, Double price, String description, int quantity, Date importDate, int categoryId, int sell_id) {
        this.pId = pId;
        this.pName = pName;
        this.image = image;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.importDate = importDate;
        this.categoryId = categoryId;
        this.sell_id = sell_id;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSell_id() {
        return sell_id;
    }

    public void setSell_id(int sell_id) {
        this.sell_id = sell_id;
    }

    public Product(int pId, String pName, String image, Double price, String description, int quantity) {
        this.pId = pId;
        this.pName = pName;
        this.image = image;
        this.price = price;
        this.description = description;
        this.quantity = quantity;

    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
