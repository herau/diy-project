package com.ds.ce.diy;

import com.ds.ce.diy.domain.User;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserControllerIntegrationTest extends AbstractControllerTest {

    @Test
    public void getUsersNotEmpty() {
        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};

        ResponseEntity<List<User>> responseEntity =
                rest.exchange(getPath("/api/users"), HttpMethod.GET, null, responseType);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}
