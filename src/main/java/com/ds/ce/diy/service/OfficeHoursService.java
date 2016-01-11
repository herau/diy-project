package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.OfficeHours;
import com.ds.ce.diy.domain.OfficeHoursType;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.repositories.OfficeHoursRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.time.LocalDate;

@Service
public class OfficeHoursService {

    private final OfficeHoursRepository repository;

    @Inject
    public OfficeHoursService(OfficeHoursRepository repository) {
        this.repository = repository;
    }

    public void subscribe(User user, LocalDate date, OfficeHoursType userType) {
        repository.save(new OfficeHours(user, date, userType));
    }

    public void unsubscribe(User user, LocalDate date) {
        repository.findByUserAndDate(user, date).ifPresent(officeHours -> {
            officeHours.unsubscribe();
            repository.save(officeHours);
        });
    }


}
