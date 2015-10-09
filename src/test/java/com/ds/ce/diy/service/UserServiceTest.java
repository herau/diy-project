package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.Account;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    public void createUser_ok() {
        UserDTO userMock = mock(UserDTO.class);
        String personalNumber = "0000";
        String firstname = "foo";
        when(userMock.getPersonalNumber()).thenReturn(personalNumber);
        when(userMock.getFirstname()).thenReturn(firstname);

        userService.create(userMock);

        verify(passwordService, times(1)).generateRandom();
        verify(passwordService, times(1)).encode(anyString());
        verify(accountRepository, times(1)).save(any(Account.class));
        ArgumentCaptor<User> userToSave = ArgumentCaptor.forClass(User.class);
        verify(repository, times(1)).save(userToSave.capture());

        User user = userToSave.getValue();

        assertEquals(user.getPersonalNumber(), personalNumber);
        assertEquals(user.getFirstname(), firstname);
        assertNotNull(user.getAccount());
        assertEquals(user.getAccount().getBalance(), Double.valueOf("0"));
    }

}
