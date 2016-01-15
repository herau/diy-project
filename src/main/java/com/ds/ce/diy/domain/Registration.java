package com.ds.ce.diy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}),
                                                   @UniqueConstraint(columnNames = {"date", "type"})})
@NoArgsConstructor/**/
@RequiredArgsConstructor
public class Registration extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "registration_id")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @NonNull
    @Getter
    // only the logged user can make a registration
    //TODO is it possible to create an officeHours for another person (if not you can use @CreatedBy)
    private User user;

    @Column(nullable = false, updatable = false)
    @NonNull
    @Getter
    private LocalDate date;

    @Column(nullable = false, updatable = false)
    @NonNull
    @Getter
    private RegistrationType type;

    //TODO if you want to keep a trace of people which unsubscribe from an office hour
    private boolean unsubscribed;

    public LocalDateTime getRegistrationDate() {
        return getCreatedDate();
    }

    public void unsubscribe() {
        this.unsubscribed = true;
    }
}
