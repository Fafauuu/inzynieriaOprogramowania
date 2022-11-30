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

    public static void main(String[] args) {}

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
