package com.vijay.controller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.entity.Account;
import com.vijay.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    
	@Autowired
	AccountRepository repository;
	
	
	@Override
	public Account createAccount(Account account) {
		 Account  accountSaved =repository.save(account);
		return accountSaved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long account_number) {
		Optional<Account> details = repository.findById(account_number);
		if(details.isEmpty()) {
			throw new RuntimeException("Account is not present");
		} 
		
		return details.get();
	}

	@Override
	public List<Account> getAllAccountDetails() {
	List<Account> ListOfAccount	=repository.findAll();
		return ListOfAccount;
	}

	@Override
	public Account depositAmount(Long account_number, Double amount) {
		Optional<Account> AccountDetail=repository.findById(account_number);
		
		if(AccountDetail.isEmpty()) {
			throw new RuntimeException("Account is not present");
		} 
		   Account accountPresent = AccountDetail.get();
		   Double totalBalance=  accountPresent.getAccount_balance() + amount;
		   accountPresent.setAccount_balance(totalBalance);
		   repository.save(accountPresent);
		
		    return accountPresent ;
		}
	
	

	@Override
	public Account withdrawAmount(Long account_number, Double amount) {
		
		   Optional<Account> AccountDetail= repository.findById(account_number);
		   if(AccountDetail.isEmpty()) {
			  throw new RuntimeException("Account is not present");
		   }
		   
		   Account AccountPresent= AccountDetail.get();  
		   Double balanceAmount=AccountPresent.getAccount_balance()-amount;
		   AccountPresent.setAccount_balance(balanceAmount);
		   repository.save(AccountPresent);
		   
		      
		return AccountPresent ;
	}

	@Override
	public void closeAccount(Long account_number) {
		
	 getAccountDetailsByAccountNumber(account_number);
	
		repository.deleteById(account_number);
		
	}

	

}
