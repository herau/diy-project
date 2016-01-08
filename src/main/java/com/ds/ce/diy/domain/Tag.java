package com.ds.ce.diy.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tags")
@Indexed(index = "tags")
//TODO #search index root and children
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"name", "root"}, callSuper = false)
public class Tag extends AbstractAuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false, name = "tag_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Field
    @Analyzer(definition = "nGrams")
    @NonNull
    private String name;

    @ManyToOne
    private Tag root;

    @OneToMany(mappedBy="root")
    private Set<Tag> children;
}
