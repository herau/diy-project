package com.ds.ce.diy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class Lifespan implements Serializable {

    @Column(nullable = false, updatable = false, name = "serial_number")
    @Getter
    private String serialNumber;

    @Column(nullable = false)
    @Getter @Setter
    private Status status;

    @Column(insertable = false, name = "exit_date")
    private LocalDate exitDate;

    @Column(insertable = false, name = "exit_mode")
    private ExitMode exitMode;

    protected Lifespan(){}

    public Lifespan(String serialNumber) {
        this.status = Status.OK;
        this.serialNumber = serialNumber;
    }

    public void stop(LocalDate exitDate, ExitMode exitMode) {
        this.exitDate = exitDate;
        this.exitMode = exitMode;
        //TODO status should change to KO ?
    }

    public void stop(ExitMode exitMode) {
        stop(LocalDate.now(), exitMode);
    }


}
