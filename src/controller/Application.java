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
        application.loadData();
        application.login();
    }

    public boolean clientLogin() {
        view.clear();
        view.printMessage("PODAJ EMAIL:");
        String emailAddress = view.getStringInput();
        view.printMessage("PODAJ NUMER TELEFONU:");
        String phoneNumber = view.getStringInput();
        boolean logged = false;
        for(Client client : clients){
            String clientPhoneNumber = client.getPhone();
            String clientEmailAddress = client.getEmail();
            if((clientPhoneNumber.equals(phoneNumber)) && (clientEmailAddress.equals(emailAddress))){
                currentClient = client;
                logged = true;
                break;
            }
        }
        if(logged) view.printMessage("POPRAWNIE ZALOGOWANO");
        else{
            view.printMessage("BLAD LOGOWANIA!");
            return true;
        }
        int input;
        while(true){
            view.showClientMenu();
            view.printMessage("WYBIERZ OPCJE:");
            input = view.getIntInput();
            switch(input){
                case 1 :
                {
                    boolean isCatalogueEmpty = catalogue.isEmpty();
                    if(isCatalogueEmpty){
                        view.printMessage("KATALOG PUSTY!");
                    }
                    else view.printCatalogue(catalogue);
                    break;
                }
                case 2 :
                {
                    viewClientRentals();
                    break;
                }
                case 3:
                {
                    createNewRentalRequest();
                    break;
                }
                case 4:
                {
                    createNewRentalExtensionRequest();
                    break;
                }
                case 5:
                {
                    createNewEquipmentLostReport();
                    break;
                }
                case 6:
                {
                    return true;
                }
                case 7:
                {
                    return false;
                }
                default: view.printMessage("NIE MA TAKIEJ OPCJI!");
            }
        }
    }

    public boolean employeeLogin() {
        view.clear();
        view.printMessage("PODAJ NUMER TELEFONU: ");
        String phoneNumber = view.getStringInput();
        boolean logged = false;
        for(Employee employee : employees){
            String employeePhoneNumber = employee.getPhone();
            if(employeePhoneNumber.equals(phoneNumber)){
                logged = true;
                break;
            }
        }
        if(logged) view.printMessage("POPRAWNIE ZALOGOWANO!");
        else{
            view.printMessage("BLAD LOGOWANIA!");
            return true;
        }
        int input = -1;
        while(true){
            view.showEmployeeMenu();
            view.printMessage("WYBIERZ OPCJE: ");
            input = view.getIntInput();
            switch(input){
                case 1 :
                {
                    boolean isCatalogueEmpty = catalogue.isEmpty();
                    if(isCatalogueEmpty){
                        view.printMessage("KATALOG PUSTY!");
                    }
                    else view.printCatalogue(catalogue);
                    break;
                }
                case 2 :
                {
					createNewEquipment();
                    break;
                }
                case 3:
                {
                    deleteEquipment();
                    break;
                }
                case 4:
                {
                    boolean isRentalListEmpty = allRentals.isEmpty();
                    if(isRentalListEmpty){
                        view.printMessage("BRAK WYPOZYCZEN!");
                    }
                    else view.printRentals(allRentals);
                    break;
                }
                case 5:
                {
                    decideRentalRequest();
                    break;
                }
                case 6:
                {
					decideExtensionRequest();
                    break;
                }
                case 7:
                {
                    decideEquipmentLostReport();
                    break;
                }
                case 8:
                {
                    return true;
                }
                case 9:
                {
                    return false;
                }
                default: view.printMessage("NIE MA TAKIEJ OPCJI!");
            }
        }
    }

    public void viewClientRentals() {
        clientRentals.clear();
        for (Rental rental: allRentals) {
            Client checkedClient = rental.getClient();
            if(checkedClient == currentClient){
                clientRentals.add(rental);
            }
        }
        boolean isClientRentalListEmpty = clientRentals.isEmpty();
        if(isClientRentalListEmpty){
            view.printMessage("BRAK WYPOZYCZEN!");
        }
        else view.printRentals(clientRentals);
    }

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

    public void createNewEquipmentLostReport() {
        viewClientRentals();
        boolean isListEmpty = clientRentals.isEmpty();
        if (isListEmpty) return;
        view.printMessage("PODAJ NUMER WYPOZYCZENIA ZGUBIENIE CHCESZ ZGLOSIC: ");
        int input = view.getIntInput();
        int listSize = clientRentals.size();
        if (input > listSize || input < 1) {
            view.printMessage("PODAJ POPRAWNY NUMER");
            return;
        }
        Rental lostRental = clientRentals.get(input - 1);
        EquipmentLostReport newEquipmentLostReport = new EquipmentLostReport(lostRental);
        view.printMessage("ZGLOSZONO ZGUBIENIE SPRZETU");
        equipmentLostReports.add(newEquipmentLostReport);
    }

    public void createNewEquipment() {
        view.printMessage("PODAJ NAZWE SPRZETU:");
		String name = view.getStringInput();
		view.printMessage("PODAJ CENE ZA DZIEN WYPOZYCZENIA:");
		double oneDayCost = view.getDoubleInput();
		view.printMessage("PODAJ KAUCJE:");
		double deposit = view.getDoubleInput();
		view.printMessage("PODAJ ILOSC SPRZETU:");
		int amount = view.getIntInput();
		view.printMessage("DODANO NOWY SPRZET DO KATALOGU!");
        Equipment newEquipment = new Equipment(name, oneDayCost, deposit, amount);
        catalogue.add(newEquipment);
    }

    public void loadData() {
        clients.add(new Client("Jan", "Kowalski", "jankowalski@gmail.com", "111222333"));
        employees.add(new Employee("Krzysztof", "Nowak", "123456789"));
        catalogue.add(new Equipment("Narty", 50.00, 100, 10));
        catalogue.add(new Equipment("Kask", 20.00, 30, 50));
    }

    public void login() {
        boolean keepRunning = true;
        while(keepRunning){
            view.printMessage("1 - ZALOGUJ SIE JAKO KLIENT");
            view.printMessage("2 - ZALOGUJ SIE JAKO PRACOWNIK");
            int	choice = view.getIntInput();
            if(choice == 1)	keepRunning = clientLogin();
            else if(choice == 2) keepRunning = employeeLogin();
            else{
                view.printMessage("WYBIERZ POPRAWNA OPCJE!");
            }}
    }

    public void decideRentalRequest() {
        boolean isRentalRequestListEmpty = rentalRequests.isEmpty();
        if(isRentalRequestListEmpty){
            view.printMessage("BRAK PROSB O WYPOZYCZENIE!");
            return;
        }
        view.clear();
        view.printRentalRequests(rentalRequests);
        view.printMessage("PODAJ NUMER PROSBY DO ZAAKCEPTOWANIA LUB WPROWADZ 0 ABY POWROCIC DO MENU");
        int input = view.getIntInput();
        if(input == 0) return;
        int rentalRequestListSize = rentalRequests.size();
        if (input > rentalRequestListSize || input < 1) {
            view.printMessage("NIEDOPUSZCZALNY NUMER");
            return;
        }
        RentalRequest selectedRentalRequest = rentalRequests.get(input - 1);
        Equipment rentedEquipment = selectedRentalRequest.getEquipment();
        int currentAmount = rentedEquipment.getAmount();
        int rentAmount = selectedRentalRequest.getAmount();
        if(currentAmount < rentAmount){
            view.printMessage("SPRZET JEST NIEDOSTEPNY!");
        }
        else {
            Client client = selectedRentalRequest.getClient();
            int rentalPeriod = selectedRentalRequest.getRentalPeriod();
            Rental newRental = new Rental(client, rentedEquipment, rentAmount, rentalPeriod);
            allRentals.add(newRental);
            rentedEquipment.setAmount(currentAmount - rentAmount);
            rentalRequests.remove(selectedRentalRequest);
            view.printMessage("PROSBA ZOSTALA ZAAKCEPTOWANA");
        }
    }

    public void decideExtensionRequest() {
        boolean isExtensionRequestListEmpty = rentalExtensionRequests.isEmpty();
        if(isExtensionRequestListEmpty){
			view.printMessage("BRAK PROSB O PRZEDLUZENIE!");
        }
		view.clear();
		view.printRentalExtensionRequests(rentalExtensionRequests);
		view.printMessage("PODAJ NUMER PROSBY KTORA CHCESZ ZAAKCEPTOWAC LUB WPROWADZ 0 ZEBY WROCIC DO MENU");
		int input = view.getIntInput();
        if(input == 0) return;
        int listSize = rentalExtensionRequests.size();
        if(input > listSize || input < 1){
			view.printMessage("NIEDOPUSZCZALNY NUMER");
        }
        else {
            RentalExtensionRequest selectedRequest = rentalExtensionRequests.get(input - 1);
            Rental selectedRental = selectedRequest.getRental();
            int currentDaysLeft = selectedRental.getDaysLeft();
            selectedRental.setDaysLeft(currentDaysLeft + 7);
            rentalExtensionRequests.remove(selectedRequest);
			view.printMessage("ZAAKCEPTOWANO PROSBE");
        }
    }

    public void decideEquipmentLostReport() {
        boolean isEquipmentLostReportListEmpty = equipmentLostReports.isEmpty();
        if(isEquipmentLostReportListEmpty){
            view.printMessage("BRAK ZGLOSZEN ZGUBIENIA!");
            return;
        }
        view.clear();
        view.printEquipmentLostReports(equipmentLostReports);
        view.printMessage("PODAJ NUMER ZGLOSZENIA ZA KTORY CHCESZ NALICZYC OPLATE LUB WPROWADZ 0 ZEBY WROCIC DO MENU");
        int input = view.getIntInput();
        if(input == 0) return;
        int equipmentLostReportSize = equipmentLostReports.size();
        if(input > equipmentLostReportSize || input < 1){
            view.printMessage("NIEDOPUSZCZALNY NUMER");
        }
        else {
            EquipmentLostReport selectedReport = equipmentLostReports.get(input-1);
            Rental rentalToRemove = selectedReport.getRental();
            allRentals.remove(rentalToRemove);
            equipmentLostReports.remove(selectedReport);
            view.printMessage("POPRAWNIE NALICZONO OPLATE");
        }
    }

    public void deleteEquipment() {
        boolean isCatalogueEmpty = catalogue.isEmpty();
        if (isCatalogueEmpty) {
            view.printMessage("KATALOG JEST PUSTY!");
            return;
        }
        else view.printCatalogue(catalogue);
        view.printMessage("PODAJ NUMER SPRZETU KTORY CHCESZ USUNAC: ");
        int input = view.getIntInput();
        int catalogueSize = catalogue.size();
        if (input > catalogueSize || input < 1) {
            view.printMessage("NIEPRAWIDLOWY NUMER SPRZETU");
        }
        else {
            catalogue.remove(input - 1);
            view.printMessage("USUNIETO SPRZET");
        }
    }
}
