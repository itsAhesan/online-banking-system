package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.BalanceInquiryRequest;
import com.banking.service.BalanceInquiryService;

@RestController
@RequestMapping("/api")
public class BalanceInquiryController {

	@Autowired
	private BalanceInquiryService balanceInquiryService;

	@PostMapping("/accountBalance")
	public ResponseEntity<String> getBalance(@RequestBody BalanceInquiryRequest request) {
		try {
			Double balance = balanceInquiryService.getBalance(request.getEmail());
			return ResponseEntity.ok("User Balance " + balance);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
