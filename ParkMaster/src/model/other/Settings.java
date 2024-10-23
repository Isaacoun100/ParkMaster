package model.other;

import java.time.LocalTime;

public class Settings {

    private LocalTime fromTime;
    private LocalTime toTime;
    private int price; // This must be even
    private int minimumTime;
    private int ticketPrice;

    public Settings(LocalTime fromTime, LocalTime toTime, int price, int minimumTime, int ticketPrice) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.price = price;
        this.minimumTime = minimumTime;
        this.ticketPrice = ticketPrice;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinimumTime() {
        return minimumTime;
    }

    public void setMinimumTime(int minimumTime) {
        this.minimumTime = minimumTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
