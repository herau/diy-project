package com.ds.ce.diy;

import com.ds.ce.diy.domain.Company;
import com.ds.ce.diy.domain.State;
import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.security.VerificationToken;
import com.ds.ce.diy.domain.security.VerificationTokenType;
import com.ds.ce.diy.dto.UserPasswordDTO;
import com.ds.ce.diy.repositories.UserRepository;
import com.ds.ce.diy.repositories.VerificationTokenRepository;
import com.ds.ce.diy.service.TokenService;
import com.ds.ce.diy.web.EntryPoint;
import com.ds.ce.diy.web.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class TokenControllerTest extends AbstractControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    UserRepository userRepository;

    @Inject
    TokenService tokenService;

    @Inject
    VerificationTokenRepository tokenRepository;

    VerificationToken token;

    User user;

    String apiPath;

    @Before
    public void setUp() {
        user = new User("0001", "foo", "bar", "dd", "foo.bar@3ds.com", Company.DS, State.INVALID);
        userRepository.save(user);
        token = tokenService.createUserToken(user, VerificationTokenType.EMAIL_REGISTRATION);
        apiPath = getPath(EntryPoint.TOKENS + "/{token}");
    }

    @After
    public void tearDown() {
        // data are shared between tests
        userRepository.deleteAll();
    }

    @Test
    public void tokenConsumption() {
        ResponseEntity<String> responseEntity = rest.getForEntity(apiPath, String.class, token.getToken());

        assertEquals(responseEntity.getStatusCode(), HttpStatus.FOUND);
        assertEquals(responseEntity.getHeaders().getLocation(),
                     URI.create(getPath("/profile/" + token.getUser().getPersonalNumber() + "/password")));
    }

    @Test
    public void invalidToken() throws IOException {
        ResponseEntity<String> responseEntity = rest.getForEntity(apiPath, String.class, "BAD_TOKEN");

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        ErrorResponse errorResponse = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        assertEquals(errorResponse.getStatus(), HttpStatus.BAD_REQUEST.value());
        //TODO illegalArgumentException but should be other because is inform user that token are in base64
    }

    @Test
    public void useTwiceTheSameToken() throws IOException {
        // first time OK (302)
        tokenConsumption();
        // then set the password for the user (and so valid the token)
        String userTokenPath = getPath("/api/users/token/{token}");
        UserPasswordDTO dto = new UserPasswordDTO();
        dto.setPassword("test");
        JsonNode json = objectMapper.valueToTree(dto);

        HttpEntity<JsonNode> requestEntity = new HttpEntity<>(json);
        ResponseEntity<String> userResponseEntity =
                rest.exchange(userTokenPath, HttpMethod.PATCH, requestEntity, String.class, token.getToken());
        assertEquals(userResponseEntity.getStatusCode(), HttpStatus.OK);
        // second time KO
        ResponseEntity<String> responseEntity = rest.getForEntity(apiPath, String.class, token.getToken());

        assertEquals(HttpStatus.GONE, responseEntity.getStatusCode());

        ErrorResponse errorResponse = objectMapper.readValue(responseEntity.getBody(), ErrorResponse.class);
        assertEquals(HttpStatus.GONE.value(), errorResponse.getStatus());
    }

}
