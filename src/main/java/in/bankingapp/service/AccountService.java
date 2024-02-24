package in.bankingapp.service;

import java.util.List;

import in.bankingapp.dto.AccountDto;

public interface AccountService {
	AccountDto createAccount(AccountDto acc);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	List<AccountDto> getAllAcount();
	
	void deleteById(Long id);
	

}
