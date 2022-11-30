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
        View view = new View();
        Equipment eq1 = new Equipment("Equipment 1", 2.9, 30.0, 5);
        Equipment eq2 = new Equipment("Equipment 2", 21.9, 10.15, 200);
        Equipment eq3 = new Equipment("Equipment 3", 4.9, 400.20, 10);
        Equipment eq4 = new Equipment("Equipment 4", 5.78, 3000, 5);

        application.catalogue.add(eq1);
        application.catalogue.add(eq2);
        application.catalogue.add(eq3);
        application.catalogue.add(eq4);

        view.printMessage("Wypozyczalnia sprzetu turystycznego\n");
        view.printCatalogue(application.catalogue);

        Client client1 = new Client("Jan", "Kowalski", "jan.kowalski@gmail.com", "123-456-789");
        Rental rental1 = new Rental(client1, eq1, 2, 14);
        Rental rental2 = new Rental(client1, eq2, 1, 14);
        Rental rental3 = new Rental(client1, eq3, 1, 7);

        application.allRentals.add(rental1);
        application.allRentals.add(rental2);
        application.allRentals.add(rental3);

        view.printRentals(application.allRentals);

        RentalRequest rr1 = new RentalRequest(client1, eq1, 1, 14);
        RentalRequest rr2 = new RentalRequest(client1, eq2, 2, 10);
        RentalRequest rr3 = new RentalRequest(client1, eq3, 3, 14);

        application.rentalRequests.add(rr1);
        application.rentalRequests.add(rr2);
        application.rentalRequests.add(rr3);

        view.printRentalRequests(application.rentalRequests);
    }

    public boolean employeeLogin() {
      return false;
    }

    public boolean clientLogin() {
        return false;
    }

    public void showClientRentals() {}

    public void createNewRentalRequest() {}

    public void createNewRentalExtensionRequest() {}

    public void createNewEquipmentLostReport() {}

    public void createNewEquipment() {}

    public void loadData() {}

    public void login() {}

    public void decideRentalRequest() {}

    public void decideExtensionRequest() {}

    public void decideEquipmentLostReport() {}

    public void deleteEquipment() {}
}
