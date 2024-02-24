package in.bankingapp.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.bankingapp.dto.AccountDto;
import in.bankingapp.mapper.AccountMapper;
import in.bankingapp.model.Account;
import in.bankingapp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	AccountRepository accountRepository ;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = this.accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}




	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exits"));
		return AccountMapper.mapToAccountDto(account);
	}




	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exits"));
		
	 double total = 	account.getBalance() + amount;
	 account.setBalance(total);
	 Account saveAccount = this.accountRepository.save(account);
	 
		return AccountMapper.mapToAccountDto(saveAccount);
	}




	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exits"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		
	 double total = 	account.getBalance() - amount;
	 account.setBalance(total);
	 Account saveAccount = this.accountRepository.save(account);
	 
		return AccountMapper.mapToAccountDto(saveAccount);
	}




	@Override
	public List<AccountDto> getAllAcount() {
	List<Account> accounts = this.accountRepository.findAll();
	
	return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
	.collect(Collectors.toList()) ;
	
		
	}




	@Override
	public void deleteById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exits"));
		
		this.accountRepository.deleteById(id);
		
	}
	
	
}
