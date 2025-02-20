/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class OrderDetail {

    private int detaidId;
    private double price;
    private int quantity;
    private int pid;

    public OrderDetail() {
    }

    public OrderDetail(int detaidId, double price, int quantity, int pid) {
        this.detaidId = detaidId;
        this.price = price;
        this.quantity = quantity;
        this.pid = pid;
    }

    public int getDetaidId() {
        return detaidId;
    }

    public void setDetaidId(int detaidId) {
        this.detaidId = detaidId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    
}
