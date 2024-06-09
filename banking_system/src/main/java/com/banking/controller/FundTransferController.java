package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.model.FundTransferRequest;
import com.banking.service.FundTransferService;


@RestController
@RequestMapping("/api")
public class FundTransferController {

	 @Autowired
	    private FundTransferService fundTransferService;

	    @PostMapping("/transferFunds")
	    public ResponseEntity<String> transferFunds(@RequestBody FundTransferRequest request) {
	        try {
	            fundTransferService.transferFunds(request);
	            return ResponseEntity.ok("Funds transferred successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	
}
