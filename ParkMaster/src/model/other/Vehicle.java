package model.other;

public class Vehicle {

    private String vehicleID;
    private String Brand;
    private String Model;

    public Vehicle(String vehicleID, String brand, String model) {
        this.vehicleID = vehicleID;
        Brand = brand;
        Model = model;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
