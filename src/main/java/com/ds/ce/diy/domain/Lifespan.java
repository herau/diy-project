package com.ds.ce.diy.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Lifespan implements Serializable {

    @Column(nullable = false, updatable = false, name = "serial_number")
    @Getter
    @NonNull
    private String serialNumber;

    //TODO in db as integer and must be non nullable
    @Enumerated
    @Getter @Setter
    private Status status;

    @Column(insertable = false, name = "exit_date")
    private LocalDate exitDate;

    @Column(insertable = false, name = "exit_mode")
    private ExitMode exitMode;

    public void stop(LocalDate exitDate, ExitMode exitMode) {
        this.exitDate = exitDate;
        this.exitMode = exitMode;
        //TODO status should change to KO ?
    }

    public void stop(ExitMode exitMode) {
        stop(LocalDate.now(), exitMode);
    }


}
