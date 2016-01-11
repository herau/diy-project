package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Registration;
import com.ds.ce.diy.domain.RegistrationType;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.repositories.RegistrationRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import java.time.LocalDate;

@Service
public class RegistrationService {

    private final RegistrationRepository repository;

    @Inject
    public RegistrationService(RegistrationRepository repository) {
        this.repository = repository;
    }

    public void subscribe(User user, LocalDate date, RegistrationType userType) {
        repository.save(new Registration(user, date, userType));
    }

    public void unsubscribe(User user, LocalDate date) {
        repository.findByUserAndDate(user, date).ifPresent(officeHours -> {
            officeHours.unsubscribe();
            repository.save(officeHours);
        });
    }

    public void getAllAfterNow() {
        repository.findAll();
    }

}
