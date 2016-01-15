package com.ds.ce.diy.domain;

import com.ds.ce.diy.domain.security.VerificationToken;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "personalNumber", callSuper = false)
@ToString(exclude = {"id", "password", "token", "account"})

@Entity
@Table(name = "users", indexes = {@Index(columnList = "personal_number", name = "user_personal_number_hidx")})
@EntityListeners({AuditingEntityListener.class})

@Indexed(index = "users")
@AnalyzerDef(name = "nGrams",
        tokenizer = @TokenizerDef(factory = NGramTokenizerFactory.class, params = {
                @Parameter(name = "minGramSize", value = "2"), @Parameter(name = "maxGramSize", value = "15")}),
        filters = {@TokenFilterDef(factory = LowerCaseFilterFactory.class),
                   @TokenFilterDef(factory = StandardFilterFactory.class)})

@JsonIgnoreProperties(value = {"token", "account", "role", "password"})
public class User extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;

    @Column(updatable = false, nullable = false, unique = true, length = 11, name = "personal_number")
    @Field
    @Analyzer(definition = "nGrams")
    @NonNull
    private String personalNumber;

    @Column(nullable = false)
    @Setter
    @NonNull
    private String password;

    @Column(nullable = false, length = 55)
    @Field
    @Analyzer(definition = "nGrams")
    @NonNull
    private String firstname;

    @Column(nullable = false, length = 55)
    @Analyzer(definition = "nGrams")
    @Field
    @NonNull
    private String lastname;

    @Column(nullable = false)
    @Email
    @NonNull
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private Role role = Role.USER;

    @Column(name = "personal_email")
    @Email
    @Setter
    private String personalEmail;

    @Column
    @Enumerated(EnumType.ORDINAL)
    @Setter
    @NonNull
    private State state = State.INVALID;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @NonNull
    private Company company;

    // TODO ADMIN user not have account
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id", unique = true)
    @Setter
    private Account account;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private VerificationToken token;

    public void addVerificationToken(final VerificationToken token) {
        this.token = token;
    }
}
