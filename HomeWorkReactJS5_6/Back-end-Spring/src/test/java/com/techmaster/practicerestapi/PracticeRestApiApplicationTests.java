package com.techmaster.practicerestapi;

import com.techmaster.practicerestapi.service.FileServiceImpl;
import com.techmaster.practicerestapi.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticeRestApiApplicationTests {
	@Autowired
	FileServiceImpl fileService;

	@Autowired
	MailService mailService;
	@Test
	void contextLoads() {
	}
	void getFileExtennsion_test(){
		System.out.println(fileService.getFileExtension("abc.jpg"));

	}
	@Test
	void testMail(){
		mailService.sendEmail("maxnhon28496@gmail.com","forgot password","adasdasavava");
	}
}
