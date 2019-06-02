package kz.akbar.service;

import kz.akbar.model.airplane.Airplane;
import kz.akbar.model.airplane.CargoAirplane;
import kz.akbar.model.airplane.AirplaneModel;
import kz.akbar.model.airplane.PassengerAirplane;
import kz.akbar.model.company.Company;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class AirplaneServiceTest {

    private static final Airplane PASSENGER_AIRPLANE_1 = new PassengerAirplane(new AirplaneModel("Boeing", "737"), 103,2592, 5);
    private static final Airplane PASSENGER_AIRPLANE_2 = new PassengerAirplane(new AirplaneModel("Boeing", "777"), 110,6820, 19);
    private static final Airplane PASSENGER_AIRPLANE_3 = new PassengerAirplane(new AirplaneModel("Boeing", "747"), 305,9695, 12);

    private static final Airplane CARGO_AIRPLANE_1 = new CargoAirplane(new AirplaneModel("Boeing", "757"), 43 ,57,7275, 5);
    private static final Airplane CARGO_AIRPLANE_2 = new CargoAirplane(new AirplaneModel("Boeing", "777"), 150,108,6820, 19);
    private static final Airplane CARGO_AIRPLANE_3 = new CargoAirplane(new AirplaneModel("Boeing", "747"), 171,178,9695, 12);

    private AirplaneService service;
    private Company airlines;


    @org.junit.Before
    public void setUp() throws Exception {
        this.airlines = new Company("Airlines");
        airlines.addAirplane(PASSENGER_AIRPLANE_1);
        airlines.addAirplane(PASSENGER_AIRPLANE_2);
        airlines.addAirplane(PASSENGER_AIRPLANE_3);
        airlines.addAirplane(CARGO_AIRPLANE_1);
        airlines.addAirplane(CARGO_AIRPLANE_2);
        airlines.addAirplane(CARGO_AIRPLANE_3);
        this.service = new AirplaneService(airlines);
    }

    @org.junit.Test
    public void sortByFlightRange() {
        List<Airplane> actual = new ArrayList<>();
        actual.add(CARGO_AIRPLANE_3);
        actual.add(CARGO_AIRPLANE_1);
        actual.add(CARGO_AIRPLANE_2);
        List<Airplane> expected = service.sortByFlightRange(airlines.getAirplanesByType("CargoAirplane"),SortType.DESC);
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }

    @org.junit.Test
    public void summarize() {
        double expectedCargoCapacity = 364.0;
        double actualCargoCapacity = service.summarize(airlines.getAirplanesByType("CargoAirplane"),"capacity");
        Assert.assertEquals(expectedCargoCapacity,actualCargoCapacity,0);
        double expectedPassengerCapacity = 518;
        double actualPassengerCapacity = service.summarize(airlines.getAirplanesByType("PassengerAirplane"),"capacity");
        Assert.assertEquals(expectedPassengerCapacity,actualPassengerCapacity,0);
        double expectedTonnage = 343;
        double actualTonnage = service.summarize(airlines.getAirplanesByType("CargoAirplane"),"tonnage");
        Assert.assertEquals(expectedTonnage, actualTonnage,0);
    }

    @org.junit.Test
    public void getAirplanesInFuelConsumptionRange() {
        double lowestFuelConsumption = 4.9;
        double highestFuelConsumption = 12.5;
        List<Airplane> expectedAirplanes = new ArrayList<>();
        expectedAirplanes.add(PASSENGER_AIRPLANE_1);
        expectedAirplanes.add(PASSENGER_AIRPLANE_3);
        List<Airplane> actualAirplanes = service.getAirplanesInFuelConsumptionRange(airlines.getAirplanesByType(PassengerAirplane.class.getSimpleName()),lowestFuelConsumption,highestFuelConsumption);
        for (int i = 0; i < expectedAirplanes.size(); i++){
            Assert.assertSame(expectedAirplanes.get(i),actualAirplanes.get(i));
        }
    }
}