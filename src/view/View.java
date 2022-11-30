package view;

import model.*;

import java.util.ArrayList;

public class View {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printCatalogue(ArrayList<Equipment> catalogue) {
        System.out.println("Aktualny katalog:\n");
        for (Equipment equipment : catalogue) {
            System.out.println(String.format("%-20s","Nazwa: ") + equipment.getName());
            System.out.println(String.format("%-20s","Cena za dzień: ") + equipment.getCostPerDay());
            System.out.println(String.format("%-20s","Kaucja: ") + equipment.getDeposit());
            System.out.println(String.format("%-20s","Ilość sztuk: ") + equipment.getAmount() + "\n");
        }
    }

    public void printRentals(ArrayList<Rental> rentals) {
        printMessage("Lista wypozyczen:\n");
        for (Rental rental : rentals) {
            System.out.println(
                    String.format("%-20s","Klient: ")
                    + rental.getClient().getName()
                    + " "
                    + rental.getClient().getSurname()
            );
            System.out.println(String.format("%-20s","Nazwa sprzętu: ") + rental.getEquipment().getName());
            System.out.println(String.format("%-20s","Ilosc sztuk: ") + rental.getAmount());
            System.out.println(String.format("%-20s","Pozostalo dni: ") + rental.getDaysLeft() + "\n");
        }
    }

    public void printRentalRequests(ArrayList<RentalRequest> rentalRequests) {
        printMessage("Lista prosb o wypozyczenie:\n");
        for (RentalRequest rentalRequest : rentalRequests) {
            System.out.println(
                    String.format("%-20s","Klient: ")
                            + rentalRequest.getClient().getName()
                            + " "
                            + rentalRequest.getClient().getSurname()
            );
            System.out.println(String.format("%-20s","Nazwa sprzętu: ") + rentalRequest.getEquipment().getName());
            System.out.println(String.format("%-20s","Ilosc sztuk: ") + rentalRequest.getAmount());
            System.out.println(String.format("%-20s","Ilosc dni: ") + rentalRequest.getRentalPeriod() + "\n");
        }
    }

    public void printRentalExtensionRequests(ArrayList<RentalExtensionRequest> rentalExtensionRequests) {}

    public void printEquipmentLostReports(ArrayList<EquipmentLostReport> equipmentLostReports) {}

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void showClientMenu() {}

    public void showEmployeeMenu() {}

    public String getInput() { return ""; }
}
