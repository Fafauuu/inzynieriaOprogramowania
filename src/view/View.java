package view;

import model.*;

import java.util.ArrayList;

public class View {

    public void printMessage(String message) {}

    public void printCatalogue(ArrayList<Equipment> catalogue) {}

    public void clear() {}

    public void showClientMenu() {}

    public void showEmployeeMenu() {}

    public void printRentals(ArrayList<Rental> rentals) {}

    public void printRentalRequests(ArrayList<RentalRequest> rentalRequests) {}

    public void printRentalExtensionRequests(ArrayList<RentalExtensionRequest> rentalExtensionRequests) {}

    public void printEquipmentLostReports(ArrayList<EquipmentLostReport> equipmentLostReports) {}

    public String getInput() { return ""; }
}
