package com.ds.ce.diy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.ngram.NGramTokenizerFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "personal_number", name = "user_personal_number_hidx")})
@EntityListeners({AuditingEntityListener.class})
@Indexed(index = "users")
@AnalyzerDef(name = "nGrams",
        tokenizer = @TokenizerDef(factory = NGramTokenizerFactory.class, params = {
                @Parameter(name = "minGramSize", value = "2"), @Parameter(name = "maxGramSize", value = "15")}),
        filters = {@TokenFilterDef(factory = LowerCaseFilterFactory.class),
                   @TokenFilterDef(factory = StandardFilterFactory.class)})
public class User extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "user_id")
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 11, name = "personal_number")
    @Field
    @Analyzer(definition = "nGrams")
    private String personalNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 55)
    @Field
    @Analyzer(definition = "nGrams")
    private String firstname;

    @Column(nullable = false, length = 55)
    @Analyzer(definition = "nGrams")
    @Field
    private String lastname;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(name = "personal_email")
    @Email
    private String personalEmail;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @Column(columnDefinition = "NUMBER(1) DEFAULT 0 NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private Company company;

    // TODO ADMIN user not have account
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private VerificationToken token;

    public User() {
        // no-args constructor required by JPA spec and Jackson
    }

    public User(String personalNumber, String firstname, String lastname, String password, String email,
                Company company, State state) {
        this.personalNumber = personalNumber;
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.email = email;
        this.company = company;
        this.state = state;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public Company getCompany() {
        return company;
    }

    public State getState() {
        return state;
    }

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Role getRole() {
        return role;
    }

    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addVerificationToken(final VerificationToken token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder().append(personalNumber, user.personalNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(personalNumber).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("personalNumber", personalNumber).append("role", role)
                                        .append("state", state).append("company", company).append("email", email)
                                        .append("lastname", lastname).append("firstname", firstname).toString();
    }
}
