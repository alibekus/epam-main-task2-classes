package kz.akbar.model.airplane;

import java.util.Objects;

public class CargoAirplane extends Airplane {

    private double tonnage;
    private double capacity;

    public CargoAirplane(AirplaneModel airplaneModel, double capacity, double tonnage, double flightRange, double fuelConsumption) {
        super(airplaneModel, flightRange, fuelConsumption);
        this.tonnage = tonnage;
        this.capacity = capacity;
    }

    public double getTonnage() {
        return tonnage;
    }

    public double getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CargoAirplane that = (CargoAirplane) o;
        return Double.compare(that.tonnage, tonnage) == 0 &&
                Double.compare(that.capacity, capacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tonnage, capacity);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTonnage, ton = " + tonnage +
                "\nVolume capacity, m3 = " + capacity + '\n';
    }
}
