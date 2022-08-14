package com.example.demo;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest(classes = ApiApplication.class)
@CucumberContextConfiguration
class ApiApplicationTests {
	@Test
	void contextLoads() {
		Assert.assertEquals(true,true);
	}


}
