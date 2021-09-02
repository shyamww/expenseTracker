package com.example.expensetracker.model;

public class Expense {
    private int id;
    private String detail;
    private String amount;
    private String check_for_update;

    public Expense(int id, String detail, String amount, String check_for_update) {
        this.id = id;
        this.detail = detail;
        this.amount = amount;
        this.check_for_update = check_for_update;
    }

    public Expense(String detail, String amount, String check_for_update) {
        this.detail = detail;
        this.amount = amount;
        this.check_for_update = check_for_update;
    }
    public Expense() {

    }


    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getAmount() {
        return amount;
    }

    public String getCheck_for_update() {
        return check_for_update;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCheck_for_update(String check_for_update) {
        this.check_for_update = check_for_update;
    }
}
