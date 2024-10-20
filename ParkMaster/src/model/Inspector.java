package model;

import java.time.LocalDate;

public class Inspector extends User {

    private LocalDate hireDate;
    private String terminal;

    public Inspector(String name, String lastName, String phoneNumber, String email, String billingAddress, String id, String PIN, LocalDate hireDate, String terminal) {
        super(name, lastName, phoneNumber, email, billingAddress, id, PIN);
        this.hireDate = hireDate;
        this.terminal = terminal;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}
