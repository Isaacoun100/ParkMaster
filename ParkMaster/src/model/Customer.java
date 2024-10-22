package model;

import model.other.PaymentMethod;
import model.other.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends User{

    private PaymentMethod paymentMethod;
    private ArrayList<Vehicle> vehicles;
    private LocalDate signUpDate;

    public Customer(String name, String lastName, String phoneNumber, String email, String billingAddress, String id, String PIN, PaymentMethod paymentMethod, ArrayList<Vehicle> vehicles) {
        super(name, lastName, phoneNumber, email, billingAddress, id, PIN);
        this.paymentMethod = paymentMethod;
        this.vehicles = vehicles;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public LocalDate getSignUpDate() {
        return signUpDate;
    }
}
