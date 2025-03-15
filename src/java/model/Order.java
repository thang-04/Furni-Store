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
public class Order {
    private int oId;
    private Date date;
    private int userId;
    private double totalMoney;
    private int status;

    public Order() {
    }

    public Order(int oId, Date date, int userId, double totalMoney, int status) {
        this.oId = oId;
        this.date = date;
        this.userId = userId;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    public int getOId() {  
    return oId;
}

    public void setOId(int oId) {
        this.oId = oId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
    
}
