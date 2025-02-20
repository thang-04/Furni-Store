/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Cart {

    private List<Item> listItems;

    public Cart() {
        listItems = new ArrayList<>();
    }

    public List<Item> getItems() {
        return listItems;
    }

    private Item getItemByID(int id) {
        for (Item item : listItems) {
            if (item.getProduct().getpId() == id) {
                return item;
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        return getItemByID(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemByID(t.getProduct().getpId()) != null) {
            Item m = getItemByID(t.getProduct().getpId());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            listItems.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemByID(id) != null) {
            listItems.remove(getItemByID(id));
        }
    }

    // lay gia san pham ban dau - gia chua sale
    public double getTotalPriceWithOutDiscount() {
        double total = 0;
        for (Item item : listItems) {
            total += Math.round(item.getQuantity() * item.getProduct().getPrice() * 100) / 100.0;
        }
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    // lay gia ban - gia san pham da sale
    public double getTotalMoney() {
        double total = 0;
        for (Item item : listItems) {
            double itemTotal = item.getQuantity() * item.getPrice();
            total += Math.round(itemTotal * 100.0) / 100.0;
        }
        return Math.round(total * 100.0) / 100.0;
    }

    // 
    public List<Product> getlistProducts() {
        List<Product> list = new ArrayList<>();
        for (Item item : listItems) {
            list.add(item.getProduct());
        }
        return list;
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product i : list) {
            if (i.getpId() == id) {
                return i;
            }
        }
        return null;
    }

    public Cart(String txt, List<Product> list) {
        listItems = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("\\|");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    Product p = getProductById(id, list);
                    Item t = new Item(p, quantity, p.getPrice() * 2);
                    addItem(t);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
