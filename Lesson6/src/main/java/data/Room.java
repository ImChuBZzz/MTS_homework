package data;


import lombok.*;
import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "rooms", schema = "booking")
public class Room {

    @Id
    @Column(name = "id_room")
    private String id;

    @Column(name = "room_number")
    private String room_number;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "room_type")
    private String room_type;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private List<Booking> bookings;


    public Room (String room_number, Integer floor, String room_type, String description, Integer price) {
        this.room_number = room_number;
        this.floor = floor;
        this.room_type = room_type;
        this.description = description;
        this.price = price;
        this.bookings = Collections.emptyList();
    }

    public Room (String id, String room_number, Integer floor, String room_type, String description, Integer price) {
        this.id = id;
        this.room_number = room_number;
        this.floor = floor;
        this.room_type = room_type;
        this.description = description;
        this.price = price;
        this.bookings = Collections.emptyList();
    }

    public Room (ResultSet rs) throws SQLException {
        this.id = rs.getString("id_room");
        this.room_number = rs.getString("room_number");
        this.floor = rs.getInt("floor");
        this.room_type = rs.getString("room_type");
        this.description = rs.getString("description");
        this.price = rs.getInt("price");
        this.bookings = Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id)
                && room_number.equals(room.room_number)
                && floor.equals(room.floor)
                && Objects.equals(room_type, room.room_type)
                && Objects.equals(description, room.description)
                && price.equals(room.price)
                && Objects.equals(bookings, room.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room_number, floor, room_type, description, price);
    }
}
