package model;

public class RentalExtensionRequest {
    private final Rental rental;

    public RentalExtensionRequest(Rental rental) {
        this.rental = rental;
    }

    public Rental getRental() {
        return rental;
    }
}
