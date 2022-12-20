package controller;

import model.*;
import view.View;

import java.util.ArrayList;

public class Application {
    private Client currentClient;
    private View view;
    private final ArrayList<Equipment> catalogue = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<Client> clients = new ArrayList<>();
    private final ArrayList<Rental> allRentals = new ArrayList<>();
    private final ArrayList<Rental> clientRentals = new ArrayList<>();
    private final ArrayList<RentalRequest> rentalRequests = new ArrayList<>();
    private final ArrayList<RentalExtensionRequest> rentalExtensionRequests = new ArrayList<>();
    private final ArrayList<EquipmentLostReport> equipmentLostReports = new ArrayList<>();

    public static void main(String[] args) {
        Application application = new Application();
        application.view = new View();
        Equipment eq1 = new Equipment("Equipment 1", 2.9, 30.0, 5);
        Equipment eq2 = new Equipment("Equipment 2", 21.9, 10.15, 200);
        Equipment eq3 = new Equipment("Equipment 3", 4.9, 400.20, 10);
        Equipment eq4 = new Equipment("Equipment 4", 5.78, 3000, 5);

        application.catalogue.add(eq1);
        application.catalogue.add(eq2);
        application.catalogue.add(eq3);
        application.catalogue.add(eq4);

        Client client1 = new Client("Jan", "Kowalski", "jan.kowalski@gmail.com", "123-456-789");
        Rental rental1 = new Rental(client1, eq1, 2, 14);
        Rental rental2 = new Rental(client1, eq2, 1, 14);
        Rental rental3 = new Rental(client1, eq3, 1, 7);

        application.clientRentals.add(rental1);
        application.clientRentals.add(rental2);
        application.clientRentals.add(rental3);

//        application.view.printRentals(application.clientRentals);
//
//        RentalRequest rr1 = new RentalRequest(client1, eq1, 1, 14);
//        RentalRequest rr2 = new RentalRequest(client1, eq2, 2, 10);
//        RentalRequest rr3 = new RentalRequest(client1, eq3, 3, 14);
//
//        application.rentalRequests.add(rr1);
//        application.rentalRequests.add(rr2);
//        application.rentalRequests.add(rr3);
//
//        application.view.printRentalRequests(application.rentalRequests);
//
//        RentalExtensionRequest rer1 = new RentalExtensionRequest(rental1);
//        RentalExtensionRequest rer2 = new RentalExtensionRequest(rental2);
//        RentalExtensionRequest rer3 = new RentalExtensionRequest(rental3);
//
//        application.rentalExtensionRequests.add(rer1);
//        application.rentalExtensionRequests.add(rer2);
//        application.rentalExtensionRequests.add(rer3);
//
//        application.view.printRentalExtensionRequests(application.rentalExtensionRequests);
//
//        EquipmentLostReport elr1 = new EquipmentLostReport(rental1);
//        EquipmentLostReport elr2 = new EquipmentLostReport(rental2);
//        EquipmentLostReport elr3 = new EquipmentLostReport(rental3);
//
//        application.equipmentLostReports.add(elr1);
//        application.equipmentLostReports.add(elr2);
//        application.equipmentLostReports.add(elr3);
//
//        application.view.printEquipmentLostReports(application.equipmentLostReports);
//
//        application.login();

        application.createNewRentalRequest();

        application.createNewRentalExtensionRequest();
    }

    public boolean employeeLogin() {
      return false;
    }

    public boolean clientLogin() {
        return false;
    }

    public void viewClientRentals() {}

    public void createNewRentalRequest() {
        boolean isCatalogueEmpty = catalogue.isEmpty();
        if (isCatalogueEmpty) {
            view.printMessage("KATALOG PUSTY!!!");
        } else {
            view.printCatalogue(catalogue);
            view.printMessage("PODAJ NUMER SPRZETU KTORY CHCESZ WYPOZYCZYC");
            int equipmentNumber = view.getIntInput();
            view.printMessage("PODAJ ILOSC SPRZETU");
            int amount = view.getIntInput();
            view.printMessage("PODAJ DLUGOSC WYPOZYCZENIA (DNI)*");
            int rentalPeriod = view.getIntInput();
            Equipment selectedEquipment = catalogue.get(equipmentNumber - 1);
            int availableAmount = selectedEquipment.getAmount();
            int catalogueSize = catalogue.size();
            if (equipmentNumber > catalogueSize || equipmentNumber < 1) {
                view.printMessage("ZLY NUMER SPRZETU");
            }
            if (availableAmount >= amount && rentalPeriod > 0) {
                view.printMessage("TWOJA PROSBA ZOSTALA ZGLOSZONA");
                RentalRequest newRentalRequest
                        = new RentalRequest(currentClient, selectedEquipment, amount, rentalPeriod);
                rentalRequests.add(newRentalRequest);
            } else {
                view.printMessage("BLEDNE DANE");
            }
        }
    }

    public void createNewRentalExtensionRequest() {
        viewClientRentals();
        boolean isListEmpty = clientRentals.isEmpty();
        if (isListEmpty) {
            view.printMessage("LISTA WYPOZYCZEN JEST PUSTA!!!");
            return;
        }
        view.printMessage("PODAJ NUMER WYPOZYCZENIA DO PRZEDLUZENIA");
        int rentalNumber = view.getIntInput();
        int listSize = clientRentals.size();
        if (rentalNumber > listSize || rentalNumber < 1) {
            view.printMessage("NIEDOPUSZCZALNY NUMER");
            return;
        }
        Rental selectedRental = clientRentals.get(rentalNumber - 1);
        RentalExtensionRequest newRentalExtensionRequest = new RentalExtensionRequest(selectedRental);
        view.printMessage("TWOJA PROSBA ZOSTALA ZGLOSZONA");
        rentalExtensionRequests.add(newRentalExtensionRequest);
    }

    public void createNewEquipmentLostReport() {}

    public void createNewEquipment() {}

    public void loadData() {}

    public void login() {}

    public void decideRentalRequest() {}

    public void decideExtensionRequest() {}

    public void decideEquipmentLostReport() {}

    public void deleteEquipment() {}
}
