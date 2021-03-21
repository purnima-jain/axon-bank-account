package com.purnima.jain.bank.account.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.bank.account.domain.CustomBankAccountHistory;
import com.purnima.jain.bank.account.json.CustomBankAccountHistoryResponseDto;
import com.purnima.jain.bank.account.service.CustomHistoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/bank-accounts")
@Slf4j
public class CustomHistoryController {

	private final CustomHistoryService customHistoryService;

	public CustomHistoryController(CustomHistoryService customHistoryService) {
		super();
		this.customHistoryService = customHistoryService;
	}

	@GetMapping("/{accountNumber}/customHistory")
	public List<CustomBankAccountHistoryResponseDto> getCustomHistoryForAccount(@PathVariable(value = "accountNumber") String accountNumber) throws InterruptedException, ExecutionException {
		log.info("Inside the CustomHistoryController REST Controller for GET::getCustomHistoryForAccount()..........");
		List<CustomBankAccountHistory> customBankAccountHistoryList = customHistoryService.getCustomHistoryForAccount(accountNumber);
		List<CustomBankAccountHistoryResponseDto> customBankAccountHistoryResponseDtoList = customBankAccountHistoryList
																								.stream()
																								.map(customBankAccountHistory -> new CustomBankAccountHistoryResponseDto(customBankAccountHistory))
																								.collect(Collectors.toList());
		return customBankAccountHistoryResponseDtoList;
	}

}
