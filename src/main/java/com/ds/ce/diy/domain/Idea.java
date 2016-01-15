package com.ds.ce.diy.domain;

import com.ds.ce.diy.domain.audit.AbstractAuditableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "title")
@Table(name = "ideas")
public class Idea extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idea_id")
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    @NonNull
    @Getter
    private String title;

    @Column(length = 510, nullable = false)
    @NotEmpty
    @NonNull
    @Getter
    private String description;

    @Column(insertable = false)
    @Setter
    @Getter
    private String response;

    @CreatedBy
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", unique = true, updatable = false)
    private User user;

}
