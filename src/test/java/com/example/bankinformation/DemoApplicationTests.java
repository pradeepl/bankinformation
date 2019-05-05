package com.example.bankinformation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;



//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

//	@Test
	public void contextLoads() {
    TestRestTemplate restTemplate = new TestRestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("https://indian-bank-information.herokuapp.com/branch_info?bank_name=ABHYUDAYA COOPERATIVE BANK LIMITED&city=MUMBAI", String.class);
    assertThat(response.getStatusCode().equals(HttpStatus.OK.value()));
	}

}
