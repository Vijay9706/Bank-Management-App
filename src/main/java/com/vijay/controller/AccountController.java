package com.vijay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.controller.service.AccountService;
import com.vijay.entity.Account;

@RestController()
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	//create the account 
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount (@RequestBody Account account) {
		
	     Account  createdAccount=service.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
		
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountNumber){
		Account AccountDetails= service.getAccountDetailsByAccountNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(AccountDetails);
		
	}
	
	@GetMapping("/getAllAccounts")
	public ResponseEntity <List<Account>> getAllAccountDetails (){
		List<Account> AccountAllDetails= service.getAllAccountDetails();
		return ResponseEntity.status(HttpStatus.OK).body(AccountAllDetails);
		
	}
	
	@PutMapping("/deposit/{accountNumber}/{amount}")
	public Account depositAccount(@PathVariable Long accountNumber ,@PathVariable Double amount) {
		Account deposit =  service.depositAmount(accountNumber, amount);
		return deposit;
		
	}
    
	@PutMapping("/withdraw/{accountNumber}/{amount}")
	public Account WithdrawAccount(@PathVariable Long accountNumber ,@PathVariable Double amount) {
		Account withdraw =  service.withdrawAmount(accountNumber, amount);
		return withdraw;
		
	}
	
	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity deleteAccount(@PathVariable Long accountNumber) {
		 service.closeAccount(accountNumber);
		return  new ResponseEntity<> ("Account deleted succesffully" , HttpStatus.ACCEPTED) ;
		
		
	}
}
