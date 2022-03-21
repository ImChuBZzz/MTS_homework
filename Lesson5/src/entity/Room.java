package entity;

import java.util.Objects;
import java.util.Set;

public class Room {
    private String id;
    private String roomNumber;
    private Integer floor;
    private RoomType type;
    private String description = null;
    private Integer price;
    private Set<Booking> bookings = null;

    public Room() {}
    public Room(String id, String roomNumber, Integer floor, RoomType type, Integer price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.type = type;
        this.price = price;
    }

    public String getId() { return this.id; }
    public String getRoomNumber() { return this.roomNumber; }
    public Integer getFloor() { return this.floor; }
    public RoomType getType() { return this.type; }
    public String getDescription() { return this.description; }
    public Integer getPrice() { return this.price; }
    public Set<Booking> getBookings() { return this.bookings; }

    public void setId(String id) { this.id = id; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setFloor(Integer floor) { this.floor = floor; }
    public void setType(RoomType type) { this.type = type; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(Integer price) { this.price = price; }
    public void setBookings(Set<Booking> bookings) { this.bookings = bookings; }

    @Override
    public String toString() {
        return "Room: {" +
                "id: " + this.id +
                ", number: " + this.roomNumber +
                ", floor: " + this.floor +
                ", type: " + this.type +
                ", description: " + this.description +
                ", price: " + this.price +
                ", bookings: " + this.bookings +
                "}\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.roomNumber, this.floor, this.type, this.description, this.price, this.bookings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Room room = (Room)o;
        return Objects.equals(this.id, room.id)
                && Objects.equals(this.roomNumber, room.roomNumber)
                && Objects.equals(this.floor, room.floor)
                && Objects.equals(this.type, room.type)
                && Objects.equals(this.description, room.description)
                && Objects.equals(this.price, room.price)
                && Objects.equals(this.bookings, room.bookings);
    }
}
