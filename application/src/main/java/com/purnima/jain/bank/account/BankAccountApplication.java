package com.purnima.jain.bank.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAccountApplication {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
		logger.info("BankAccountApplication Started........");
	}

}