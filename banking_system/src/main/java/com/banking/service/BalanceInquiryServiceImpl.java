package com.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.model.User;
import com.banking.repository.UserRepository;

@Service
public class BalanceInquiryServiceImpl implements BalanceInquiryService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Double getBalance(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		return user.getBalance();
	}

}
