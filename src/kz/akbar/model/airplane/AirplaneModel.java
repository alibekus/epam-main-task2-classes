package kz.akbar.model.airplane;

public class AirplaneModel {
    private final String manufacturer;
    private final String model;

    public AirplaneModel(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return manufacturer + " " + model;
    }
}
