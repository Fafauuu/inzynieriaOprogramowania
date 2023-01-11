package view;

import model.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printCatalogue(ArrayList<Equipment> catalogue) {
        printMessage("Aktualny katalog:\n");
        int equipmentNumber = 1;
        for (Equipment equipment : catalogue) {
            printMessage(String.format("%-20s", "Numer w katalogu: ") + equipmentNumber);
            printMessage(String.format("%-20s", "Nazwa: ") + equipment.getName());
            printMessage(String.format("%-20s", "Cena za dzień: ") + equipment.getCostPerDay());
            printMessage(String.format("%-20s", "Kaucja: ") + equipment.getDeposit());
            printMessage(String.format("%-20s", "Ilość sztuk: ") + equipment.getAmount() + "\n");
            equipmentNumber++;
        }
    }

    public void printRentals(ArrayList<Rental> rentals) {
        printMessage("Lista wypozyczen:\n");
        int rentalNumber = 1;
        for (Rental rental : rentals) {
            printMessage(String.format("%-20s", "Numer wypozyczenia: ") + rentalNumber);
            printMessage(
                    String.format("%-20s", "Klient: ")
                            + rental.getClient().getName()
                            + " "
                            + rental.getClient().getSurname()
            );
            printMessage(String.format("%-20s", "Nazwa sprzętu: ") + rental.getEquipment().getName());
            printMessage(String.format("%-20s", "Ilosc sztuk: ") + rental.getAmount());
            printMessage(String.format("%-20s", "Pozostalo dni: ") + rental.getDaysLeft() + "\n");
            rentalNumber++;
        }
    }

    public void printRentalRequests(ArrayList<RentalRequest> rentalRequests) {
        printMessage("Lista prosb o wypozyczenie:\n");
        int rentalRequestNumber = 1;
        for (RentalRequest rentalRequest : rentalRequests) {
            printMessage(String.format("%-20s", "Numer prosby: ") + rentalRequestNumber);
            printMessage(
                    String.format("%-20s", "Klient: ")
                            + rentalRequest.getClient().getName()
                            + " "
                            + rentalRequest.getClient().getSurname()
            );
            printMessage(String.format("%-20s", "Nazwa sprzętu: ") + rentalRequest.getEquipment().getName());
            printMessage(String.format("%-20s", "Ilosc sztuk: ") + rentalRequest.getAmount());
            printMessage(String.format("%-20s", "Ilosc dni: ") + rentalRequest.getRentalPeriod() + "\n");
            rentalRequestNumber++;
        }
    }

    public void printRentalExtensionRequests(ArrayList<RentalExtensionRequest> rentalExtensionRequests) {
        printMessage("Lista prosb o przedluzenie wypozyczenia:\n");
        int rentalExtensionRequestNumber = 1;
        for (RentalExtensionRequest request : rentalExtensionRequests) {
            printMessage(String.format("%-20s", "Numer prosby: ") + rentalExtensionRequestNumber);
            printMessage(
                    String.format("%-20s", "Klient: ")
                            + request.getRental().getClient().getName()
                            + " "
                            + request.getRental().getClient().getSurname()
            );
            printMessage(String.format("%-20s", "Nazwa sprzętu: ") + request.getRental().getEquipment().getName());
            printMessage(String.format("%-20s", "Ilosc sztuk: ") + request.getRental().getAmount());
            printMessage(String.format("%-20s", "Ilosc dni: ") + request.getRental().getDaysLeft() + "\n");
            rentalExtensionRequestNumber++;
        }
    }

    public void printEquipmentLostReports(ArrayList<EquipmentLostReport> equipmentLostReports) {
        printMessage("Lista zgloszen zgubienia sprzetu:\n");
        int equipmentLostReportNumber = 1;
        for (EquipmentLostReport lostReport : equipmentLostReports) {
            printMessage(String.format("%-20s", "Numer zgloszenia: ") + equipmentLostReportNumber);
            printMessage(
                    String.format("%-20s", "Klient: ")
                            + lostReport.getRental().getClient().getName()
                            + " "
                            + lostReport.getRental().getClient().getSurname()
            );
            printMessage(String.format("%-20s", "Nazwa sprzętu: ") + lostReport.getRental().getEquipment().getName());
            printMessage(String.format("%-20s", "Ilosc sztuk: ") + lostReport.getRental().getAmount());
            printMessage(String.format("%-20s", "Kaucja: ") + lostReport.getRental().getEquipment().getDeposit() + "\n");
            equipmentLostReportNumber++;
        }
    }

    public void clear() {
        printMessage("\033[H\033[2J");
        System.out.flush();
    }

    public void showClientMenu() {
        printMessage("1. Wyświetl katalog");
        printMessage("2. Wyświetl swoje wypożyczenia");
        printMessage("3. Poproś o wypożyczenie");
        printMessage("4. Poproś o przedłużenie wypożyczenia");
        printMessage("5. Zgłoś zgubienie sprzętu");
        printMessage("6. Wyloguj sie");
        printMessage("7. Wyjdź");
    }

    public void showEmployeeMenu() {
        System.out.println("1. Wyswietl katalog");
        System.out.println("2. Dodaj sprzęt do katalogu");
        System.out.println("3. Usun sprzet z katalogu");
        System.out.println("4. Modyfikuj dane sprzetu");
        System.out.println("5. Wyświetl listę aktualnych wypożyczeń");
        System.out.println("6. Rozpatrz prośby o wypożyczenie");
        System.out.println("7. Rozpatrz prośby o przedłużenie wypożyczenia");
        System.out.println("8. Rozpatrz zgloszenia zgubien sprzetu");
        System.out.println("9. Wyloguj sie");
        System.out.println("0. Wyjdź");
    }

    public String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine();
        } catch (InputMismatchException e) {
            printMessage("Wprowadzono bledne dane");
            return "";
        }
    }

    public int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            printMessage("Wprowadzono bledne dane");
            return 0;
        }
    }

    public double getDoubleInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            printMessage("Wprowadzono bledne dane");
            return 0;
        }
    }
}
