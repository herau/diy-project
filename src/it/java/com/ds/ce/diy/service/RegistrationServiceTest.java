package com.ds.ce.diy.service;

import com.ds.ce.diy.DiyApplication;
import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.RegistrationType;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.repositories.RegistrationRepository;
import com.ds.ce.diy.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiyApplication.class)
public class RegistrationServiceTest {

    @Autowired
    RegistrationService service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationRepository repository;

    User user;

    LocalDate date;

    @Before
    public void setUp() throws Exception {
        user = userRepository.save(new User("2212", "", "", "", "", Company.DS));
        date = LocalDate.now();
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void officeHoursService_subscribeFirst_ok() throws Exception {
        service.subscribe(user, date, RegistrationType.FIRST);

        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    public void officeHoursService_subscribeSeveralPeopleOnSameDate_ok() throws Exception {
        service.subscribe(user, date, RegistrationType.FIRST);
        assertThat(repository.findAll()).hasSize(1);

        User user2 = userRepository.save(new User("2213", "", "", "", "", Company.DS));
        service.subscribe(user2, date, RegistrationType.SECOND);

        assertThat(repository.findAll()).hasSize(2);

        User user3 = userRepository.save(new User("2214", "", "", "", "", Company.DS));
        service.subscribe(user3, date, RegistrationType.REPLACEMENT);

        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    public void officeHoursService_subscribePeopleForMultipleDate_ok() throws Exception {
        service.subscribe(user, date, RegistrationType.FIRST);
        assertThat(repository.findAll()).hasSize(1);

        service.subscribe(user, date.plusDays(1), RegistrationType.FIRST);
        assertThat(repository.findAll()).hasSize(2);

        service.subscribe(user, date.plusDays(7), RegistrationType.FIRST);
        assertThat(repository.findAll()).hasSize(3);

        service.subscribe(user, date.plusDays(14), RegistrationType.SECOND);
        assertThat(repository.findAll()).hasSize(4);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void officeHoursService_subscribe_uniqueConstraintOnDateAndUser() throws Exception {
        service.subscribe(user, date, RegistrationType.FIRST);
        assertFalse(repository.findAll().isEmpty());
        // unable to register twice the same user for the same date
        service.subscribe(user, date, RegistrationType.FIRST);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void officeHoursService_subscribe_uniqueConstraintOnDateAndType() throws Exception {
        service.subscribe(user, date, RegistrationType.FIRST);
        assertFalse(repository.findAll().isEmpty());
        // unable to register two different users for a same
        User user2 = userRepository.save(new User("2213", "", "", "", "", Company.DS));
        service.subscribe(user2, date, RegistrationType.FIRST);
    }

}
