package com.ds.ce.diy;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DiyApplication.class)
@WebAppConfiguration
public class DiyApplicationTests {

	@Test
	@Ignore
	//TODO MailSender isn't autowired
	public void contextLoads() {
	}

}
