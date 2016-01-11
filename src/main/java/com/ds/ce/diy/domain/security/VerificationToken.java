package com.ds.ce.diy.domain.security;

import com.ds.ce.diy.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(name = "tokens", indexes = {@Index(columnList = "token", name = "token_value_hidx")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "type"})})
@NoArgsConstructor
@EqualsAndHashCode(of = {"user", "type", "token"})
@RequiredArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "token_id")
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false, name = "user_id")
    @Getter @NonNull
    private User user;

    @Column(nullable = false, updatable = false, name = "type")
    @Enumerated
    @Getter @NonNull
    private VerificationTokenType type;

    /**
     * time to live for the Token. According to configuration ${app.email.registration.expiryTime}
     */
    @Column(nullable = false, updatable = false, name = "expiry_date")
    @NonNull
    private LocalDateTime expiryDate;

    /**
     * a UUID that is used to identify the token. It is Base64 encoded before being sent
     */
    @Column(nullable = false, updatable = false)
    @NonNull
    private String token;

    /**
     * has this token been verified
     */
    @Column
    private boolean verified;

    /**
     * @param user
     * @param type
     * @param expiredTime expired time in minutes
     */
    public VerificationToken(User user, VerificationTokenType type, int expiredTime) {
        this(user, type, LocalDateTime.now().plusMinutes(expiredTime), UUID.randomUUID().toString());
    }

    /**
     * Verification token is only valid when it is not expired and hasn't been verified yet
     *
     * @return true is the verification token is valid, false otherwise
     *
     * @see VerificationToken#setVerified()
     */
    public boolean isValid() {
        return !verified && expiryDate.isAfter(LocalDateTime.now());
    }

    public void setVerified() {
        this.verified = true;
    }

    public String getToken() {
        return Base64.getEncoder().encodeToString(token.getBytes());
    }

}
