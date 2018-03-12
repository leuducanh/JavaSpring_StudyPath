package edu.hanoi.service.springservice.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockRestServiceServer
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
DependencyInjectionTestExecutionListener.class		
})
public class SpringHnServiceApplicationTests {


}
