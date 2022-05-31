package data;


import lombok.*;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "users", schema = "booking")
public class User {

    @Id
    @Column(name = "id_user")
    private String id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String  first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "mid_name")
    private String mid_name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Booking> bookings;

    public User(String phone, String email, String first_name, String last_name, String mid_name) {
        this.phone = phone;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mid_name = mid_name;
        this.bookings = Collections.emptyList();
    }

    public User(String id, String phone, String email, String first_name, String last_name, String mid_name) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mid_name = mid_name;
        this.bookings = Collections.emptyList();
    }

    public User(ResultSet rs) throws SQLException {
        this.id = rs.getString("id_user");
        this.phone = rs.getString("phone");
        this.email = rs.getString("email");
        this.first_name = rs.getString("first_name");
        this.last_name = rs.getString("last_name");
        this.mid_name = rs.getString("mid_name");
        this.bookings = Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id)
                && phone.equals(user.phone)
                && email.equals(user.email)
                && first_name.equals(user.first_name)
                && last_name.equals(user.last_name)
                && Objects.equals(mid_name, user.mid_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, email, first_name, last_name, mid_name);
    }
}
