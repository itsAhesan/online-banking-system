package com.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.model.FundTransferRequest;
import com.banking.model.User;
import com.banking.repository.UserRepository;

@Service
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	private UserRepository userRepository;

	public void transferFunds(FundTransferRequest request) {

		User sender = userRepository.findByEmail(request.getSender())
				.orElseThrow(() -> new RuntimeException("Sender not found"));
		User recipient = userRepository.findByEmail(request.getRecipient())
				.orElseThrow(() -> new RuntimeException("Recipient not found"));

		/*
		 * User sender = userRepository.findByEmail(request.getSender());
		 * 
		 * if (sender == null) { throw new RuntimeException("Sender not found"); }
		 * 
		 * User recipient = userRepository.findByEmail(request.getRecipient()); if
		 * (recipient == null) { new RuntimeException("Recipient not found"); }
		 */

		if (sender.getBalance() < request.getAmount()) {
			throw new RuntimeException("Insufficient funds");
		}

		sender.setBalance(sender.getBalance() - request.getAmount());
		recipient.setBalance(recipient.getBalance() + request.getAmount());

		userRepository.save(sender);
		userRepository.save(recipient);
	}

}
