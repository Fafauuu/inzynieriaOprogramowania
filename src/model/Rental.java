package model;

public class Rental {
    private final Client client;
    private final Equipment equipment;
    private final int amount;
    private int daysLeft;

    public Rental(Client client, Equipment equipment, int amount, int daysLeft) {
        this.client = client;
        this.equipment = equipment;
        this.amount = amount;
        this.daysLeft = daysLeft;
    }

    public Client getClient() {
        return client;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getAmount() {
        return amount;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }
}
