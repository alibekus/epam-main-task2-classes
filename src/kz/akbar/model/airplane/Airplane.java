package kz.akbar.model.airplane;

import java.util.Objects;

public class Airplane  {

    private double flightRange;
    private double fuelConsumption;
    private final AirplaneModel airplaneModel;

    public Airplane(AirplaneModel airplaneModel, double flightRange, double fuelConsumption) {
        this.flightRange = flightRange;
        this.fuelConsumption = fuelConsumption;
        this.airplaneModel = airplaneModel;
    }

    public double getFlightRange() {
        return flightRange;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public AirplaneModel getModel() {
        return airplaneModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return Double.compare(airplane.flightRange, flightRange) == 0 &&
                Double.compare(airplane.fuelConsumption, fuelConsumption) == 0 &&
                airplaneModel.equals(airplane.airplaneModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightRange, fuelConsumption, airplaneModel);
    }

    @Override
    public String toString() {
        return "AirplaneModel: " + airplaneModel +
                "\nType: " + this.getClass().getSimpleName() +
                "\nFlight range, km = " + flightRange +
                "\nFuel consumption, l/km = " + fuelConsumption;
    }
}
