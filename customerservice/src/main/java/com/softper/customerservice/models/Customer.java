package com.softper.customerservice.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="customers")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Customer implements Serializable {

    @Getter(AccessLevel.PRIVATE)
    //@Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "credits")
    private double credits;

    //@OneToMany(mappedBy = "customer")
    //private List<Cargo> cargoList = new ArrayList<>();

    //@OneToOne
    //@JoinColumn(name = "person_id")
    //private Person person;

    @Column(name = "person_id")
    private Integer personId;

    @OneToMany(mappedBy = "customer")
    private List<Benefit> claimedBenefits = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "balance")
    private Balance balance;
}