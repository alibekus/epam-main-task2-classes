package kz.akbar.service;

import kz.akbar.model.airplane.Airplane;
import kz.akbar.model.airplane.CargoAirplane;
import kz.akbar.model.airplane.PassengerAirplane;
import kz.akbar.model.company.Company;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AirplaneService {

    private Company company;
    private static final Logger LOG = Logger.getLogger(Company.class.getName());

    public AirplaneService(Company company) {
        this.company = company;
    }

    public List<Airplane> sortByFlightRange(List<Airplane> airplanes, SortType sortType) {
//        LOG.info(sortType.getTitle() + " sorting airplanes by distance range");
        LOG.log(Level.INFO, sortType.getTitle() + " sorting airplanes by distance range");
        return airplanes.stream().sorted((a1, a2) -> {
            switch (sortType) {
                case ASC:
                    return a1.getFlightRange() < a2.getFlightRange() ? -1 :
                            a1.getFlightRange() == a2.getFlightRange() ? 0 : 1;
                case DESC:
                    return a1.getFlightRange() > a2.getFlightRange() ? -1 :
                            a1.getFlightRange() == a2.getFlightRange() ? 0 : 1;
            }
            return 0;
        }).collect(Collectors.toList());
    }

    public double summarize(List<Airplane> airplanes, String parameter) {
        String airplaneTypeName = airplanes.get(0).getClass().getSimpleName();
//        LOG.info("Summarizing by " + parameter + " for " + airplaneTypeName);
        LOG.log(Level.INFO, "Summarizing by " + parameter + " for " + airplaneTypeName);
        switch (parameter) {
            case "tonnage":
                return airplanes.stream().mapToDouble(value -> ((CargoAirplane) value).getTonnage()).sum();
            case "capacity":
                if (airplanes.get(0).getClass().getSimpleName().equals("PassengerAirplane")) {
                    return airplanes.stream().mapToDouble(value -> ((PassengerAirplane) value).getCapacity()).sum();
                } else {
                    return airplanes.stream().mapToDouble(value -> ((CargoAirplane) value).getCapacity()).sum();
                }
        }
        return 0;
    }

    public List<Airplane> getAirplanesInFuelConsumptionRange(List<Airplane> airplanes, double lowest, double highest) {
        LOG.log(Level.INFO, "Getting airplanes with fuel consumption in range of " + lowest + " and " + highest);
        return airplanes.stream().filter(airplane -> airplane.getFuelConsumption() > lowest & airplane.getFuelConsumption() < highest).collect(Collectors.toList());
    }
}
