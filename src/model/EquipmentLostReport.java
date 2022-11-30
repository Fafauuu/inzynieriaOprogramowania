package model;

public class EquipmentLostReport {
    private final Rental rental;

    public EquipmentLostReport(Rental rental) {
        this.rental = rental;
    }

    public Rental getRental() {
        return rental;
    }
}
