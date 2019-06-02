package kz.akbar;

import kz.akbar.model.airplane.Airplane;
import kz.akbar.model.airplane.AirplaneModel;
import kz.akbar.model.airplane.CargoAirplane;
import kz.akbar.model.airplane.PassengerAirplane;
import kz.akbar.model.company.Company;
import kz.akbar.service.AirplaneService;
import kz.akbar.service.SortType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getSimpleName());

    private static final Airplane PASSENGER_AIRPLANE_1 = new PassengerAirplane(new AirplaneModel("Boeing", "737"), 103,2592, 5);
    private static final Airplane PASSENGER_AIRPLANE_2 = new PassengerAirplane(new AirplaneModel("Boeing", "777"), 110,6820, 19);
    private static final Airplane PASSENGER_AIRPLANE_3 = new PassengerAirplane(new AirplaneModel("Boeing", "747"), 305,9695, 12);

    private static final Airplane CARGO_AIRPLANE_1 = new CargoAirplane(new AirplaneModel("Boeing", "757"), 43 ,57,7275, 5);
    private static final Airplane CARGO_AIRPLANE_2 = new CargoAirplane(new AirplaneModel("Boeing", "777"), 150,108,6820, 19);
    private static final Airplane CARGO_AIRPLANE_3 = new CargoAirplane(new AirplaneModel("Boeing", "747"), 171,178,9695, 12);

    public static void main(String[] args) throws IOException {
        Company airlines = new Company("Airlines");
        airlines.addAirplane(PASSENGER_AIRPLANE_1);
        airlines.addAirplane(PASSENGER_AIRPLANE_2);
        airlines.addAirplane(PASSENGER_AIRPLANE_3);
        airlines.addAirplane(CARGO_AIRPLANE_1);
        airlines.addAirplane(CARGO_AIRPLANE_2);
        airlines.addAirplane(CARGO_AIRPLANE_3);
        AirplaneService service = new AirplaneService(airlines);
        List<Airplane> sortedAirplanes = service.sortByFlightRange(airlines.getAirplanesByType("CargoAirplane"), SortType.DESC);
        LOG.info("Airplanes " + SortType.DESC.getTitle() + " sorted by flight range:\n" + sortedAirplanes);
        double totalTonnage = service.summarize(airlines.getAirplanesByType("CargoAirplane"),"tonnage");
        LOG.info("Total tonnage of cargo airplanes: " + totalTonnage);
        double totalCargoCapacity = service.summarize(airlines.getAirplanesByType("CargoAirplane"), "capacity");
        LOG.info("Total capacity of cargo airplanes: " + totalCargoCapacity + " m3");
        int totalFlightRangeCapacity = (int) service.summarize(airlines.getAirplanesByType("PassengerAirplane"),"capacity");
        LOG.info("Total capacity of passenger airplanes: " + totalFlightRangeCapacity + " man");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LOG.info("Airplanes with fuel consumption in range");
        System.out.print("Enter lowest fuel consumption number: ");
        double lowest = Double.parseDouble(br.readLine());
        System.out.print("Enter highest fuel consumption number: ");
        double highest = Double.parseDouble(br.readLine());
        LOG.info(service.getAirplanesInFuelConsumptionRange(airlines.getAirplanesByType("PassengerAirplane"),lowest,highest).toString());
    }
}
