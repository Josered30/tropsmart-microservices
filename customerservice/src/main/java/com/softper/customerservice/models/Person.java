package com.softper.customerservice.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Person implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    //@Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    //private User user;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "person_type")
    private int personType;

    //@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    //private Customer customer;

    @Column(name = "customer_id")
    private Integer customerId;

    @Transient
    private Customer customer;


   //@OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    //private Driver driver;
    @Column(name = "driver_id")
    private Integer driverId;

    @Transient
    private Driver driver;
}
