package com.ds.ce.diy.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import java.time.LocalDate;

@StaticMetamodel(Registration.class)
public class Registration_ {
    public static volatile SingularAttribute<Registration, LocalDate> date;
}
