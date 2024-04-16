package com.ohgiraffers.handlermethod;


public class MenuDTO {

    private String name;
    private int Price;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO() {}

    public MenuDTO(String name, int price, int categoryCode, String orderableStatus) {
        this.name = name;
        Price = price;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", Price=" + Price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }

}
