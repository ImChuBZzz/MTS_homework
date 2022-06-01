package com.example.lesson7.data;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;



@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

@Entity
@Table(name = "organizations", schema = "company")
public class Organization {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "contact_person_id")
    private String contactPersonId;

    @Column(name = "director")
    private String director;

    @Column(name = "inn", nullable = false)
    private String INN;

    @Column(name = "ogrn")
    private String OGRN;

    @Column(name = "kpp")
    private String KPP;

    @Column(name = "okved")
    private String OKVED;

    @Column(name = "okpo")
    private String OKPO;

    @Column(name = "bank")
    private String bank;

    @Column(name = "bik")
    private String BIK;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "employees_count")
    private Integer employeesCount;

    @Column(name = "address")
    private String address;

    @Column(name = "creation_date", nullable = false)
    private Timestamp creationDate;

    @Column(name = "update_date", nullable = false)
    private Timestamp updateDate;

    @Column(name = "description")
    private String description;




    public Organization(String name,    String image, String contactPersonId,    String director,    String INN,    String OGRN,
                        String KPP,     String OKVED, String OKPO,  String bank, String BIK,         String phone,   String email,
                        Integer employeesCount, String address,     Timestamp creationDate, Timestamp updateDate,    String description) {

        this.name = name;
        this.image = image;
        this.contactPersonId = contactPersonId;
        this.director = director;
        this.INN = INN;
        this.KPP = KPP;
        this.OKVED = OKVED;
        this.OKPO = OKPO;
        this.bank = bank;
        this.BIK = BIK;
        this.phone = phone;
        this.email = email;
        this.employeesCount = employeesCount;
        this.address = address;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.description = description;
    }
}
