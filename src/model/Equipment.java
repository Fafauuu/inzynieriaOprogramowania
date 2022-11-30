package model;

public class Equipment {
    private String name;
    private double costPerDay;
    private double deposit;
    private int amount;

    public Equipment(String name, double costPerDay, double deposit, int amount) {
        this.name = name;
        this.costPerDay = costPerDay;
        this.deposit = deposit;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

