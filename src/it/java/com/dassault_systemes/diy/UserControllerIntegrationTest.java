package com.dassault_systemes.diy;

import com.dassault_systemes.diy.domain.User;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiyApplication.class)
@WebIntegrationTest("server.port:9000")
public class UserControllerIntegrationTest {

    RestTemplate restTemplate = new TestRestTemplate();

    @Test
    @Ignore
    public void getUsersNotEmpty() {
        String url = "http://localhost:9000/api/users";

        ParameterizedTypeReference<List<User>> responseType = new ParameterizedTypeReference<List<User>>() {};

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<User> users = responseEntity.getBody();

        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

}
