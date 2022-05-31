package data;


import lombok.*;
import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "bookings", schema = "booking")
public class Booking {

    @Id
    @Column(name = "id_booking")
    private String id;

    @Column(name = "check_in")
    private LocalDate check_in;

    @Column(name = "check_out")
    private LocalDate check_out;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public Booking(LocalDate check_in, LocalDate check_out, User user, Room room) {
        this.check_in = check_in;
        this.check_out = check_out;
        this.user = user;
        this.room = room;
    }

    public Booking(String id, LocalDate check_in, LocalDate check_out, User user, Room room) {
        this.id = id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.user = user;
        this.room = room;
    }

    public Booking(ResultSet rs) throws SQLException {
        this.id = rs.getString("id_booking");
        this.check_in = rs.getDate("check_in").toLocalDate();
        this.check_out = rs.getDate("check_out").toLocalDate();
        this.user = null;
        this.room = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id)
                && Objects.equals(check_in, booking.check_in)
                && Objects.equals(check_out, booking.check_out)
                && Objects.equals(user, booking.user)
                && Objects.equals(room, booking.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, check_in, check_out, user, room);
    }
}
