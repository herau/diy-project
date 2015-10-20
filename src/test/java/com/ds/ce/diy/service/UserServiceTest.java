package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Account;
import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.dto.UserDTO;
import com.ds.ce.diy.repositories.AccountRepository;
import com.ds.ce.diy.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;
    @Mock
    PasswordService passwordService;
    @Mock
    AccountRepository accountRepository;

    UserService userService;

    @Before
    public void setUp(){
        userService = new UserServiceImpl(repository, passwordService, accountRepository);
    }


    @Test(expected = IllegalArgumentException.class)
    public void createUserWithNullPersonalNumber_ko() {
        UserDTO userMock = mock(UserDTO.class);
        userService.create(userMock);
    }

    @Test
    public void createUser_withOptionalFields_ok() {
        UserDTO userMock = mock(UserDTO.class);
        String personalNumber = "0000";
        String firstname = "foo";
        String lastname = "bar";
        String email = "foo.bar@3ds.com";

        // expectations
        when(userMock.getPersonalNumber()).thenReturn(personalNumber);
        when(userMock.getFirstname()).thenReturn(firstname);
        when(userMock.getLastname()).thenReturn(lastname);
        when(userMock.getEmail()).thenReturn(email);
        // default value
        when(userMock.getCompany()).thenReturn(Company.DS);

        when(passwordService.encode(anyString())).thenReturn("randomEncodedPassword");

        // test
        userService.create(userMock);

        // assertions
        verify(passwordService).generateRandom();
        verify(passwordService).encode(anyString());
        verify(accountRepository).save(any(Account.class));
        // retrieve generated user
        ArgumentCaptor<User> userToSave = ArgumentCaptor.forClass(User.class);
        verify(repository).save(userToSave.capture());

        User user = userToSave.getValue();

        assertEquals(user.getPersonalNumber(), personalNumber);
        assertEquals(user.getFirstname(), firstname);
        assertEquals(user.getLastname(), lastname);
        assertEquals(user.getEmail(), email);
        assertEquals(user.getState(), State.INVALID);
        assertNotNull(user.getPassword());
        // optional fields
        assertEquals(user.getCompany(), Company.DS);
        assertNull(user.getPersonalEmail());
        // assert that an account has been created
        assertNotNull(user.getAccount());
        assertEquals(user.getAccount().getBalance(), Double.valueOf("0"));
    }

}
