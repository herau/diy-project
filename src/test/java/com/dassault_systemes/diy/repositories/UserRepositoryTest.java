package com.dassault_systemes.diy.repositories;

import com.dassault_systemes.diy.DiyApplication;
import com.dassault_systemes.diy.domain.Company;
import com.dassault_systemes.diy.domain.State;
import com.dassault_systemes.diy.domain.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiyApplication.class)
public class UserRepositoryTest {

    public static final String PERSONAL_NUMBER = "23546";

    private User user;

    @Inject
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        user = new User(PERSONAL_NUMBER, "foo", "bar", "foobar", "foo@bar.com", Company.DS, State.VALID);
        repository.save(user);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFindByPersonalNumber() throws Exception {
        User user1 = repository.findByPersonalNumber(PERSONAL_NUMBER);
        assertEquals(user, user1);
    }

    @Test
    public void testFindByFirstnameOrLastname() throws Exception {

    }
}