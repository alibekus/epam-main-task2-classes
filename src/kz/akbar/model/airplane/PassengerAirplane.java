package kz.akbar.model.airplane;

public class PassengerAirplane extends Airplane {

    private int capacity;

    public PassengerAirplane(AirplaneModel airplaneModel, int capacity, double flightRange, double fuelConsumption) {
        super(airplaneModel, flightRange, fuelConsumption);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPassenger capacity, man = " + capacity + '\n';
    }
}
