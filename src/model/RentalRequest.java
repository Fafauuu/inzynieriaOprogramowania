package model;

public class RentalRequest {
    private final Client client;
    private final Equipment equipment;
    private final int amount;
    private final int rentalPeriod;

    public RentalRequest(Client client, Equipment equipment, int amount, int rentalPeriod) {
        this.equipment = equipment;
        this.client = client;
        this.amount = amount;
        this.rentalPeriod = rentalPeriod;
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

    public int getRentalPeriod() {
        return rentalPeriod;
    }
}
