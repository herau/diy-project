package com.ds.ce.diy.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "tool_id")
    private Integer id;

    @Column(nullable = false)
    @NonNull
    @Getter
    @Setter
    private String message;

    @Column(nullable = false, name = "display_date")
    @NonNull
    @Getter
    @Setter
    private LocalDate displayDate;
}
