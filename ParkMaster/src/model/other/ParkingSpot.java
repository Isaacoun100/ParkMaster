package model.other;

public class ParkingSpot {

    private String ID;
    private String scheduledBy; //This will have the ID of the customer that scheduled it, 0 will be default
    private int earnings; //This will add upp all the earnings from this parking spot
    private int tickets;

    public ParkingSpot(String ID, String scheduledBy, int earnings, int tickets) {
        this.ID = ID;
        this.scheduledBy = scheduledBy;
        this.earnings = earnings;
        this.tickets = tickets;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getScheduledBy() {
        return scheduledBy;
    }

    public void setScheduledBy(String scheduledBy) {
        this.scheduledBy = scheduledBy;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
