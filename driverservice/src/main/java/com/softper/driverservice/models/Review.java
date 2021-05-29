package com.softper.driverservice.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Review implements Serializable {
    @Getter(AccessLevel.PRIVATE)
    //@Setter(AccessLevel.PRIVATE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "calification")
    private Double calification;

    //@OneToOne
    //@JoinColumn(name = "cargo_id")
    //private Cargo cargo;

    @Column(name = "cargo_id")
    private Integer cargoId;

    @ManyToOne
    @JoinColumn(name = "qualification_id", nullable = false)
    private Qualification qualification;
}