package com.dassault_systemes.diy;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiyApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public abstract class AbstractControllerTest {

    private static final String LOCALHOST = "http://localhost:";

    protected RestTemplate rest = new TestRestTemplate();

    @Value("${local.server.port}")
    int port;

    protected String getPath(String suffix) {
        return LOCALHOST + port + suffix;
    }

    protected String getPath() {
        return getPath(null);
    }

}
