package kz.akbar.model.company;

import kz.akbar.model.airplane.Airplane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Company {
    private static final Logger LOG = Logger.getLogger(Company.class.getName());
    private String name;
    private Map<String, List<Airplane>> airplanesFleet;

    public Company(String name) {
        this.name = name;
        this.airplanesFleet = new HashMap<>();
        airplanesFleet.put("PassengerAirplane", new ArrayList<>());
        airplanesFleet.put("CargoAirplane", new ArrayList<>());
    }

    public void addAirplane(Airplane airplane){
        String airplaneTypeName = airplane.getClass().getSimpleName();
//        LOG.info("Adding airplane of " + airplaneTypeName);
        LOG.log(Level.INFO, "Adding airplane of " + airplaneTypeName);
        switch(airplane.getClass().getSimpleName()){
            case "PassengerAirplane":
                airplanesFleet.get("PassengerAirplane").add(airplane);
                break;
            case "CargoAirplane":
                airplanesFleet.get("CargoAirplane").add(airplane);
        }
    }

    public List<Airplane> getAirplanesByType(String airplaneType){
        return airplanesFleet.get(airplaneType);
    }

}
