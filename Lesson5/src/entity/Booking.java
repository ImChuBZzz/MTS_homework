package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private String id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Guest guest;
    private Room room;

    public Booking() {}
    public Booking(String id, LocalDate checkIn, LocalDate checkOut, Guest guest, Room room) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guest = guest;
        this.room = room;
    }

    public String getId() { return this.id; }
    public LocalDate getCheckIn() { return this.checkIn; }
    public LocalDate getCheckOut() { return this.checkOut; }
    public Guest getGuest() { return this.guest; }
    public Room getRoom() { return this.room; }

    public void setId(String id) { this.id = id; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public void setGuest(Guest guest) { this.guest = guest; }
    public void setRoom(Room room) { this.room = room; }

    @Override
    public String toString(){
        return "Booking: {" +
                "id: " + this.id +
                ", checkIn: " + this.checkIn +
                ", checkOut " + this.checkOut +
                ", guest: " + this.guest +
                ", room:" + this.room +
                "}\n";
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.checkIn, this.checkOut, this.guest, this.room);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Booking booking = (Booking)o;
        return Objects.equals(this.id, booking.id)
                && Objects.equals(this.checkIn, booking.checkIn)
                && Objects.equals(this.checkOut, booking.checkOut)
                && Objects.equals(this.guest, booking.guest)
                && Objects.equals(this.room, booking.room);
    }
}
