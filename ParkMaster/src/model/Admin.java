package model;

import java.time.LocalDate;

public class Admin extends User {

    private LocalDate hireDate;

    public Admin(String name, String lastName, String phoneNumber, String email, String billingAddress, String id, int PIN, LocalDate hireDate) {
        super(name, lastName, phoneNumber, email, billingAddress, id, PIN);
        this.hireDate = hireDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}
