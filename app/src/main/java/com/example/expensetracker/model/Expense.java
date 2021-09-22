package com.example.expensetracker.model;

public class Expense {
    private int id;
    private String detail;
    private String amount;
    private String check_for_update;
    private String check_for_in_out;
    private String hour;
    private String min;
    private String sec;
    private String day;
    private String month;
    private String year;
    private String date;

    public Expense(int id, String detail, String amount, String check_for_update, String check_for_in_out, String hour, String min, String sec, String day, String month, String year, String date) {
        this.id = id;
        this.detail = detail;
        this.amount = amount;
        this.check_for_update = check_for_update;
        this.check_for_in_out = check_for_in_out;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = date;
    }

    public Expense(String detail, String amount, String check_for_update, String check_for_in_out, String hour, String min, String sec, String day, String month, String year, String date) {
        this.detail = detail;
        this.amount = amount;
        this.check_for_update = check_for_update;
        this.check_for_in_out = check_for_in_out;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.day = day;
        this.month = month;
        this.year = year;
        this.date = date;
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

    public String getCheck_for_in_out() {
        return check_for_in_out;
    }

    public String getHour() {
        return hour;
    }

    public String getMin() {
        return min;
    }

    public String getSec() {
        return sec;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
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
    public void setCheck_for_in_out(String check_for_in_out) {
        this.check_for_in_out = check_for_in_out;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
