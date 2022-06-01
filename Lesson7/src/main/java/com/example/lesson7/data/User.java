package com.example.lesson7.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "users", schema = "company")

public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "mid_name")
    private String midName;

    @Column(name = "phone")
    private String phone;

    public User(String firstName, String lastName, String midName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.midName = midName;
        this.phone = phone;
    }


}
